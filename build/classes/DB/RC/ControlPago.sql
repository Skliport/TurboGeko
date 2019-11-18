CREATE TABLE loan(
    loan_id SERIAL,
    loan_number INTEGER NOT NULL,
    amount REAL NOT NULL,
    start_date DATE NOT NULL,
    final_date DATE NOT NULL,
    percentage_interest REAL NOT NULL,
    percentage_delay REAL NOT NULL time_frame INT NOT NULL,
    loan_state INT NOT NULL,
    fee REAL NOT NULL,
    id_customer INT NOT NULL id_sale INT NOT NULL
);

CREATE TABLE pending_fee(
    pending_fee_id SERIAL,
    initial_balance REAL NOT NULL,
    fee REAL NOT NULL,
    --CUOTA PUEDE QUEDAR A LA MITAD..
    required_date DATE NOT NULL,
    final_balance REAL NOT NULL,
    state_pending_fee INT NOT NULL,
    loan_id INT NOT NULL
);

CREATE TABLE fee(
    fee_id SERIAL,
    payment_date DATE NOT NULL,
    paid_amount REAL NOT NULL,
    pending_fee_id INT NOT NULL
);

CREATE TABLE delay(
    delay_id SERIAL,
    delay_date DATE NOT NULL,
    delay_amount REAL NOT NULL,
    delay_state INT NOT NULL,
    pending_fee_id INT NOT NULL,
);

CREATE TABLE advance(
    advance_id SERIAL,
    amount_advance REAL NOT NULL,
    advance_date DATE NOT NULL,
    loan_id INT NOT NULL
);

ALTER TABLE
    ONLY loan
ADD
    CONSTRAINT pk_loan PRIMARY KEY (loan_id);

ALTER TABLE
    ONLY pending_fee
ADD
    CONSTRAINT pk_pending_fee PRIMARY KEY (pending_fee_id);

ALTER TABLE
    ONLY fee
ADD
    CONSTRAINT pk_fee PRIMARY KEY (fee_id);

ALTER TABLE
    ONLY delay
ADD
    CONSTRAINT pk_delay PRIMARY KEY (delay_id);

ALTER TABLE
    ONLY advance
ADD
    CONSTRAINT pk_advance PRIMARY KEY (advance_id);

ALTER TABLE
    ONLY loan
ADD
    CONSTRAINT fk_loan_customer FOREIGN KEY (id_customer) REFERENCES customer;

ALTER TABLE
    ONLY pending_fee
ADD
    CONSTRAINT fk_pending_fee_loan FOREIGN KEY (loan_id) REFERENCES loan;

ALTER TABLE
    ONLY fee
ADD
    CONSTRAINT fk_fee_pending_fee FOREIGN KEY (pending_fee_id) REFERENCES pending_fee;

ALTER TABLE
    ONLY delay
ADD
    CONSTRAINT fk_delay_pending_fee FOREIGN KEY (pending_fee) REFERENCES pending_fee;

ALTER TABLE
    ONLY delay
ADD
    CONSTRAINT fk_delay_pending_fee FOREIGN KEY (pending_fee) REFERENCES pending_fee;

ALTER TABLE
    ONLY advance
ADD
    CONSTRAINT fk_advance_loan FOREIGN KEY (loan_id) REFERENCES loan;

ALTER TABLE
    ONLY loan
ADD
    CONSTRAINT fK_loan_sale FOREIGN KEY (id_sale) REFERENCES loan;