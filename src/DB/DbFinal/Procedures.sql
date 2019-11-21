-- Modulo CI --
CREATE FUNCTION purchase_suppliers_log_funtion() RETURNS TRIGGER
AS
$$
BEGIN
    INSERT INTO purchase_suppliers_log VALUES(OLD.purchase_id,OLD.purchase_date,
        OLD.purchase_status,OLD.observations,
        OLD.subtotal,OLD.iva,OLD.total,NOW());
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

---Asignacion de disparadores---

CREATE TRIGGER purchase_suppliers_log_update BEFORE UPDATE ON purchase_suppliers
FOR EACH ROW
EXECUTE PROCEDURE purchase_suppliers_log_funtion();

--------------------------------------------------
--------------------------------------------------
--------------------------------------------------
------------------FUNCIONES-----------------------
--------------------------------------------------
--------------------------------------------------
--------------------------------------------------

--------------------------------------------------
---------Encapsulamientos de inserciones----------
--------------------------------------------------
CREATE OR REPLACE FUNCTION insertSupplier(company_name VARCHAR(60),contact_name VARCHAR(60),
    contact_title VARCHAR(60),address VARCHAR(70),
    email VARCHAR(30),city VARCHAR(25),region VARCHAR(20),
    postal_code VARCHAR(15),country VARCHAR(15),
    phone VARCHAR(15),fax VARCHAR(25),nrc VARCHAR(30),
    nit VARCHAR(30)) RETURNS BOOLEAN
AS
$$
DECLARE
registro RECORD;
cur CURSOR FOR SELECT suppliers.company_name FROM suppliers;
BEGIN
FOR registro IN cur LOOP
    IF(registro.company_name = company_name) THEN
--Validamos la existencia de un proveedor
	RETURN false;
    END IF;
END LOOP;
INSERT INTO suppliers VALUES
    (Default,company_name,contact_name,contact_title,
    address,email,city,region,postal_code,country,
    phone,fax,nrc,nit);
RETURN true;
END;
$$
LANGUAGE plpgsql;
-------------------------------------------------
-------------------------------------------------
-------------------------------------------------

CREATE OR REPLACE FUNCTION insertMaterial(supplier_id int, material_name VARCHAR(25),units_in_stock int,
    material_measure VARCHAR(25),unit_price REAL,number_lot INT,
    expiration_date DATE,discontinued INT) RETURNS VOID
AS
$$
BEGIN
INSERT INTO materials VALUES
    (default,supplier_id,material_name,units_in_stock
     ,material_measure,unit_price,number_lot,expiration_date
     ,discontinued);
END;
$$
LANGUAGE plpgsql;

-------------------------------------------------
-------------------------------------------------
-------------------------------------------------

CREATE OR REPLACE FUNCTION insertPurchaseSuppliersDetails(purchase_id INT,material_id INT,
    quantity INT,unit_price REAL) RETURNS VOID
AS
$$
BEGIN
INSERT INTO purchase_suppliers_details VALUES
    (DEFAULT,purchase_id,material_id,quantity,unit_price);
END;
$$
LANGUAGE plpgsql;

-------------------------------------------------
-------------------------------------------------
-------------------------------------------------

CREATE OR REPLACE FUNCTION insertPurchaseSuppliers(purchase_date DATE,purchase_status INT,
    observations varchar(100),subtotal REAL,iva REAL,total REAL) RETURNS VOID
AS
$$
BEGIN
INSERT INTO purchase_suppliers VALUES
    (default,purchase_date,purchase_status,observations,subtotal,iva,total);
END;
$$
LANGUAGE plpgsql;



--------------------------------------------------
------------------Busquedas-----------------------
--------------------------------------------------

CREATE OR REPLACE FUNCTION searchSuppliers(pcompany_name VARCHAR(60),pcontact_name VARCHAR(60),
    pcontact_title VARCHAR(60),paddress VARCHAR(70),
    pemail VARCHAR(30),pcity VARCHAR(25),pregion VARCHAR(20),
    ppostal_code VARCHAR(15),pcountry VARCHAR(15),
    pphone VARCHAR(15),pfax VARCHAR(25),pnrc VARCHAR(30),
    pnit VARCHAR(30))
	RETURNS TABLE(supplier_id INT,company_name VARCHAR(60),contact_name VARCHAR(60),
    contact_title VARCHAR(60),address VARCHAR(70),
    email VARCHAR(30),city VARCHAR(25),region VARCHAR(20),
    postal_code VARCHAR(15),country VARCHAR(15),
    phone VARCHAR(15),fax VARCHAR(25),nrc VARCHAR(30),
    nit VARCHAR(30))
