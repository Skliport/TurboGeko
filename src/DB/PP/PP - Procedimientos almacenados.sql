/* Módulo PP: Pago a proveedores - Procedimientos almacenados */
/* Magaña Urrutia, Juan Sebastian */ /* Santamaría Calderón, René Francisco */

/* - - - 1. Registra un nuevo plan de pago - - - */
-- Y lista sus cuotas correspondientes --
CREATE OR REPLACE FUNCTION new_purchase_payment_plan
(ppurchase_id integer, ptotal real, pnumber_of_payments integer,
plate_fee_percentage_charge real, ppurchase_date date)
RETURNS INTEGER 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
    ldue_date date:=null;
BEGIN  
    -- 1. Registrando un nuevo plan de pago --
    INSERT INTO purchase_payment_plan
    (purchase_payment_plan_id, purchase_id, payment_plan_status_id, total, amount_paid,
    remaining_amount, number_of_payments, late_fee_percentage_charge)
    VALUES (default, ppurchase_id, 1, ptotal, 0, ptotal, pnumber_of_payments, 
    plate_fee_percentage_charge );   
            
    -- 2. Creando una lista de cuotas --
    
    -- Calculando due date de primera cuota --
    -- ldue_date = (DATE(ppurchase_date) + int '30');
    ldue_date = (DATE(ppurchase_date) + interval '1 month');
    
    -- Estableciendo cuotas --
    FOR i IN 1..pnumber_of_payments LOOP

        INSERT INTO purchase_payment
        (purchase_payment_id, purchase_id, payment_status_id, total, paid_amount,
        due_date, payment_date)
        VALUES (default, ppurchase_id, 1, (ptotal/pnumber_of_payments), 0, ldue_date, null);  
            
        -- Calculando due date de próxima cuota --      
        -- ldue_date = (DATE(ldue_date) + int '30');
        ldue_date = (DATE(ldue_date) + interval '1 month');
    END LOOP;
    RAISE NOTICE 'New purchase payment plan has been successfully registered';
RETURN 1;
END;
$$;

/* - - - 2. Realiza un pago de cuota - - - */
-- Cancela mora si es existente y realiza avances de pago si es posible--
CREATE OR REPLACE FUNCTION new_purchase_payment
(ppurchase_payment_id int, 
 ppaid_amount real)
RETURNS INTEGER 
LANGUAGE plpgsql 
AS $$ DECLARE 
   lpurchase_id int:=null;
   ltotal real:=null;
   lcurrent_funds real:=null;
   lamount_to_bill real:=null;
   lamount_on_paid real:=null;
   lmax_upfront_funds real:=null;
   llate_fee_amount real:=null;
