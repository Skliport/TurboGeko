
-- OP Modulo
CREATE TABLE IF NOT EXISTS order_production(
	order_production_id serial NOT NULL,
	customer_id SMALLINT NOT NULL,
	order_prod_date date NOT NULL,
	total REAL,
	order_state int,
	CHECK(total > 0),
	CHECK(
		order_state >= 0
		and order_state <= 2
	)
);

CREATE TABLE IF NOT EXISTS order_production_detail(
	order_production_detail_id serial NOT NULL,
	id_recipe SMALLINT NOT NULL,
	quantity int NOT NULL,
	unit_price REAL NOT NULL,
	order_production_id SMALLINT,
	CHECK(quantity > 0),
	CHECK(unit_price > 0)
);

CREATE TABLE IF NOT EXISTS product_discarded(
	product_discarded_id serial NOT NULL,
	id_recipe SMALLINT NOT NULL,
	quantity INT NOT NULL,
	order_production_id SMALLINT NOT NULL,
	CHECK(quantity > 0)
);

CREATE TABLE IF NOT EXISTS cancel_order_production(
	cancel_order_production_id serial NOT NULL,
	order_production_id SMALLINT NOT NULL,
	reason varchar(500) NOT NULL,
	canceled_order_date date not null
);

--VT Modulo
CREATE TABLE customer(
	customer_id serial NOT NULL,
	first_name varchar(25) NOT NULL,
	last_name varchar(25) NOT NULL,
	address varchar(200) NOT NULL,
	mobile varchar(50) NOT NULL,
	telephone varchar (50) NOT NULL,
	dui varchar(20) NOT NULL,
	nit varchar(50) NOT NULL,
	nrc varchar(20),
	email varchar(150) NOT NULL
);

CREATE TABLE sale(
	id_sale serial NOT NULL,
	num_invoice varchar(11) NOT NULL,
	status_sale int NOT NULL,
	date_sale varchar(20) NOT NULL,
	ship_date varchar(20),
	document_type int NOT NULL,
	sub_total real NOT NULL,
	tax real NOT NULL,
	total real NOT NULL,
	observations varchar(200),
	id_customer int NOT NULL
);

CREATE TABLE sale_detail(
	id_saleDetail serial NOT NULL,
	discount real,
	amount real,
	id_sale int NOT NULL,
	id_finished_product int
);

CREATE TABLE product_log(
	id_product_log serial NOT NULL,
	id_sale int NOT NULL,
	order_production_id int NOT NULL,
	status int,
	update_time date
);

--EC Modulo

CREATE TABLE IF NOT EXISTS fixed_costs(
	id_fixed_costs serial primary key,
	cost_name varchar(30),
	tax real,
	subtotal real,
	total real
);

CREATE TABLE IF NOT EXISTS finished_product(
	id_finished_product SERIAL PRIMARY KEY,
	name_product VARCHAR(40),
	units_in_stock INT,
	unit_price REAL,
	quantity INT,
	discontinued INT,
	manufacture_date DATE,
	total REAL
);

-- MT Modulo
CREATE TABLE recipe (
	id_recipe SERIAL PRIMARY KEY,
	unit_cost REAL,
	sale_price REAL,
	id_product INT
);

CREATE TABLE supply_for_recipe(
	id_supply SERIAL PRIMARY KEY,
	quantity REAL,
	unit_cost REAL,
	total_cost REAL,
	id_measurement int,
	id_recipe int,
	id_material int
);

CREATE TABLE measurement_description(
	id_measurement SERIAL PRIMARY KEY,
	descrip varchar(60)
);

CREATE TABLE monthly_cost (
	id_cost SERIAL PRIMARY KEY,
	_cost REAL,
	cost_detail varchar(100),
	id_category_cost int NOT NULL
);

CREATE TABLE cost_category(
	id_cost_category INT PRIMARY KEY,
	category_description VARCHAR(200)
);

--CI Modulo
CREATE TABLE suppliers(
	supplier_id SERIAL PRIMARY KEY,
	company_name VARCHAR(60) NOT NULL,
	contact_name VARCHAR(60) NOT NULL,
	contact_title VARCHAR(60) NOT NULL,
	address VARCHAR(70) NOT NULL,
	email VARCHAR(30) NOT NULL,
	city VARCHAR(25) NOT NULL,
	region VARCHAR(20) NOT NULL,
	postal_code VARCHAR(15) NOT NULL,
	country VARCHAR(15) NOT NULL,
	phone VARCHAR(15) NOT NULL,
	fax VARCHAR(25),
	nrc VARCHAR(30) NOT NULL,
	nit VARCHAR(30) NOT NULL
);

