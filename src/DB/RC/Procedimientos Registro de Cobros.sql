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