AS
$$
DECLARE
lcompany_name VARCHAR(62):= concat('%',pcompany_name,'%');
lcontact_name VARCHAR(62):= concat('%',pcontact_name,'%');
lcontact_title VARCHAR(62):= concat('%',pcontact_title,'%');
laddress VARCHAR(72):= concat('%',paddress,'%');
lemail VARCHAR(32):= concat('%',pemail,'%');
lcity VARCHAR(27):= concat('%',pcity,'%');
lregion VARCHAR(22):= concat('%',pregion,'%');
lpostal_code VARCHAR(17):= concat('%',ppostal_code,'%');
lcountry VARCHAR(17):= concat('%',pcountry,'%');
lphone VARCHAR(17):= concat('%',pphone,'%');
lfax VARCHAR(27):= concat('%',pfax,'%');
lnrc VARCHAR(32):= concat('%',pnrc,'%');
lnit VARCHAR(32):= concat('%',pnit,'%');
BEGIN
RETURN QUERY SELECT * FROM suppliers WHERE suppliers.company_name LIKE lcompany_name AND
suppliers.contact_name LIKE lcontact_name AND
suppliers.contact_title LIKE lcontact_title AND
suppliers.address LIKE laddress AND
suppliers.email LIKE lemail AND
suppliers.city LIKE lcity AND
suppliers.region LIKE lregion AND
suppliers.postal_code LIKE lpostal_code AND
suppliers.country LIKE lcountry AND
suppliers.phone LIKE lphone AND
suppliers.fax LIKE lfax AND
suppliers.nrc LIKE lnrc AND
suppliers.nit LIKE lnit;
END;
$$
LANGUAGE plpgsql;

-------------------------------------------------
-------------------------------------------------
-------------------------------------------------

CREATE OR REPLACE FUNCTION searchSuppliers(psupplier_id INT)
	RETURNS TABLE(supplier_id INT,company_name VARCHAR(60),contact_name VARCHAR(60),
    contact_title VARCHAR(60),address VARCHAR(70),
    email VARCHAR(30),city VARCHAR(25),region VARCHAR(20),
    postal_code VARCHAR(15),country VARCHAR(15),
    phone VARCHAR(15),fax VARCHAR(25),nrc VARCHAR(30),
    nit VARCHAR(30))
AS
$$
DECLARE
lsupplier_id INT:= psupplier_id;
BEGIN
RETURN QUERY SELECT * FROM suppliers WHERE suppliers.supplier_id = lsupplier_id;
END;
$$
LANGUAGE plpgsql;
-------------------------------------------------
----------------Actualizaciones------------------
-------------------------------------------------
CREATE OR REPLACE FUNCTION updateSupplier(psupplier_id INT,pcompany_name VARCHAR(60),pcontact_name VARCHAR(60),
    pcontact_title VARCHAR(60),paddress VARCHAR(70),
    pemail VARCHAR(30),pcity VARCHAR(25),pregion VARCHAR(20),
    ppostal_code VARCHAR(15),pcountry VARCHAR(15),
    pphone VARCHAR(15),pfax VARCHAR(25),pnrc VARCHAR(30),
    pnit VARCHAR(30))
	RETURNS void
AS
$$
DECLARE
lcompany_name VARCHAR(60):= pcompany_name;
lcontact_name VARCHAR(60):= pcontact_name;
lcontact_title VARCHAR(60):= pcontact_title;
laddress VARCHAR(70):= paddress;
lemail VARCHAR(30):= pemail;
lcity VARCHAR(25):= pcity;
lregion VARCHAR(20):= pregion;
lpostal_code VARCHAR(15):= ppostal_code;
lcountry VARCHAR(15):= pcountry;
lphone VARCHAR(15):= pphone;
lfax VARCHAR(25):= pfax;
lnrc VARCHAR(30):= pnrc;
lnit VARCHAR(30):= pnit;
lsupplier_id INT := psupplier_id;
BEGIN
UPDATE suppliers set company_name= lcompany_name,contact_name= lcontact_name,
contact_title= lcontact_title,email= lemail,
address= laddress,city= lcity,
region= lregion,postal_code= lpostal_code,
country= lcountry,phone= lphone,
fax = lfax,nrc= lnrc,nit= lnit
where suppliers.supplier_id = lsupplier_id;
END;
$$
LANGUAGE plpgsql;

--------------------------------------------------
-------------------------------------------------- 
--------------------------------------------------

--------------------------------------------------
--------------------------------------------------
-------------------Vistas-------------------------
--------------------------------------------------
--------------------------------------------------
--------------------------------------------------

CREATE OR REPLACE VIEW viewLastRecordMaterial
AS SELECT * FROM materials ORDER BY material_id DESC LIMIT 1;

CREATE OR REPLACE VIEW viewLastIdMaterial
AS SELECT material_id FROM viewLastRecordMaterial;

CREATE OR REPLACE VIEW viewLastRecordFactura
AS SELECT * FROM purchase_suppliers ORDER BY purchase_id DESC LIMIT 1;

CREATE OR REPLACE VIEW viewLastIdFactura
AS SELECT purchase_id FROM viewLastRecordFactura;