CREATE TABLE materials(
	material_id SERIAL PRIMARY KEY,
	supplier_id INT NOT NULL,
	material_name VARCHAR(25) NOT NULL,
	units_in_stock INT NOT NULL,
	material_measure VARCHAR(25) NOT NULL,
	unit_price REAL NOT NULL,
	number_lot INT,
	expiration_date DATE,
	discontinued INT NOT NULL
);

CREATE TABLE purchase_suppliers(
	purchase_id SERIAL PRIMARY KEY,
	purchase_date DATE NOT NULL,
	purchase_status INT NOT NULL,
	observations varchar(100),
	subtotal REAL NOT NULL,
	iva REAL NOT NULL,
	total REAL NOT NULL
);

CREATE TABLE purchase_suppliers_details(
	purchase_suppliers_details serial PRIMARY KEY,
	purchase_id INT NOT NULL,
	material_id INT NOT NULL,
	quantity INT NOT NULL,
	unit_price REAL NOT NULL
);

CREATE TABLE purchase_suppliers_log(
	purchase_id SERIAL PRIMARY KEY,
	purchase_date DATE NOT NULL,
	purchase_status INT NOT NULL,
	observations varchar(100),
	subtotal REAL NOT NULL,
	iva REAL NOT NULL,
	total REAL NOT NULL,
	modification_date_hour TIMESTAMP NOT NULL
);

--RC
CREATE TABLE loan(
	loan_id SERIAL,
	loan_number varchar(10) NOT NULL,
	amount REAL NOT NULL,
	initial_date DATE NOT NULL,
	final_date DATE NOT NULL,
	percentage_interest REAL NOT NULL,
	percentage_late_fee REAL NOT NULL,
	time_frame INT NOT NULL,
	loan_state INT NOT NULL,
	loan_fee REAL NOT NULL,
	id_customer INT NOT NULL,
	id_sale INT NOT NULL
);

CREATE TABLE loan_pending_fee(
	loan_pending_fee_id SERIAL,
	initial_balance REAL NOT NULL,
	fee REAL NOT NULL,
	required_date DATE NOT NULL,
	final_balance REAL NOT NULL,
	loan_pending_fee_state INT NOT NULL,
	loan_id INT NOT NULL
);

CREATE TABLE loan_payment(
	loan_payment_id SERIAL,
	payment_date DATE NOT NULL,
	paid_amount REAL NOT NULL,
	loan_pending_fee_id INT NOT NULL,
	num_payment_fee varchar(10) unique not null
);

CREATE TABLE loan_late_fee(
	loan_late_fee_id SERIAL,
	total REAL NOT NULL,
	loan_late_fee_state INT NOT NULL,
	late_fee_payment_date DATE,
	paid_amount REAL,
	loan_pending_fee_id INT NOT NULL,
	late_fee_num varchar(10) unique not null
);

CREATE TABLE loan_payment_upfront(
	loan_payment_upfront_id SERIAL,
	paid_amount REAL NOT NULL,
	payment_date DATE NOT NULL,
	loan_pending_fee_id INT NOT NULL,
	num_payment_upfront varchar(10) unique not null
);

--PP Modulo
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

CREATE TABLE purchase_payment(
	purchase_payment_id SERIAL PRIMARY KEY,
	purchase_id INT NOT NULL,
	payment_status_id INT NOT NULL,
	total REAL NOT NULL,
	paid_amount REAL,
	due_date DATE NOT NULL,
	payment_date TIMESTAMP
);

CREATE TABLE purchase_late_fee_payment(
	late_fee_payment_id SERIAL PRIMARY KEY,
	purchase_id INT NOT NULL,
	purchase_payment_id INT NOT NULL,
	late_fee_payment_status_id INT NOT NULL,
	total REAL NOT NULL,
	paid_amount REAL,
	payment_date TIMESTAMP
);

CREATE TABLE purchase_payment_upfront(
	purchase_payment_upfront_id SERIAL PRIMARY KEY,
	purchase_id INT NOT NULL,
	purchase_payment_id INT NOT NULL,
	paid_amount REAL NOT NULL,
	upfront_payment_date TIMESTAMP NOT NULL
);

CREATE TABLE purchase_payment_plan_status(
	payment_plan_status_id SERIAL PRIMARY KEY,
	name VARCHAR (40) NOT NULL
);

