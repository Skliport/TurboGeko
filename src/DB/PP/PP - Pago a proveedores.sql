/* Módulo PP: Pago a proveedores - Tablas, relaciones y discriminantes */
/* Magaña Urrutia, Juan Sebastian */ /* Santamaría Calderón, René Francisco */

--! Toda instancia de este módulo contendrá dependencias respecto al módulo CI: Compra.
-- Para realizar pruebas en este módulo de forma independiente es necesario crear una tabla
-- de prueba 'purchase_suppliers' con una llave primaria 'purchase_id'.

/* - - - - 1. Tablas - - - - */

-- Plan de pago --
CREATE TABLE purchase_payment_plan(
    purchase_payment_plan_id SERIAL PRIMARY KEY,
    purchase_id INT NOT NULL,
    payment_plan_status_id INT NOT NULL,
    total REAL NOT NULL, 
    amount_paid REAL NOT NULL, 
    remaining_amount REAL NOT NULL, 
    number_of_payments INT NOT NULL,
    late_fee_percentage_charge REAL NOT NULL
);

-- Cuota descrita en el plan de pago --
CREATE TABLE purchase_payment(
    purchase_payment_id SERIAL PRIMARY KEY,
    purchase_id INT NOT NULL,
    payment_status_id INT NOT NULL,
    total REAL NOT NULL,
    paid_amount REAL,
    due_date DATE NOT NULL,
    payment_date TIMESTAMP
);

-- Mora --
CREATE TABLE purchase_late_fee_payment(
    late_fee_payment_id SERIAL PRIMARY KEY,
    purchase_id INT NOT NULL,
    purchase_payment_id INT NOT NULL,
    late_fee_payment_status_id INT NOT NULL,
    total REAL NOT NULL,
    paid_amount REAL,
    payment_date TIMESTAMP
);

-- Avance de pago a última cuota pendiente disponible --
CREATE TABLE purchase_payment_upfront(
    purchase_payment_upfront_id SERIAL PRIMARY KEY,
    purchase_id INT NOT NULL,
    purchase_payment_id INT NOT NULL,
    paid_amount REAL NOT NULL,
    upfront_payment_date TIMESTAMP NOT NULL
);

-- Estado de plan de pago --
CREATE TABLE purchase_payment_plan_status(
    payment_plan_status_id SERIAL PRIMARY KEY,
    name VARCHAR (40) NOT NULL
);

-- Estado de pago a cuota --
CREATE TABLE purchase_payment_status(
    payment_status_id SERIAL PRIMARY KEY,
    name VARCHAR (40) NOT NULL
);

-- Estado de pago a mora --
CREATE TABLE purchase_late_fee_payment_status(
    late_fee_payment_status_id SERIAL PRIMARY KEY,
    name VARCHAR (40) NOT NULL
);

/* - - - - 2. Relaciones - - - - */

-- Purchase payment plan.
ALTER TABLE purchase_payment_plan
ADD CONSTRAINT fk_purchase_payment_plan_purchase 
FOREIGN KEY (purchase_id) REFERENCES purchase_suppliers (purchase_id),
ADD CONSTRAINT fk_purchase_payment_status 
FOREIGN KEY (payment_plan_status_id) REFERENCES purchase_payment_plan_status (payment_plan_status_id);

-- Purchase payment.
ALTER TABLE purchase_payment
ADD CONSTRAINT fk_purchase_payment_purchase 
FOREIGN KEY (purchase_id) REFERENCES purchase_suppliers (purchase_id),
ADD CONSTRAINT fk_purchase_payment_status 
FOREIGN KEY (payment_status_id) REFERENCES purchase_payment_status (payment_status_id);

-- Purchase late fee payment. 
ALTER TABLE purchase_late_fee_payment
ADD CONSTRAINT fk_purchase_late_fee_payment_purchase_payment 
FOREIGN KEY (purchase_payment_id) REFERENCES purchase_payment (purchase_payment_id),
ADD CONSTRAINT fk_purchase_late_fee_payment_purchase 
FOREIGN KEY (purchase_id) REFERENCES purchase_suppliers (purchase_id),
ADD CONSTRAINT fk_purchase_late_fee_payment_status 
FOREIGN KEY (late_fee_payment_status_id) REFERENCES purchase_late_fee_payment_status (late_fee_payment_status_id);

-- Purchase payment upfront.
ALTER TABLE purchase_payment_upfront
ADD CONSTRAINT fk_purchase_payment_upfront_purchase_payment 
FOREIGN KEY (purchase_payment_id) REFERENCES purchase_payment (purchase_payment_id),
ADD CONSTRAINT fk_purchase_payment_upfront_purchase 
FOREIGN KEY (purchase_id) REFERENCES purchase_suppliers (purchase_id);

/* - - - - 3. Discriminantes - - - - */

-- Purchase payment plan status.
INSERT INTO purchase_payment_plan_status (payment_plan_status_id, name)
VALUES (default, 'Pendiente'); 
INSERT INTO purchase_payment_plan_status (payment_plan_status_id, name)
VALUES (default, 'Cancelado'); 

-- Purchase payment status.
INSERT INTO purchase_payment_status (payment_status_id, name)
VALUES (default, 'Pendiente'); 
INSERT INTO purchase_payment_status (payment_status_id, name)
VALUES (default, 'Cancelado'); 
INSERT INTO purchase_payment_status (payment_status_id, name)
VALUES (default, 'En mora'); 

-- Purchase late fee payment status.
INSERT INTO purchase_late_fee_payment_status (late_fee_payment_status_id, name)
VALUES (default, 'Pendiente'); 
INSERT INTO purchase_late_fee_payment_status (late_fee_payment_status_id, name)
VALUES (default, 'Cancelado'); 