BEGIN  
    /* - - - 1. Asignación de variables - - - */
    -- ID de compra para este plan--
    SELECT pp.purchase_id INTO lpurchase_id FROM purchase_payment pp
    WHERE pp.purchase_payment_id = ppurchase_payment_id;
    
    -- Costo común de cuota para este plan --
    SELECT pp.total INTO ltotal FROM purchase_payment pp
    WHERE pp.purchase_payment_id = ppurchase_payment_id;
    
    -- Total cancelado en esta cuota --
    SELECT pp.paid_amount INTO lamount_on_paid FROM purchase_payment pp
    WHERE pp.purchase_payment_id = ppurchase_payment_id;
    
   -- Total que puede ser avanzado (Total en pagos pendientes sin mora) --
    SELECT SUM(pp.total - pp.paid_amount)::numeric INTO lmax_upfront_funds
    FROM purchase_payment pp WHERE pp.purchase_id = lpurchase_id AND
    pp.payment_status_id = 1;
 
    -- Monto de mora a cargar en esta cuota.
    SELECT ((ppp.total/ppp.number_of_payments) * ppp.late_fee_percentage_charge)::numeric
    INTO llate_fee_amount
    FROM purchase_payment_plan ppp WHERE ppp.purchase_id = lpurchase_id;
    
    /* - - - 2. Validaciones - - - */

   -- Verificando: La cuota ya ha sido cancelada -
    IF ((SELECT pp.payment_status_id FROM 
        purchase_payment pp WHERE pp.purchase_payment_id = ppurchase_payment_id) = 2) 
    THEN   
        RAISE NOTICE 'Transaction cancelled: Payment already delivered';
        RETURN null;
    END IF;
    
    -- Verificando: El monto ingresado debe ser
    -- inferior total que puede ser avanzado (Para evitar avances inexistentes) --
    IF (ppaid_amount::numeric>lmax_upfront_funds::numeric) 
    THEN   
        RAISE NOTICE 'Transaction cancelled: Payment exceds funds capacity';
        RETURN null;
    END IF;

    /* - - - 3. Cancelación de mora - - - */
    -- Verificando: Esta cuota se encuentra en mora --
    IF ((SELECT pp.payment_status_id FROM purchase_payment pp 
        WHERE pp.purchase_payment_id = ppurchase_payment_id) = 3) 
    THEN   
        -- Cantidad a cobrar para esta cuota --
        lamount_to_bill = ltotal::numeric - lamount_on_paid::numeric;
        
        -- 3.1 Verificando: Cantidad ingresada es inferior al mínimo requerido para cancelar
        -- el monto de mora y el monto pendiente para esta cuota --
        IF (ppaid_amount<(lamount_to_bill + llate_fee_amount))
        THEN   
            RAISE NOTICE 'Transaction cancelled: Insufficient funds due to late fee';
            RETURN null;
        END IF;
            
        -- 3.2 Si el caso anterior no se cumple: Cancelando mora --
        ppaid_amount = ppaid_amount::numeric  - llate_fee_amount::numeric ;

        PERFORM add_to_late_fee_payment(ppurchase_payment_id);
            
    END IF;
    
    /* - - - 4. Registro de pago de cuotas y avances - - - */

    -- Cantidad a cobrar para esta cuota --
    lamount_to_bill = ltotal::numeric - lamount_on_paid::numeric;

    /* - - - Verificando monto de ingreso y posibles casos - - - */

    -- 4.1 El monto ingresado no es suficiente para realizar la transacción --
    IF (ppaid_amount::numeric<lamount_to_bill::numeric) THEN   
        RAISE NOTICE 'Transaction cancelled: Insufficient funds';
    RETURN null;
    
    -- 4.2 El monto ingresado es exacto para finalizar el pago de esta cuota --
    ELSEIF (lamount_to_bill::numeric=ppaid_amount::numeric) THEN 
        -- Cancelando cuota --
        PERFORM add_to_purchase_payment(ppurchase_payment_id, lamount_to_bill);
    RETURN 1;
     
    -- 4.3 El monto ingresado es sobre suficiente para realizar la transacción --
    ELSEIF (ppaid_amount::numeric>lamount_to_bill::numeric) THEN 
    
        -- Asignando nuevos fondos --
        lcurrent_funds = ppaid_amount::numeric;

        -- Recalculando fondos ---
        lcurrent_funds = lcurrent_funds::numeric - lamount_to_bill::numeric;

        -- Cancelando cuota actual ---
        PERFORM add_to_purchase_payment(ppurchase_payment_id, lamount_to_bill);
        
        -- Realizando avances de pago con los fondos restantes --
        -- Avances de pago se realizarán a la última cuota pendiente disponible --
        WHILE lcurrent_funds::numeric > 0 LOOP

            -- Última cuota pendiente disponible --
            SELECT pp.purchase_payment_id INTO ppurchase_payment_id 
            FROM purchase_payment pp
            WHERE pp.purchase_id = lpurchase_id AND pp.payment_status_id = 1
            ORDER BY pp.purchase_payment_id DESC LIMIT 1;

            -- Total cancelado en esta cuota --
            SELECT pp.paid_amount INTO lamount_on_paid FROM purchase_payment pp
            WHERE pp.purchase_payment_id = ppurchase_payment_id;

            -- Cantidad a cobrar para esta cuota --
            lamount_to_bill = ltotal - lamount_on_paid;
        
            -- 4.3.1 Verificando: Los fondos no son suficientes para cancelar
            -- esta cuota. Un avance parcial será realizado --
            IF (lcurrent_funds::numeric<lamount_to_bill::numeric) 
            THEN   
                -- Monto de avance para esta cuota --
                lamount_to_bill = lcurrent_funds;

                -- Registrando avance --
                PERFORM new_purchase_payment_upfront
                (lpurchase_id, ppurchase_payment_id, lamount_to_bill); 
                
                RETURN 1;
            END IF;

            -- 4.3.2 Los fondos son suficientes para cancelar esta cuota --
            
            -- Cancelando y registrando un nuevo adelanto ---
            PERFORM new_purchase_payment_upfront
            (lpurchase_id, ppurchase_payment_id, lamount_to_bill);
            
            -- Recalculando fondos ---
            lcurrent_funds = lcurrent_funds::numeric - lamount_to_bill::numeric;
        
        END LOOP;

    RETURN 1; 
    END IF;
        