CREATE TABLE purchase_payment_status(
	payment_status_id SERIAL PRIMARY KEY,
	name VARCHAR (40) NOT NULL
);

CREATE TABLE purchase_late_fee_payment_status(
	late_fee_payment_status_id SERIAL PRIMARY KEY,
	name VARCHAR (40) NOT NULL
);

--Alter PP
ALTER TABLE
	purchase_payment_plan
ADD
	CONSTRAINT fk_purchase_payment_plan_purchase FOREIGN KEY (purchase_id) REFERENCES purchase_suppliers (purchase_id),
ADD
	CONSTRAINT fk_purchase_payment_status FOREIGN KEY (payment_plan_status_id) REFERENCES purchase_payment_plan_status (payment_plan_status_id);

ALTER TABLE
	purchase_payment
ADD
	CONSTRAINT fk_purchase_payment_purchase FOREIGN KEY (purchase_id) REFERENCES purchase_suppliers (purchase_id),
ADD
	CONSTRAINT fk_purchase_payment_status FOREIGN KEY (payment_status_id) REFERENCES purchase_payment_status (payment_status_id);

ALTER TABLE
	purchase_late_fee_payment
ADD
	CONSTRAINT fk_purchase_late_fee_payment_purchase_payment FOREIGN KEY (purchase_payment_id) REFERENCES purchase_payment (purchase_payment_id),
ADD
	CONSTRAINT fk_purchase_late_fee_payment_purchase FOREIGN KEY (purchase_id) REFERENCES purchase_suppliers (purchase_id),
ADD
	CONSTRAINT fk_purchase_late_fee_payment_status FOREIGN KEY (late_fee_payment_status_id) REFERENCES purchase_late_fee_payment_status (late_fee_payment_status_id);

ALTER TABLE
	purchase_payment_upfront
ADD
	CONSTRAINT fk_purchase_payment_upfront_purchase_payment FOREIGN KEY (purchase_payment_id) REFERENCES purchase_payment (purchase_payment_id),
ADD
	CONSTRAINT fk_purchase_payment_upfront_purchase FOREIGN KEY (purchase_id) REFERENCES purchase_suppliers (purchase_id);

--Alter VT
ALTER TABLE
	ONLY customer
ADD
	CONSTRAINT pk_customer_id PRIMARY KEY (customer_id);

ALTER TABLE
	ONLY sale
ADD
	CONSTRAINT pk_id_sale PRIMARY KEY (id_sale);

ALTER TABLE
	ONLY sale_detail
ADD
	CONSTRAINT pk_id_saleDetail PRIMARY KEY (id_saleDetail);

ALTER TABLE
	ONLY product_log
ADD
	CONSTRAINT pk_id_product_log PRIMARY KEY (id_product_log);

ALTER TABLE
	ONLY sale
ADD
	CONSTRAINT fk_sale_customer FOREIGN KEY (id_customer) REFERENCES customer;

ALTER TABLE
	ONLY sale_detail
ADD
	CONSTRAINT fk_saleDetail_ FOREIGN KEY (id_sale) REFERENCES sale;

ALTER TABLE
	ONLY sale_detail
ADD
	CONSTRAINT fk_finished_product_sale FOREIGN KEY (id_finished_product) REFERENCES finished_product;

ALTER TABLE
	ONLY product_log
ADD
	CONSTRAINT fk_product_log_sale FOREIGN KEY (id_sale) REFERENCES sale;

ALTER TABLE
	order_production
ADD
	CONSTRAINT pk_order_production PRIMARY KEY(order_production_id);

ALTER TABLE
	ONLY product_log
ADD
	CONSTRAINT fk_order_production_log_sale FOREIGN KEY (order_production_id) REFERENCES order_production;
--Alter RC
ALTER TABLE
	ONLY loan
ADD
	CONSTRAINT pk_loan PRIMARY KEY (loan_id);

ALTER TABLE
	ONLY loan_pending_fee
ADD
	CONSTRAINT pk_loan_pending_fee PRIMARY KEY (loan_pending_fee_id);

ALTER TABLE
	ONLY loan_payment
ADD
	CONSTRAINT pk_loan_payment PRIMARY KEY (loan_payment_id);

ALTER TABLE
	ONLY loan_late_fee
ADD
	CONSTRAINT pk_loan_late_fee PRIMARY KEY (loan_late_fee_id);

