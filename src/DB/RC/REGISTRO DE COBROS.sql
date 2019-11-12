-- REGISTRO DE COBROS

CREATE TABLE loan(
loan_id SERIAL,
amount REAL NOT NULL,
initial_date DATE NOT NULL,
final_date DATE NOT NULL,
percentage_interest REAL NOT NULL,
percentage_late_fee REAL NOT NULL,
time_frame INT NOT NULL,
loan_state INT NOT NULL, -- 0.Pending 1.Active 2.Completed 3.Late fee
loan_fee REAL NOT NULL,
id_customer INT NOT NULL,
id_sale INT NOT NULL
);

CREATE TABLE pending_fee(
pending_fee_id SERIAL,
initial_balance REAL NOT NULL,
fee REAL NOT NULL, 
required_date  DATE NOT NULL,
final_balance REAL NOT NULL,
pending_fee_state INT NOT NULL, --0.Pending 1.Completed 3.Late fee
loan_id INT NOT NULL
);

CREATE TABLE loan_payment(
loan_payment_id SERIAL,
payment_date DATE NOT NULL,
paid_amount REAL NOT NULL,
pending_fee_id INT NOT NULL
);

CREATE TABLE loan_late_fee(
loan_late_fee_id SERIAL,
total REAL NOT NULL,
loan_late_fee_state INT NOT NULL, --0. Pending 1.Completed
late_fee_payment_date DATE,
paid_amount REAL,
pending_fee_id INT NOT NULL
);

CREATE TABLE loan_payment_upfront(
loan_payment_upfront_id SERIAL,
paid_amount REAL NOT NULL,
payment_date DATE NOT NULL,
pending_fee_id INT NOT NULL
);
--LLAVES PRIMARIAS: REGISTRO DE COBROS
ALTER TABLE ONLY loan
ADD CONSTRAINT pk_loan PRIMARY KEY (loan_id);

ALTER TABLE ONLY pending_fee
ADD CONSTRAINT pk_pending_fee PRIMARY KEY (pending_fee_id);

ALTER TABLE ONLY loan_payment
ADD CONSTRAINT pk_loan_payment PRIMARY KEY (loan_payment_id);

ALTER TABLE ONLY loan_late_fee
ADD CONSTRAINT pk_loan_late_fee PRIMARY KEY (loan_late_fee_id);

ALTER TABLE ONLY loan_payment_upfront
ADD CONSTRAINT pk_loan_payment_upfront PRIMARY KEY (loan_payment_upfront_id);

--LLAVES FORANEAS: REGISTRO DE COBROS

ALTER TABLE ONLY loan
    ADD CONSTRAINT fk_loan_customer FOREIGN KEY (id_customer) REFERENCES customer;
	
ALTER TABLE ONLY loan
	ADD CONSTRAINT fK_loan_sale FOREIGN KEY (id_sale) REFERENCES loan;

ALTER TABLE ONLY pending_fee
    ADD CONSTRAINT fk_pending_fee_loan FOREIGN KEY (loan_id) REFERENCES loan;

ALTER TABLE ONLY loan_payment
    ADD CONSTRAINT fk_loan_payment_pending_fee FOREIGN KEY (pending_fee_id) REFERENCES pending_fee;

ALTER TABLE ONLY loan_late_fee
    ADD CONSTRAINT fk_loan_late_fee_pending_fee FOREIGN KEY (pending_fee_id) REFERENCES pending_fee;

ALTER TABLE ONLY loan_payment_upfront
	ADD CONSTRAINT fk_loan_payment_upfront_loan FOREIGN KEY (pending_fee_id) REFERENCES pending_fee;
	
--FIN REGISTRO DE COBROS 