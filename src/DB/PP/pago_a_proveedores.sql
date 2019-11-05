CREATE TABLE purchase_payment_plan_status(
    payment_plan_status_id SERIAL PRIMARY KEY,
    name VARCHAR (40) NOT NULL
);

CREATE TABLE purchase_payment_plan(
    purchase_payment_plan_id SERIAL PRIMARY KEY,
    purchase_id INT NOT NULL,
    payment_plan_status_id INT NOT NULL,
    total REAL NOT NULL,
    amount_paid REAL NOT NULL,
    remaining_amount REAL NOT NULL,
    number_of_payments INT NOT NULL,
    payment_frequency_in_days INT NOT NULL,
    late_fee_percentage_charge REAL NOT NULL
);

ALTER TABLE
    purchase_payment_plan
ADD
    CONSTRAINT fk_purchase_payment_plan_purchase FOREIGN KEY (purchase_id) REFERENCES purchase (purchase_id),
ADD
    CONSTRAINT fk_purchase_payment_status FOREIGN KEY (payment_plan_status_id) REFERENCES purchase_payment_plan_status (payment_plan_status_id);

CREATE TABLE purchase_payment_status(
    payment_status_id SERIAL PRIMARY KEY,
    name VARCHAR (40) NOT NULL
);

CREATE TABLE purchase_payment(
    purchase_payment_id SERIAL PRIMARY KEY,
    purchase_id INT NOT NULL,
    payment_status_id INT NOT NULL,
    total REAL NOT NULL,
    paid_amount REAL,
    due_date DATE NOT NULL,
    payment_date DATE
);

ALTER TABLE
    purchase_payment
ADD
    CONSTRAINT fk_purchase_payment_purchase FOREIGN KEY (purchase_id) REFERENCES purchase (purchase_id),
ADD
    CONSTRAINT fk_purchase_payment_status FOREIGN KEY (payment_status_id) REFERENCES purchase_payment_status (payment_status_id);

CREATE TABLE purchase_late_fee_payment_status(
    late_fee_payment_status_id SERIAL PRIMARY KEY,
    name VARCHAR (40) NOT NULL
);

CREATE TABLE purchase_late_fee_payment(
    late_fee_payment_id SERIAL PRIMARY KEY,
    purchase_payment_id INT NOT NULL,
    late_fee_payment_status_id INT NOT NULL,
    total REAL NOT NULL,
    paid_amount REAL,
    payment_date DATE
);

ALTER TABLE
    purchase_late_fee_payment
ADD
    CONSTRAINT fk_purchase_late_fee_payment_purchase_payment FOREIGN KEY (purchase_payment_id) REFERENCES purchase_payment (purchase_payment_id),
ADD
    CONSTRAINT fk_purchase_late_fee_payment_status FOREIGN KEY (late_fee_payment_status_id) REFERENCES purchase_late_fee_payment_status (late_fee_payment_status_id);