ALTER TABLE
	ONLY loan_payment_upfront
ADD
	CONSTRAINT pk_loan_payment_upfront PRIMARY KEY (loan_payment_upfront_id);

ALTER TABLE ONLY loan
ADD CONSTRAINT loan_number_unique UNIQUE (loan_number);

ALTER TABLE
	ONLY loan
ADD
	CONSTRAINT fk_loan_customer FOREIGN KEY (id_customer) REFERENCES customer;


ALTER TABLE
	ONLY loan
ADD
	CONSTRAINT fK_loan_sale FOREIGN KEY (id_sale) REFERENCES sale;

ALTER TABLE
	ONLY loan_pending_fee
ADD
	CONSTRAINT fk_loan_pending_fee_loan FOREIGN KEY (loan_id) REFERENCES loan;

ALTER TABLE
	ONLY loan_payment
ADD
	CONSTRAINT fk_loan_payment_loan_pending_fee FOREIGN KEY (loan_pending_fee_id) REFERENCES loan_pending_fee;

ALTER TABLE
	ONLY loan_late_fee
ADD
	CONSTRAINT fk_loan_late_fee_loan_pending_fee FOREIGN KEY (loan_pending_fee_id) REFERENCES loan_pending_fee;

ALTER TABLE
	ONLY loan_payment_upfront
ADD
	CONSTRAINT fk_loan_payment_upfront_loan_pending_fee FOREIGN KEY (loan_pending_fee_id) REFERENCES loan_pending_fee;

--FIN REGISTRO DE COBROS
--Alter CI
ALTER TABLE
	materials
ADD
	CONSTRAINT fk_material_suppliers FOREIGN KEY(supplier_id) REFERENCES suppliers (supplier_id);

ALTER TABLE
	purchase_suppliers_details
ADD
	CONSTRAINT fk_purchase_suppliers_details_material FOREIGN KEY(material_id) REFERENCES materials (material_id);

ALTER TABLE
	purchase_suppliers_details
ADD
	CONSTRAINT fk_purchase_suppliers_details_purchase FOREIGN KEY(purchase_id) REFERENCES purchase_suppliers(purchase_id);

--Alter MT
ALTER TABLE
	supply_for_recipe
ADD
	CONSTRAINT fk_supply_recipe FOREIGN KEY (id_recipe) REFERENCES recipe (id_recipe);

ALTER TABLE
	supply_for_recipe
ADD
	CONSTRAINT fk_supply_measurement FOREIGN KEY (id_measurement) REFERENCES measurement_description (id_measurement);

ALTER TABLE
	supply_for_recipe
ADD
	CONSTRAINT fk_supply_material FOREIGN KEY (id_material) REFERENCES materials (material_id);

ALTER TABLE
	recipe
ADD
	CONSTRAINT fk_recipe_product FOREIGN KEY(id_product) REFERENCES finished_product (id_finished_product);

ALTER TABLE
	monthly_cost
ADD
	CONSTRAINT fk_monthly_category FOREIGN KEY (id_category_cost) REFERENCES cost_category(id_cost_category);

--Alter EC


--Alter OP
ALTER TABLE
	ONLY order_production_detail
ADD
	CONSTRAINT fk_order_production_detail FOREIGN KEY (id_recipe) REFERENCES recipe;

ALTER TABLE
	ONLY product_discarded
ADD
	CONSTRAINT fk_product_discarded FOREIGN KEY (id_recipe) REFERENCES recipe;


ALTER TABLE
	order_production_detail
ADD
	CONSTRAINT pk_order_production_detial PRIMARY KEY(order_production_detail_id);

ALTER TABLE
	product_discarded
ADD
	CONSTRAINT pk_product_discarded PRIMARY KEY(product_discarded_id);

ALTER TABLE
	order_production_detail
ADD
	CONSTRAINT fk_order_production_detail_order_production FOREIGN KEY(order_production_id) REFERENCES order_production(order_production_id);

ALTER TABLE
	order_production
ADD
	CONSTRAINT fk_order_production_customer FOREIGN KEY(customer_id) REFERENCES customer(customer_id);

ALTER TABLE
	product_discarded
ADD
	CONSTRAINT fk_order_production_detail_product FOREIGN KEY(order_production_id) REFERENCES order_production(order_production_id);

ALTER TABLE
	order_production
ADD
	CONSTRAINT fk_cancel_order_production_order_production FOREIGN key (order_production_id) REFERENCES order_production(order_production_id);