-- MT --
CREATE OR REPLACE FUNCTION get_unit_cost_product(p_recipe_id INTEGER)
RETURNS REAL AS $$
DECLARE
l_result REAL := 0;
l_rec_mat RECORD;
l_cur_mat CURSOR(p_recipe_id INTEGER)
    FOR SELECT total_cost FROM supply_for_recipe
    WHERE recipe_id = p_recipe_id;
BEGIN
    OPEN l_cur_mat(p_recipe_id);
    LOOP
        FETCH l_cur_mat INTO l_rec_mat;
        EXIT WHEN NOT FOUND;
        SET l_result = l_result + l_rec_mat.total_cost;
    END LOOP;
    CLOSE l_cur_mat;
    RETURN l_result;
END; $$
LANGUAGE plpgsql;

--INSERCIÓN DE DISCRIMINANTE measurement
INSERT INTO meauserement_description (id_measurement, descrip) 
VALUES (1,'Kilogramo'),(2,'Miligramo'),(3,'Metro'),(4,'Centímetro'),
        (5,'Milímetro'),(6,'Litro'), (7,'Mililitro'),(8,'Onza'), (9,'Gramo'),
        (10,'Libra');
--procedimientos almacenados
--PROCEDIMIENTO: insertar una nueva receta

CREATE OR REPLACE FUNCTION set_new_recipe(punit_cost REAL, pid_product INT)
RETURNS INT AS $$
DECLARE
l_id INTEGER:=0;
BEGIN
    INSERT INTO recipe (unit_cost, sale_price, id_product) VALUES (punit_cost, psale_price, pid_product);
    SELECT id_recipe INTO l_id FROM recipe ORDER BY id_recipe DESC;
    RETURN l_id AS id_recipe;
END; $$
LANGUAGE plpgsql;

--procedimiento: insertar insumos de receta

CREATE OR REPLACE FUNCTION set_new_supply_for_recipe(pid_recipe INT, pid_material INT, pid_measurement INT, punit_cost REAL)
RETURNS VOID AS $$
BEGIN
    INSERT INTO supply_for_recipe(unit_cost,total_cost,id_measurement, id_recipe, id_material) 
    VALUES (punit_cost,pid_measurement, pid_recipe, pid_material);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insertProductForRecipe(pNombre VARCHAR(100))
RETURNS INT AS $$
DECLARE
l_id INTEGER :=0;
BEGIN
    INSERT INTO finished_product (name_product) VALUES (pNombre);
    SELECT id_finished_product INTO l_id FROM finished_product ORDER BY id_finished_product DESC LIMIT 1;
    RETURN l_id AS id_finished_product;
END;$$
LANGUAGE PLPGSQL;

-- PP --
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

-- RC --
--PROCEDIMIENTOS REGISTRO DE COBROS

--Funcion para calcular la fecha final

CREATE OR REPLACE FUNCTION calcular_fecha_fin(IN pfecha DATE, IN plazo INT) RETURNS DATE 
LANGUAGE 'plpgsql'
AS $$
DECLARE
	lfecha_fin DATE;                 
BEGIN
	 lfecha_fin = pfecha::date+ concat(plazo, 'month')::interval;
	return lfecha_fin;
END
$$;

--CALCULAR CUOTA MENSUAL

CREATE OR REPLACE FUNCTION calcular_cuota_mensual(IN pmonto real, IN pplazo INT, IN pinteres REAL)
RETURNS REAL 
LANGUAGE 'plpgsql'
AS $$
DECLARE
	lcuota_mensual real:=0; 
	linteres real:=0;
BEGIN
	 linteres = pinteres /100;
	 lcuota_mensual = pmonto  * (power((1+linteres), pplazo) * linteres)/(power((1+linteres), pplazo) -1);
	 return round (lcuota_mensual::DECIMAL, 2);
END
$$;

--Funcion para poder obtener el monto de una venta.

CREATE OR REPLACE FUNCTION sale_amount_loan(IN pcod_sale VARCHAR)
RETURNS REAL
LANGUAGE 'plpgsql'
AS $$
	DECLARE
		lid INTEGER :=0;
		lamount REAL:=0;
	BEGIN
		lid = id_sale FROM sale WHERE num_invoice = pcod_sale;
		lamount= total from sale WHERE id_sale = lid;
		RETURN lamount;
	END
$$;

--TRIGGER PARA AGREGAR UN PLAN DE PAGO DESPUES DE AGREGAR UN PRESTAMO.
CREATE OR REPLACE FUNCTION plan_pago_fun()
RETURNS TRIGGER 
AS $$
DECLARE
	lid_loan INTEGER:=0;
	lamount REAL:=0; 
	linitial_date DATE;
	ltime_frame INT:=0;
	linterestpercentage REAL:=0;
    i    INTEGER := 1;  
	linteres REAL:=0;
	lcuota_mensual REAL:=0;
	lfecha_cuota DATE;
	lfecha_fin DATE;
	lcapital REAL:=0;
	linteresmonto REAL:=0;
	lfinal_balance REAL:=0;