RETURN 1;
END;
$$;

/* - - - 3. Registra un avance de pago  - - - */
CREATE OR REPLACE FUNCTION new_purchase_payment_upfront
(ppurchase_id int, 
 ppurchase_payment_id int,
 ppaid_amount real)
RETURNS INTEGER 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
BEGIN  
    -- 1. Registra avance de pago a última cuota pendiente disponible -- 
    INSERT INTO purchase_payment_upfront
    (purchase_payment_upfront_id, purchase_id, purchase_payment_id, paid_amount, upfront_payment_date)
    VALUES (default, ppurchase_id, ppurchase_payment_id, ppaid_amount, current_timestamp);   

    -- 2. Registra el pago correspondiente a la cuota de avance -- 
    PERFORM add_to_purchase_payment(ppurchase_payment_id, ppaid_amount);
    
    RAISE NOTICE 'New purchase payment upfront fee has been successfully registered';
RETURN 1;
END;
$$;


/* - - - 4. Registra un pago de cuota  - - - */
CREATE OR REPLACE FUNCTION add_to_purchase_payment
 (ppurchase_payment_id int, 
  ppaid_amount real)
RETURNS INTEGER 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
    lpurchase_id int:=null;
    lpayment_status_id int:=1; 
    ltotal real:=null;
    lamount_on_paid real:=null;
BEGIN  

    -- ID de compra actual --
    SELECT pp.purchase_id INTO lpurchase_id FROM purchase_payment pp
    WHERE pp.purchase_payment_id = ppurchase_payment_id;

    -- Costo común de cuota para este plan --
    SELECT pp.total INTO ltotal FROM purchase_payment pp
    WHERE pp.purchase_payment_id = ppurchase_payment_id;
    
    -- Total cancelado en esta cuota --
    SELECT pp.paid_amount INTO lamount_on_paid FROM purchase_payment pp
    WHERE pp.purchase_payment_id = ppurchase_payment_id;

    -- 1. Verificando el estado final de la cuota (Cancelado) --
    IF ((ppaid_amount+lamount_on_paid)=ltotal) THEN      
        lpayment_status_id = 2;
    END IF;

    -- 2. Cancelando cuota pendiente --
    UPDATE purchase_payment
    SET payment_status_id = lpayment_status_id,
    paid_amount = paid_amount + ppaid_amount,
    payment_date = current_timestamp
    WHERE purchase_payment_id = ppurchase_payment_id;

    -- 3. Actualiza los datos del plan de pago --
    PERFORM update_purchase_payment_plan_status(lpurchase_id);
        
    RAISE NOTICE 'New purchase payment has been successfully updated';
RETURN 1;
END;
$$;

