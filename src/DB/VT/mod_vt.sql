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
--TABLA DE RECETA
CREATE TABLE recipe (id_recipe SERIAL PRIMARY KEY, 
cod_recipe varchar(20), 
unit_cost REAL, sale_price REAL, raw_income REAL NULL,
id_product INT);

CREATE TABLE supply_for_recipe(id_supply SERIAL PRIMARY KEY, 
quantity REAL, unit_cost REAL, 
total_cost REAL,id_measurement int, id_recipe int
id_material int);

CREATE TABLE measurement_description(id_measurement SERIAL PRIMARY KEY,
description varchar(60));

ALTER TABLE supply_for_recipe ADD CONSTRAINT fk_supply_recipe FOREIGN KEY (id_recipe)
REFERENCES recipe (id_recipe);

ALTER TABLE supply_for_recipe ADD CONSTRAINT fk_supply_measurement FOREIGN KEY (id_measurement)
REFERENCES measurement_description (id_measurement);

ALTER TABLE supply_for_recipe ADD CONSTRAINT fk_supply_material FOREIGN KEY (id_material)
REFERENCES material (id_material);

ALTER TABLE recipe ADD CONSTRAINT fk_recipe_product FOREIGN KEY(id_product)
REFERENCES product (id_product);
--FIN DE TABLA RECETA
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