BEGIN
     lid_loan = MAX(loan_id) FROM loan;
	 lamount = amount FROM loan WHERE loan_id = lid_loan;
	 ltime_frame = time_frame FROM loan WHERE loan_id = lid_loan;
	 linterestpercentage = percentage_interest FROM loan WHERE loan_id = lid_loan;
	 linitial_date = initial_date FROM loan WHERE loan_id = lid_loan; 
	 linteres = linterestpercentage /100;
	 lcuota_mensual = lamount  * (power((1+linteres), ltime_frame) * linteres)/(power((1+linteres), ltime_frame) -1);

	lfecha_cuota = linitial_date::date+ concat(1, 'month')::interval;
	
	 WHILE i <= ltime_frame LOOP
		 
		 linteresmonto= lamount * linteres;
		 lcapital = lcuota_mensual - linteresmonto;
	     lfinal_balance = ROUND(AVG(lamount-lcapital)::numeric,2);
	        
			INSERT INTO loan_pending_fee (initial_balance, fee, required_date, final_balance, loan_pending_fee_state, loan_id)
			VALUES (lamount, lcuota_mensual, lfecha_cuota, lfinal_balance, 0, lid_loan);
			i := i + 1;
			lamount:= lamount-lcapital;
			lfecha_cuota = linitial_date::date + concat(i, 'month')::interval;
	
	 END LOOP;
   
   RETURN NULL;
END  
$$ LANGUAGE plpgsql;

CREATE TRIGGER plan_pago_2 AFTER INSERT ON loan EXECUTE PROCEDURE plan_pago_fun();

--FUNCIÓN PARA CALCULAR SI ALGÚN PRESTAMO TIENE MORA.

CREATE OR REPLACE FUNCTION add_late_fee_function() RETURNS INTEGER
LANGUAGE 'plpgsql'
AS $$
DECLARE
	lid_loan_late INTEGER :=0;
	i record;
	j record;

	lamount REAL:=0;
	lid_loan INTEGER :=0;
	linterest_late_fee REAL:=0; 
	linterv DATE;
	
	lcount INTEGER:= 0;
	--para las letras random
	result VARCHAR := '';
	chars varchar[] := '{A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z}';
	l integer := 0;
	--finletras random
	
	cursos CURSOR FOR SELECT loan_id, loan_fee, amount, percentage_late_fee, id_sale from loan;
	curso2 CURSOR (loan_idd integer) FOR SELECT fee, required_date, loan_pending_fee_state, loan_pending_fee_id FROM loan_pending_fee WHERE loan_id = loan_idd;
	
	BEGIN 
		lid_loan_late = max(loan_late_fee_id) from loan_late_fee;
		lcount = COUNT(loan_late_fee_id)from loan_late_fee;
		for l in 1..2 loop
           	result := result || chars[1+random()*(array_length(chars, 1)-1)];
              	end loop;
			
	  FOR i IN cursos LOOP
		OPEN curso2(loan_idd:=i.loan_id);
		LOOP FETCH curso2 INTO j;
			IF lcount=0 THEN
			   lid_loan_late = 2;
			ELSE
			   lid_loan_late := lid_loan_late+1;
			END IF;

			lid_loan_late = max(loan_late_fee_id) from loan_late_fee;
			linterv = j.required_date + '1 day'::interval;
			IF linterv <= now() and j.loan_pending_fee_state = 0 THEN
			lamount= j.fee;
			linterest_late_fee = lamount * (i.percentage_late_fee/100);

	     			IF NOT EXISTS(SELECT loan_pending_fee_id from loan_late_fee WHERE loan_pending_fee_id = j.loan_pending_fee_id) THEN 
				
	     			INSERT INTO loan_late_fee (late_fee_num, total, loan_late_fee_state, loan_pending_fee_id)
	     			VALUES (CONCAT(result, lid_loan_late),linterest_late_fee,  0, j.loan_pending_fee_id);
		
	    			UPDATE loan_pending_fee SET loan_pending_fee_state = 1 WHERE loan_pending_fee_id =j.loan_pending_fee_id;
				
	     			UPDATE loan SET loan_state = 2 WHERE loan_id = i.loan_id;
		    	
       	     			END IF;
				
			END IF;
		     UPDATE loan_pending_fee SET loan_id = i.loan_id WHERE loan_pending_fee_id = j.loan_pending_fee_id;	
		  EXIT WHEN NOT FOUND;
		END LOOP;
		
	      CLOSE curso2;
	     UPDATE loan SET id_sale = i.id_sale WHERE loan_id= i.loan_id;
	 END LOOP;
   RETURN NULL;
END
$$;

--PROCEDIMIENTO PARA AGREGAR UN PRÉSTAMO 