/* - - - 5. Actualiza totales y estado (cancelado) en plan de pago  - - - */
CREATE OR REPLACE FUNCTION update_purchase_payment_plan_status
(ppurchase_id int)
RETURNS INTEGER 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
    ltotal real:=null;
    lamount_paid real:=null;
    lremaining_amount real:=null;
BEGIN  
    -- Obteniendo total final en plan de pago --
    SELECT ppp.total INTO ltotal FROM purchase_payment_plan ppp 
    WHERE ppp.purchase_id = ppurchase_id;

    -- Obteniendo total cancelado en plan de pago --
    SELECT SUM(pp.paid_amount) INTO lamount_paid FROM purchase_payment pp 
    WHERE pp.purchase_id = ppurchase_id;

    -- Obteniendo total restante en plan de pago --
    lremaining_amount = ltotal - lamount_paid;

    -- 1. Actualizando totales en plan de pago --
    UPDATE purchase_payment_plan
    SET amount_paid = lamount_paid,
    remaining_amount = lremaining_amount
    WHERE purchase_id = ppurchase_id;

    -- 2. Verificando si el plan de pago ha sido cancelado --
    IF (lremaining_amount=0) THEN  

        UPDATE purchase_payment_plan
        SET payment_plan_status_id = 2
        WHERE purchase_id = ppurchase_id;

    END IF;

RETURN 1;
END;
$$;

/* - - - 6.1. Verifica expiración (due_date) de una cuota específica  - - - */
-- Registra un cargo por mora pendiente en cuotas que han expirado --
CREATE OR REPLACE FUNCTION check_purchase_payment_late_fee_existence
(ppurchase_payment_id int)
RETURNS INTEGER 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
    lpurchase_id int:=null;
    ltotal real:=null;
    ldue_date date:=null;
BEGIN  
 
    -- ID de compra --
    SELECT pp.purchase_id INTO lpurchase_id FROM purchase_payment pp
    WHERE pp.purchase_payment_id = ppurchase_payment_id;

    -- Calculando total a cancelar en mora para esta cuota --
    SELECT (ppp.late_fee_percentage_charge * pp.total) INTO ltotal
    FROM purchase_payment pp JOIN purchase_payment_plan ppp 
    ON pp.purchase_id = ppp.purchase_id
    WHERE pp.purchase_payment_id = ppurchase_payment_id;
    
    -- Obteniendo due date de cuota actual --
    SELECT pp.due_date INTO ldue_date FROM purchase_payment pp 
    WHERE pp.purchase_payment_id = ppurchase_payment_id;

    -- Evaluando si la cuota ha superado la fecha de expiración --
    IF (current_date > ldue_date) THEN
        
        INSERT INTO purchase_late_fee_payment
        (late_fee_payment_id, purchase_id, purchase_payment_id, 
        late_fee_payment_status_id, total, paid_amount, payment_date) 
        VALUES (default, 
        lpurchase_id, ppurchase_payment_id, 1, ltotal, 0, null); 
        
        -- Modificando estado de cuota: En mora --
        UPDATE purchase_payment SET payment_status_id = 3
        WHERE purchase_payment_id = ppurchase_payment_id;

        RETURN 0;
    END IF;
    
RETURN 1;
END;
$$;

/* - - - 6.2. Verifica expiración (due_date) en todas las cuotas pendientes - - - */
-- Este procedimiento se ejecutará al inicio del programa principal --
CREATE OR REPLACE FUNCTION update_purchase_payment_late_fee_existence()
RETURNS INTEGER 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
    lrow RECORD;
BEGIN  
    FOR lrow IN 
    SELECT pp.purchase_payment_id FROM purchase_payment pp 
    WHERE payment_status_id = 1 LOOP
        PERFORM check_purchase_payment_late_fee_existence(lrow.purchase_payment_id);
    END LOOP;
RETURN 1;
END;
$$;

