create table material(
	id_material SERIAL PRIMARY KEY,
	material_name varchar(60),
	quantity_per_unit int,
	discountinued int,
	supplier_id int(foranea)
);

create table sale(
	id_sale SERIAL PRIMARY KEY,
	num_invoice int not null,
	status_sale int not null,
	date_sale date not null,
	ship_date date,
	sub_total real not null,
	tax real not null,
	observation varchar(200),
	total real not null,
	id_customer int not null
);


create table sale_detail(
	id_sale_detail SERIAL PRIMARY KEY,
	id_product int not null,
	id_sale int not null,
	unit_price real,
	quantity int,
	discount real,
	amount real
);


create table purchase(
	id_buy SERIAL PRIMARY KEY,
	num_invoice int not null,
	date_buy date not null,
	subtotal real not null,
	iva real not null,
	total real not null
);


create table purchase_detail(
	id_buy_detail SERIAL PRIMARY KEY,
	id_material int not null,
	id_buy int not null,
	quantity int not null,
	discount real not null,
	unit_price real not null,
	amount real not null
);


create table finished_product(
	id_finished_product SERIAL PRIMARY KEY,
	units_int_stock int not null,
	unit_price int not null
);


Create table payment_employee(
	id_payment_employee SERIAL PRIMARY KEY,
	id_employee int not null,
	payment_date date not null,
	salary real not null,
	isss real not null,
	afp real not null,
	notes varchar(60)
);

CREATE TABLE IF NOT EXISTS costos_fijos(
	id_costos_fijos serial primary key,
	nombre_costo varchar(30),
	monto real

) Create table if not exists loss_gain(
	id_loss_gain serial primary key,
	date_loss_gain date,
	monto real

) CREATE TABLE suppliers(
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
	fax VARCHAR(25) NOT NULL,
	nrc VARCHAR(25),
	nit VARCHAR(25)
);