CREATE OR REPLACE FUNCTION insert_loan(IN pamount REAL, IN pi_date DATE, IN pf_date DATE,
				       IN pp_interest REAL, IN pp_late_fee REAL, IN ptframe INTEGER, 
				       IN pfee REAL, IN pcustomer_id INTEGER, IN pnum_sale VARCHAR)
RETURNS BOOLEAN
LANGUAGE 'plpgsql'
AS $$
	DECLARE
	result VARCHAR := '';
	chars varchar[] := '{A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z}';
        i integer := 0;
        lcod_venta VARCHAR :='';
	lid_loan INTEGER:=0;
	lcount INTEGER:=0;
	lid_sale INTEGER:=0;
	
	BEGIN
		lid_loan = max(loan_id) from loan;
		lcount = COUNT(loan_id) from loan;
		lid_sale = id_sale from sale WHERE num_invoice = pnum_sale;
		for i in 1..2 loop
            	result := result || chars[1+random()*(array_length(chars, 1)-1)];
            	end loop;
		IF lcount=0 THEN
			lid_loan = 1;
		ELSE 
			lid_loan = lid_loan-1;
		END IF;
  		    
		INSERT INTO loan(loan_number, amount, initial_date, final_date, percentage_interest,
				 percentage_late_fee, time_frame, loan_state, loan_fee, id_customer, id_sale)
				 VALUES(CONCAT(result, lid_loan), pamount, pi_date, pf_date, pp_interest,
				 pp_late_fee, ptframe, 0, pfee, pcustomer_id, lid_sale);
		RETURN TRUE;
	END 
$$;




-- ----------------------- BUSCAR PRÉSTAMO
--MOSTRAR TODOS LOS PRESTAMOS
CREATE OR REPLACE FUNCTION search_loan()
RETURNS TABLE (loan_number VARCHAR, customer_name TEXT, i_date DATE, f_date DATE, 
	       l_fee REAL, lp_interest REAL, lp_late_fee REAL, l_amount REAL, 
               lloan_state INTEGER)
AS $$
BEGIN 
	RETURN QUERY SELECT l.loan_number, CONCAT(cu.first_name, ' ', cu.last_name),
	l.initial_date, l.final_date, l.loan_fee, l.percentage_interest, l.percentage_late_fee,
	l.amount, l.loan_state FROM loan l INNER JOIN customer cu ON l.id_customer = cu.customer_id;
END; $$  
LANGUAGE 'plpgsql';

--BUSCAR POR Número de préstamo.

create or replace function filter_number_fee(n_fee varchar)
returns  TABLE (loan_number VARCHAR, customer_name TEXT, i_date DATE, f_date DATE, 
			   l_fee REAL, lp_interest REAL, lp_late_fee REAL, l_amount REAL, 
			   lloan_state INTEGER) as $$
declare 
	reg RECORD;
begin
	 RETURN QUERY SELECT l.loan_number, CONCAT(cu.first_name, ' ', cu.last_name),
	l.initial_date, l.final_date, l.loan_fee, l.percentage_interest, l.percentage_late_fee,
	l.amount, l.loan_state FROM loan l INNER JOIN customer cu ON l.id_customer = cu.customer_id
	WHERE l.loan_number LIKE CONCAT('%',n_fee, '%');

end $$
language plpgsql;


---filtro préstamo por cliente
create or replace function filter_customer(customer varchar(11)) 
returns  TABLE (loan_number VARCHAR, customer_name TEXT, i_date DATE, f_date DATE, 
			   l_fee REAL, lp_interest REAL, lp_late_fee REAL, l_amount REAL, 
			   lloan_state INTEGER) as $$
begin
	 RETURN QUERY SELECT l.loan_number, CONCAT(cu.first_name, ' ', cu.last_name),
	l.initial_date, l.final_date, l.loan_fee, l.percentage_interest, l.percentage_late_fee,
	l.amount, l.loan_state FROM loan l INNER JOIN customer cu ON l.id_customer = cu.customer_id

where CONCAT(cu.first_name, ' ', cu.last_name) LIKE ('%' || customer || '%');
end $$
	
language plpgsql;


---filtro préstamo por monto total

create or replace function filter_amount(amount_total real) 
returns  TABLE (loan_number VARCHAR, customer_name TEXT, i_date DATE, f_date DATE, 
			   l_fee REAL, lp_interest REAL, lp_late_fee REAL, l_amount REAL, 
			   lloan_state INTEGER) as $$
begin
	 RETURN QUERY SELECT l.loan_number, CONCAT(cu.first_name, ' ', cu.last_name),
	l.initial_date, l.final_date, l.loan_fee, l.percentage_interest, l.percentage_late_fee,
	l.amount, l.loan_state FROM loan l INNER JOIN customer cu ON l.id_customer = cu.customer_id
     where cast(l.amount as varchar) like '%'||amount_total||'%';
end $$
	
language plpgsql;