/* - - - 7. Registra un pago de mora */
CREATE OR REPLACE FUNCTION add_to_late_fee_payment
(ppurchase_payment_id int)
RETURNS INTEGER 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
BEGIN  
    -- Registra un pago a mora --
    UPDATE purchase_late_fee_payment
    SET late_fee_payment_status_id = 2, 
    paid_amount = total,
    payment_date = current_timestamp
    WHERE purchase_payment_id = ppurchase_payment_id; 

    -- Retira estado "en mora" del pago, asigna un 
    -- nuevo estado "pendiente" antes de ser cancelado --
    UPDATE purchase_payment
    SET payment_status_id = 1
    WHERE purchase_payment_id = ppurchase_payment_id;

RETURN 1;
END;
$$;

/* - - - - Procedimientos para consultas - - - - */
-- 1. Recupera planes de pago y detalles --
CREATE OR REPLACE FUNCTION getPurchasePaymentPlan()
RETURNS TABLE (
    purchase_payment_plan_id integer,
    purchase_id integer,
    payment_plan_status_id integer,
    name varchar(40),
    total real,
    amount_paid real,
    remaining_amount real,
    number_of_payments integer,
    late_fee_percentage_charge real,
    purchase_date date,
    company_name varchar(60),
    late_fee_total real,
    late_fee_paid_amount real
) 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
BEGIN  
    RETURN QUERY SELECT 
    ppp.purchase_payment_plan_id,
    ppp.purchase_id,
    ppp.payment_plan_status_id,
    ppps.name,
    ppp.total,
    ppp.amount_paid,
    ppp.remaining_amount,
    ppp.number_of_payments,
    ppp.late_fee_percentage_charge,
    ps.purchase_date,
    ss.company_name,
    getPurchasePaymentPlanLateFee(ppp.purchase_id) AS late_fee_total,
    getPurchasePaymentPaidLateFee(ppp.purchase_id) AS late_fee_paid_amount

    FROM purchase_payment_plan ppp 
    JOIN purchase_suppliers ps ON ppp.purchase_id = ps.purchase_id 
    JOIN purchase_suppliers_details psd ON psd.purchase_id = ps.purchase_id 
    JOIN materials mm ON mm.material_id = psd.material_id 
    JOIN suppliers ss ON ss.supplier_id = mm.supplier_id
    JOIN purchase_payment_plan_status ppps ON ppps.payment_plan_status_id =
    ppp.payment_plan_status_id ORDER BY ppp.purchase_id ASC;
END;
$$;

-- 2. Recupera cuotas y detalles --
CREATE OR REPLACE FUNCTION getPurchasePayment(ppurchase_id int)
RETURNS TABLE (
    purchase_payment_id integer,
    purchase_id integer,
    payment_status_id integer,
    total real,
    paid_amount real,
    late_fee_charge real,
    due_date date,
    payment_date timestamp,
    name varchar(40)
) 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
BEGIN  
        
    RETURN QUERY SELECT 
    pp.purchase_payment_id,
    pp.purchase_id,
    pp.payment_status_id,
    pp.total,
    pp.paid_amount,
    getPurchasePaymentLateFeeCharge(pp.purchase_payment_id) AS late_fee_charge,
    pp.due_date,
    pp.payment_date,
    pps.name
    FROM purchase_payment pp
    JOIN purchase_payment_status pps ON pp.payment_status_id = pps.payment_status_id
    WHERE pp.purchase_id = ppurchase_id ORDER BY pp.purchase_payment_id ASC;
END;
$$;

-- 3. Recupera detalles de pago --
CREATE OR REPLACE FUNCTION getPurchasePaymentDetails(ppurchase_payment_id int)
RETURNS TABLE (
    purchase_id integer,
    remaining_amount_to_pay real,
    late_fee_charge real,
    final_total real,
    new_payment_date date
) 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
    lremaining_amount_to_pay real:=null;
    llate_fee_charge real:=0;
    lfinal_total real:=null;
