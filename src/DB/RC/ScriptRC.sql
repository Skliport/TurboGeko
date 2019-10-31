CREATE TABLE loan(
loan_id SERIAL,
start_date DATE NOT NULL,
final_date DATE NOT NULL,
perecentage_interests REAL NOT NULL,
percentage_delay REAL NOT NULL,
timeframe INT NOT NULL,
state_loan INT NOT NULL,
id_customer INT NOT NULL
);

CREATE TABLE pending_fee(
pending_fee_id SERIAL,
initial_balance REAL NOT NULL,
required_amount REAL NOT NULL,
required_date DATE NOT  NULL,
interest_amount REAL NOT NULL,
principal_amount REAL NOT NULL,
delay_amount REAL NOT NULL,
final_balance REAL NOT NULL,
pending_fee_state INT NOT NULL,
loan_id INT NOT NULL
);

CREATE TABLE fee(
fee_id SERIAL,
payment_day DATE NOT NULL,
paid_amount REAL NOT NULL,
advance_id INT NULL,
pending_fee_id INT NOT NULL
);

CREATE TABLE advance(
advance_id SERIAL,
advance_amount REAL NOT NULL,
advance_type INT NOT NULL
);


ALTER TABLE ONLY loan
    ADD CONSTRAINT pk_loan PRIMARY KEY (loan_id);
	
ALTER TABLE ONLY pending_fee
    ADD CONSTRAINT pk_pending_fee PRIMARY KEY (pending_fee_id);
	
ALTER TABLE ONLY fee
    ADD CONSTRAINT pk_fee PRIMARY KEY (fee_id);
	
ALTER TABLE ONLY advance
    ADD CONSTRAINT pk_advance PRIMARY KEY (advance_id);
	
	
ALTER TABLE ONLY loan
    ADD CONSTRAINT fk_loan_customer FOREIGN KEY (id_customer) REFERENCES customer;

ALTER TABLE ONLY pending_fee
    ADD CONSTRAINT fk_pending_fee_loan FOREIGN KEY (loan_id) REFERENCES loan;
	
ALTER TABLE ONLY fee
    ADD CONSTRAINT fk_fee_pending_fee FOREIGN KEY (pending_fee_id) REFERENCES pending_fee;
	
ALTER TABLE ONLY fee
    ADD CONSTRAINT fk_advance_fee FOREIGN KEY (advance_id) REFERENCES advance;