---filtro préstamo por estado
create or replace function filter_status_fee(status real) 
returns  TABLE (loan_number VARCHAR, customer_name TEXT, i_date DATE, f_date DATE, 
			   l_fee REAL, lp_interest REAL, lp_late_fee REAL, l_amount REAL, 
			   lloan_state INTEGER) as $$
begin
	RETURN QUERY SELECT l.loan_number, CONCAT(cu.first_name, ' ', cu.last_name),
	l.initial_date, l.final_date, l.loan_fee, l.percentage_interest, l.percentage_late_fee,
	l.amount, l.loan_state FROM loan l INNER JOIN customer cu ON l.id_customer = cu.customer_id
	
where l.loan_state = status;
end $$
language plpgsql;

--Consultas de cuotas pendientes.

CREATE OR REPLACE FUNCTION search_payment_plan_by_cod_pending(IN pnum_pending INTEGER) 
RETURNS TABLE(i_balance REAL, lfee REAL, r_date DATE, l_late_fee REAL, f_balance REAL, n_sale VARCHAR, n_loan VARCHAR,
			  cod_cu INTEGER, pending_fee INTEGER, cu_name text)
AS $$
BEGIN
    RETURN QUERY SELECT pf.initial_balance, pf.fee, pf.required_date, lf.total, pf.final_balance, sa.num_invoice,
		lo.loan_number,lo.id_customer, pf.loan_pending_fee_state, CONCAT(cu.first_name, ' ', cu.last_name) "Customer Namee" FROM loan lo
		INNER JOIN loan_pending_fee pf ON lo.loan_id = pf.loan_id
		INNER JOIN customer cu ON cu.customer_id = lo.id_customer
		INNER JOIN sale sa ON sa.id_sale = lo.id_sale
		LEFT JOIN loan_late_fee lf ON lf.loan_pending_fee_id = pf.loan_pending_fee_id
		WHERE pf.loan_pending_fee_id = pnum_pending;
	  END; $$  
LANGUAGE 'plpgsql';

--BUSCAR LA CUOTA que corresponde para realizar un pago.


CREATE OR REPLACE FUNCTION search_payment_fee(IN pnum_loan VARCHAR)
  RETURNS SETOF INTEGER AS
$func$
DECLARE
    j record;
	lid_loan INTEGER:=0;
	curso CURSOR (loan_idd integer) FOR SELECT loan_pending_fee_state, loan_pending_fee_id FROM loan_pending_fee WHERE loan_id = loan_idd;

BEGIN   
	lid_loan = loan_id FROM loan WHERE loan_number = pnum_loan;
        OPEN curso(loan_idd:=lid_loan);
	LOOP
	FETCH  FROM curso INTO j ;
     
	 EXIT WHEN NOT FOUND;
	  IF j.loan_pending_fee_state = 1 OR j.loan_pending_fee_state = 0 THEN
          RETURN next j.loan_pending_fee_id;               
          END IF;
      END LOOP;
   CLOSE curso;
END
$func$  LANGUAGE plpgsql STABLE;


--AGREGAR LOS PAGOS

CREATE OR REPLACE FUNCTION add_payment(IN ppayment_d DATE, IN pamount REAL, IN ppending_num INTEGER)
RETURNS BOOLEAN
LANGUAGE 'plpgsql'
AS $$	
	DECLARE
		lid_pending_fee INTEGER:=0;
		lid_loan_payment INTEGER :=0;
		ld_required DATE;
		lamount_required REAL:=0;
		lfee_payment REAL:=0;
		lcount INTEGER:=0;
		lloan_id INTEGER :=0;
		result VARCHAR := '';
		chars varchar[] := '{A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z}';
		l integer := 0;
		lfee REAL:=0;
		lpaid_late REAL := 0;
	BEGIN
			
			ld_required = required_date FROM loan_pending_fee WHERE loan_pending_fee_id = ppending_num;
			lloan_id = loan_id FROM loan_pending_fee WHERE loan_pending_fee_id = ppending_num;
			lid_loan_payment = max(loan_payment_id) from loan_payment;
			lcount = COUNT(loan_payment_id)from loan_payment;
			for l in 1..2 loop
         	        result := result || chars[1+random()*(array_length(chars, 1)-1)];
                        end loop;
			 
			IF lcount=0 THEN
				lid_loan_payment = 1;
		        ELSE 
		 		lid_loan_payment = lid_loan_payment-1;
			END IF;

				lamount_required = ROUND(AVG(fee)::numeric,2) FROM loan_pending_fee WHERE loan_pending_fee_id = ppending_num;
		
				INSERT INTO loan_payment(payment_date, paid_amount, loan_pending_fee_id, num_payment_fee)
			        VALUES(ppayment_d, lamount_required, ppending_num, CONCAT(result, lid_loan_payment));
					
				UPDATE loan SET loan_state = 1 WHERE loan_id = lloan_id;
				UPDATE loan_pending_fee SET loan_pending_fee_state = 2 WHERE loan_pending_fee_id =ppending_num;
				
	RETURN TRUE;
	END
