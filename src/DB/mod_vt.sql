CREATE TABLE customer(
	customer_id serial NOT NULL,
	first_nameCustomer varchar(25) NOT NULL,
	last_nameCustomer varchar(25) NOT NULL,
	address varchar(200) NOT NULL,
	mobile varchar(50) NOT NULL,
	telephone varchar (50) NOT NULL,
	dui_customer varchar(20) NOT NULL,
	nit_customer varchar(50) NOT NULL,
	nrc_customer varchar(20) ,
	email_customer varchar(150) NOT NULL
);

CREATE TABLE final_product(
	final_product_id serial NOT NULL,
	code_final_product varchar(20) NOT NULL,
	name_final_product varchar(100) NOT NULL,
	units_in_stock int NOT NULL,
	unit_price real NOT NULL
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
	unit_price real NOT NULL,
	quantity int NOT NULL,
	discount real,
	amount real,
	id_sale int NOT NULL,
	product_id int NOT NULL
);

ALTER TABLE ONLY customer
ADD CONSTRAINT pk_customer_id PRIMARY KEY (customer_id);

ALTER TABLE ONLY final_product
ADD CONSTRAINT pk_final_product_id PRIMARY KEY (final_product_id);

ALTER TABLE ONLY orders
ADD CONSTRAINT pk_id_sale PRIMARY KEY (id_sale);

ALTER TABLE ONLY order_detail
ADD CONSTRAINT pk_id_saleDetail PRIMARY KEY (id_saleDetail);

ALTER TABLE ONLY orders
ADD CONSTRAINT fk_customer_id FOREIGN KEY (id_customer) REFERENCES customer;

ALTER TABLE ONLY order_detail
ADD CONSTRAINT fk_order_id FOREIGN KEY (order_id) REFERENCES orders;

ALTER TABLE ONLY order_detail
ADD CONSTRAINT fk_final_product_id FOREIGN KEY (final_product_id) REFERENCES final_product;