BEGIN  
    -- Monto restante para cancelar esta cuota --
    SELECT (pp.total - pp.paid_amount) INTO lremaining_amount_to_pay 
    FROM purchase_payment pp WHERE pp.purchase_payment_id = ppurchase_payment_id;
    
    -- Monto de mora para esta cuota --
    IF ((SELECT pp.payment_status_id FROM purchase_payment pp 
        WHERE pp.purchase_payment_id = ppurchase_payment_id) = 3) 
    THEN   
            SELECT (ppp.late_fee_percentage_charge * pp.total)::numeric INTO llate_fee_charge
            FROM purchase_payment pp 
            JOIN purchase_payment_plan ppp ON pp.purchase_id = ppp.purchase_id
            WHERE pp.purchase_payment_id = ppurchase_payment_id;
    END IF;
    
    -- Total final para cancelar esta cuota --
    lfinal_total = lremaining_amount_to_pay::numeric + llate_fee_charge::numeric;
    
    -- La cuota ya ha sido cancelada --
    IF ((SELECT pp.payment_status_id FROM 
        purchase_payment pp WHERE pp.purchase_payment_id = ppurchase_payment_id) = 2) 
    THEN   
        lremaining_amount_to_pay = 0; lfinal_total = 0;
    END IF;
    
    RETURN QUERY SELECT 
    pp.purchase_id,
    lremaining_amount_to_pay AS remaining_amount_to_pay,
    llate_fee_charge AS late_fee_charge,
    lfinal_total AS final_total,
    current_date as new_payment_date
    FROM purchase_payment pp WHERE pp.purchase_payment_id = ppurchase_payment_id;
END;
$$;

-- 4. Obtiene monto de mora para una cuota --
CREATE OR REPLACE FUNCTION getPurchasePaymentLateFeeCharge(ppurchase_payment_id int)
RETURNS REAL
LANGUAGE plpgsql 
AS $$ 
DECLARE 
    llate_fee_charge real:=0;
BEGIN  
    -- Monto de mora para esta cuota --
    IF ((SELECT pp.payment_status_id FROM purchase_payment pp 
        WHERE pp.purchase_payment_id = ppurchase_payment_id) = 3) 
    THEN   
            SELECT (ppp.late_fee_percentage_charge * pp.total)::numeric 
            INTO llate_fee_charge
            FROM purchase_payment pp 
            JOIN purchase_payment_plan ppp ON pp.purchase_id = ppp.purchase_id
            WHERE pp.purchase_payment_id = ppurchase_payment_id;
    END IF;
    
    RETURN llate_fee_charge;
END;
$$;

-- 5. Recupera monto final de mora para un plan de pago --
CREATE OR REPLACE FUNCTION getPurchasePaymentPlanLateFee(ppurchase_id int)
RETURNS REAL
LANGUAGE plpgsql 
AS $$ 
DECLARE 
    llate_fee_total real:=0;
BEGIN  

    SELECT SUM(plfp.total)::numeric INTO llate_fee_total
    FROM purchase_late_fee_payment plfp WHERE plfp.purchase_id = ppurchase_id;
    
    IF (llate_fee_total IS NULL) THEN   
        RETURN 0;
    END IF;
    
    RETURN llate_fee_total;
END;
$$;


-- 6. Recupera monto final de mora cancelada para un plan de pago --
CREATE OR REPLACE FUNCTION getPurchasePaymentPaidLateFee(ppurchase_id int)
RETURNS REAL
LANGUAGE plpgsql 
AS $$ 
DECLARE 
    llate_fee_paid_amount real:=0;
BEGIN  

    SELECT SUM(plfp.paid_amount)::numeric INTO llate_fee_paid_amount
    FROM purchase_late_fee_payment plfp WHERE plfp.purchase_id = ppurchase_id;
    
    IF (llate_fee_paid_amount IS NULL) THEN   
        RETURN 0;
    END IF;
    
    RETURN llate_fee_paid_amount;
END;
$$;