$$;

-- AGREGAR LA ACTUALIZACION DE UN MORA SI ESTA SE PAGA.

CREATE OR REPLACE FUNCTION add_payment_late_fee(IN ppayment_d DATE, IN pamount REAL, IN ppending_num INTEGER)
RETURNS BOOLEAN
LANGUAGE 'plpgsql'
AS $$	
	DECLARE
		
		lloan_id INTEGER :=0;
	BEGIN
		
		lloan_id = loan_id FROM loan_pending_fee WHERE loan_pending_fee_id = ppending_num;
			
		UPDATE loan SET loan_state = 1 WHERE loan_id = lloan_id;
				 
		UPDATE loan_late_fee SET late_fee_payment_date = ppayment_d, paid_amount = pamount,
					 loan_late_fee_state = 1 WHERE loan_pending_fee_id =ppending_num; 
						
		
	RETURN TRUE;
	END
$$;

--MOSTRAR EL PLAN DE PAGO 

CREATE OR REPLACE FUNCTION search_payment_plan(IN pnum_loan VARCHAR) 
RETURNS TABLE(i_balance REAL, lfee REAL, r_date DATE, l_late_fee REAL, f_balance REAL, n_sale VARCHAR, n_loan VARCHAR,
			  cod_cu INTEGER, pending_fee INTEGER, cu_name text, id_pending_fee INTEGER)
AS $$
	DECLARE 
		lloan_id INTEGER :=0;
	BEGIN
		lloan_id = loan_id FROM loan WHERE loan_number = pnum_loan;
    		RETURN QUERY SELECT pf.initial_balance, pf.fee, pf.required_date, lf.total, pf.final_balance, sa.num_invoice,
		lo.loan_number,lo.id_customer, pf.loan_pending_fee_state, CONCAT(cu.first_name, ' ', cu.last_name) "Customer Namee",
		pf.loan_pending_fee_id
		FROM loan lo
		INNER JOIN loan_pending_fee pf ON lo.loan_id = pf.loan_id
		INNER JOIN customer cu ON cu.customer_id = lo.id_customer
		INNER JOIN sale sa ON sa.id_sale = lo.id_sale
		LEFT JOIN loan_late_fee lf ON lf.loan_pending_fee_id = pf.loan_pending_fee_id
		WHERE pf.loan_id = lloan_id ORDER BY pf.initial_balance DESC;
	  END;
$$  
LANGUAGE 'plpgsql';

--VT--

CREATE OR REPLACE FUNCTION getCustomersByLastName(plast_name varchar) 
RETURNS TABLE(id integer, Nombre character varying, Apellido character varying, Direccion character varying, Movil character varying, Telefono character varying, DUI character varying, NIT character varying, NRC character varying, Correo character varying)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
             select * from customer
             where customer.last_name like '%' || plast_name || '%';
    END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION getCustomersByFirstName(pfirst_name varchar) 
RETURNS TABLE(id integer, Nombre character varying, Apellido character varying, Direccion character varying, Movil character varying, Telefono character varying, DUI character varying, NIT character varying, NRC character varying, Correo character varying)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
             select * from customer
             where customer.first_name like '%' || pfirst_name || '%';
    END;
$$ LANGUAGE plpgsql;
CREATE OR REPLACE FUNCTION getCustomersById(pid integer) 
RETURNS TABLE(id integer, Nombre character varying, Apellido character varying, Direccion character varying, Movil character varying, Telefono character varying, DUI character varying, NIT character varying, NRC character varying, Correo character varying)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
             select * from customer
             where customer.customer_id=pid;
    END;
$$ LANGUAGE plpgsql;
-- drop FUNCTION getCustomers
--2
CREATE OR REPLACE FUNCTION getOrders(pcustomer_id INTEGER) 
RETURNS TABLE(Fecha date,Total real,Estado integer,Cantidad integer,Precio_Unitario real)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
             select order_prod_date,order_production.total,order_state,quantity,unit_price 
             from order_production 
             inner join order_production_detail on order_production_detail.order_production_id=order_production.order_production_id
             where order_production.customer_id=pcustomer_id and order_production.order_state=1;
    END;
$$ LANGUAGE plpgsql;
-- drop FUNCTION getOrders;
--3
--4
CREATE OR REPLACE FUNCTION getAllOrders(pcustomer_id INTEGER) 
RETURNS TABLE(Fecha date,Total real,Estado integer,Cantidad integer,Precio_Unitario real)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
             select order_prod_date,order_production.total,order_state,quantity,unit_price 
             from order_production 
             inner join order_production_detail on order_production_detail.order_production_id=order_production.order_production_id
             where order_production.customer_id=pcustomer_id;
    END;
