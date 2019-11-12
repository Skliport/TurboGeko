CREATE TABLE customer(
	customer_id serial NOT NULL,
	first_name varchar(25) NOT NULL,
	last_name varchar(25) NOT NULL,
	address varchar(200) NOT NULL,
	mobile varchar(50) NOT NULL,
	telephone varchar (50) NOT NULL,
	dui varchar(20) NOT NULL,
	nit varchar(50) NOT NULL,
	nrc varchar(20) ,
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
	update_time date
);


ALTER TABLE ONLY customer
ADD CONSTRAINT pk_customer_id PRIMARY KEY (customer_id);

ALTER TABLE ONLY sale
ADD CONSTRAINT pk_id_sale PRIMARY KEY (id_sale);

ALTER TABLE ONLY sale_detail
ADD CONSTRAINT pk_id_saleDetail PRIMARY KEY (id_saleDetail);

ALTER TABLE ONLY product_log
ADD CONSTRAINT pk_id_product_log PRIMARY KEY (id_product_log);

ALTER TABLE ONLY sale
ADD CONSTRAINT fk_sale_customer FOREIGN KEY (id_customer) REFERENCES customer;

ALTER TABLE ONLY sale_detail
ADD CONSTRAINT fk_saleDetail_ FOREIGN KEY (id_sale) REFERENCES sale;

ALTER TABLE ONLY sale_detail
ADD CONSTRAINT fk_finished_product_sale FOREIGN KEY (id_finished_product) REFERENCES finished_product;

ALTER TABLE ONLY product_log
ADD CONSTRAINT fk_product_log_sale FOREIGN KEY (id_sale) REFERENCES sale;

ALTER TABLE ONLY product_log
ADD CONSTRAINT fk_order_production_log_sale FOREIGN KEY (order_production_id) REFERENCES order_production;