-- 7. Recupera todas las moras en cuota para plan de pago --
CREATE OR REPLACE FUNCTION getLateFeePayments(ppurchase_id int)
RETURNS TABLE (
    purchase_payment_id integer,
    status varchar(40),
    total real,
    paid_amount real,
    payment_date timestamp
) 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
BEGIN  
        
    RETURN QUERY SELECT 
    plfp.purchase_payment_id, 
    plfps.name AS status, 
    plfp.total, 
    plfp.paid_amount,
    plfp.payment_date 
    FROM purchase_late_fee_payment plfp JOIN
    purchase_late_fee_payment_status plfps 
    ON plfps.late_fee_payment_status_id = plfp.late_fee_payment_status_id
    WHERE plfp.purchase_id = ppurchase_id ORDER BY plfp.purchase_payment_id ASC;
END;
$$;

-- 8. Recupera todos los avances de pago en cuota para plan de pago --
CREATE OR REPLACE FUNCTION getUpfrontPayments(ppurchase_id int)
RETURNS TABLE (
    purchase_payment_id integer,
    paid_amount real,
    upfront_payment_date timestamp
) 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
BEGIN  
        
    RETURN QUERY SELECT 
    ppu.purchase_payment_id, 
    ppu.paid_amount,
    ppu.upfront_payment_date 
    FROM purchase_payment_upfront ppu 
    WHERE ppu.purchase_id = ppurchase_id ORDER BY ppu.purchase_payment_id ASC;
END;
$$;

-- 8. Recupera planes de pago filtrado por supplier --
CREATE OR REPLACE FUNCTION getPurchasePaymentPlanBySupplierName(ppcompany_name varchar)
RETURNS TABLE (
    purchase_payment_plan_id integer,
    purchase_id integer,
    payment_plan_status_id integer,
    name varchar(40),
    total real,
    amount_paid real,
    remaining_amount real,
    number_of_payments integer,
    late_fee_percentage_charge real,
    purchase_date date,
    company_name varchar(60),
    late_fee_total real,
    late_fee_paid_amount real
) 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
BEGIN  
    RETURN QUERY SELECT * FROM 
    (SELECT * FROM getPurchasePaymentPlan())sc1 WHERE
    sc1.company_name LIKE ('%'||ppcompany_name||'%');
END;
$$;

-- 9. Recupera planes de pago filtrado por purchase_id --
CREATE OR REPLACE FUNCTION getPurchasePaymentPlanByPurchaseId(ppurchase_id int)
RETURNS TABLE (
    purchase_payment_plan_id integer,
    purchase_id integer,
    payment_plan_status_id integer,
    name varchar(40),
    total real,
    amount_paid real,
    remaining_amount real,
    number_of_payments integer,
    late_fee_percentage_charge real,
    purchase_date date,
    company_name varchar(60),
    late_fee_total real,
    late_fee_paid_amount real
) 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
BEGIN  
    RETURN QUERY SELECT * FROM 
    (SELECT * FROM getPurchasePaymentPlan())sc1 WHERE
    sc1.purchase_id = ppurchase_id;
END;
$$;


-- 10. Recupera planes de pago filtrado por purchase_date --
CREATE OR REPLACE FUNCTION getPurchasePaymentPlanByPurchaseDate(ppurchase_date varchar)
RETURNS TABLE (
    purchase_payment_plan_id integer,
    purchase_id integer,
    payment_plan_status_id integer,
    name varchar(40),
    total real,
    amount_paid real,
    remaining_amount real,
    number_of_payments integer,
    late_fee_percentage_charge real,
    purchase_date date,
    company_name varchar(60),
    late_fee_total real,
    late_fee_paid_amount real
) 
LANGUAGE plpgsql 
AS $$ 
DECLARE 
BEGIN  
    RETURN QUERY SELECT * FROM 
    (SELECT * FROM getPurchasePaymentPlan())sc1 WHERE
    sc1.purchase_date::varchar LIKE ('%'||ppurchase_date||'%');
END;
$$;