$$ LANGUAGE plpgsql;
--5
CREATE OR REPLACE FUNCTION getOrderById(pOrderId INTEGER) 
RETURNS TABLE(Fecha date,Total real,Estado integer,Cantidad integer,Precio_Unitario real)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
             select order_prod_date,order_production.total,order_state,quantity,unit_price 
             from order_production 
             inner join order_production_detail on order_production_detail.order_production_id=order_production.order_production_id
             where order_production.order_production_id=pOrderId;
    END;
$$ LANGUAGE plpgsql;
CREATE OR REPLACE FUNCTION getOrderByDate(pOrderDate varchar) 
RETURNS TABLE(Fecha date,Total real,Estado integer,Cantidad integer,Precio_Unitario real)
 AS $$
    DECLARE
        lorderdate date;
    BEGIN
        lorderdate := (select pOrderDate::date);
         RETURN QUERY
             
             select order_prod_date,order_production.total,order_state,quantity,unit_price 
             from order_production 
             inner join order_production_detail on order_production_detail.order_production_id=order_production.order_production_id
             where order_production.order_prod_date=lorderdate;
    END;
$$ LANGUAGE plpgsql;
--6
CREATE OR REPLACE PROCEDURE insertIntoLog(pid_sale integer,porder_production_id integer,pstatus integer) 
 AS $$
    DECLARE
     
    BEGIN
         INSERT INTO product_log(pid_sale,porder_production,pupdate_time,pstatus)
         values(pid_sale,porder_production_id,pstatus);
    END;
$$ LANGUAGE plpgsql;

--
--insert sale
CREATE OR REPLACE PROCEDURE insertIntoSale(pnum_invoice varchar,pstatus_sale integer,pdate_sale varchar,pship_sale varchar, pdocument_type int,
                                           psub_total real, ptax real, ptotal real, pidcustomer int) 
 AS $$
    DECLARE
        
    BEGIN
         INSERT INTO sale(num_invoice,status_sale,date_sale, ship_date, document_type, 
                                sub_total, tax, total, id_customer)
         values(pnum_invoice,pstatus_sale,pdate_sale,pship_sale,pdocument_type,
               psub_total,ptax,ptotal,pidcustomer);
    END;
$$ LANGUAGE plpgsql;

--7
CREATE OR REPLACE FUNCTION getProducts() 
RETURNS TABLE(ID integer,Nombre character varying,Unidades_Stock integer,Precio_unitario real,cantidad integer,discontinuado integer,fecha_manufactura date,Precio_final real)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
            SELECT * 
            FROM finished_product
            GROUP BY id_finished_product,manufacture_date;
    END;
$$ LANGUAGE plpgsql;
--8
CREATE OR REPLACE PROCEDURE UpdateOrder(pid_order integer,pstatus integer) 
 AS $$
    DECLARE
        
    BEGIN
         update order_production set order_state = pstatus where order_production.order_production_id=pid_order;
    END;
$$ LANGUAGE plpgsql;

-- OP --

CREATE OR REPLACE FUNCTION updateM (in precipe_id int, in pquantity int)
RETURNS VARCHAR(30)
LANGUAGE plpgsql
AS $$
DECLARE
cursorM cursor FOR SELECT id_material , quantity from supply_for_recipe where id_recipe = precipe_id;
 record_up record;
 BEGIN
  Open cursorM;
 LOOP
 FETCH cursorM INTO record_up;
 EXIT WHEN NOT FOUND;
 UPDATE materials SET units_in_stock = units_in_stock - record_up.quantity * pquantity where material_id = record_up.id_material ;
 END LOOP;
 Close cursorM;
 RETURN 'se ha actualizado con exito';
 END;
$$
CREATE OR REPLACE FUNCTION validacion(in precipe_id int, in pquantity int)
RETURNS INTEGER
LANGUAGE plpgsql
AS $$
DECLARE 
 cursorMaterials cursor FOR SELECT id_material , quantity from supply_for_recipe where id_recipe = precipe_id;
 recordM record;
 lcant_mat INT:=0;
 lcant_mat_in_stock INT:=0;
 lcant_mat_for_recipe INT :=0;
 lcant_material INT:=0;
 BEGIN
  SELECT count(id_material) INTO lcant_mat_for_recipe from supply_for_recipe where id_recipe = precipe_id;
 Open cursorMaterials;
 LOOP
 FETCH cursorMaterials INTO recordM;
 EXIT WHEN NOT FOUND;
 SELECT ceiling(recordM.quantity*pquantity) ,units_in_stock INTO lcant_mat , lcant_mat_in_stock from materials where material_id = recordM.id_material;
 IF(lcant_mat <= lcant_mat_in_stock)THEN
 lcant_material = lcant_material + 1;
 END IF;
 END LOOP;
 Close cursorMaterials;
  IF(lcant_material = lcant_mat_for_recipe) THEN
  RETURN 0;
  ELSE
  RETURN 1;
  END IF;
 END;
 $$

