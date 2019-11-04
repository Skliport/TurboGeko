CREATE TABLE IF NOT EXISTS employees(
	employee_id serial PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS purchase(
	purchase_id serial PRIMARY KEY,
	purchase_date DATE,
	observations text,
	material_id smallint,
	employee_id smallint,
	subtotal real,
	total real
);

CREATE TABLE IF NOT EXISTS purchase_details(
	purchase_id smallint,
	material_id smallint,
	observations text,
	discount real,
	unit_price real,
	discontinued integer
);

CREATE TABLE IF NOT EXISTS materials(
	material_id serial PRIMARY KEY,
	material_code smallint,
	material_name character varying(40),
	quantity_per_unit character varying(20),
	type character varying(15),
	unit_price real,
	date_discontinued date,
	company_name character varying(40),
	address_company character varying(60),
	discontinued integer
);


CREATE TABLE IF NOT EXISTS materials_old_date(
	material_id_old smallint,
	material_code smallint,
	material_name character varying(20),
	quantity_per_unit character varying(20),
	type character varying(15),
	unit_price real,
	date_discontinued date,
	modification_employee smallint,
	modification_date date
);

CREATE TABLE IF NOT EXISTS purchase_details_old_date(
	purchase_id_old smallint,
	material_id smallint,
	observations text,
	discount real,
	unit_price real,
	discontinued integer,
	modification_employee smallint,
	modification date
);

CREATE TABLE IF NOT EXISTS purchase_old_date(
	purchase_id_old_date smallint,
	purchase_date date,
	observations text,
	material_id smallint,
	employee_id smallint,
	subtotal real,
	total real,
	modification_employee smallint,
	modification_date date
);

ALTER TABLE purchase
ADD CONSTRAINT fk_purchase_employees FOREIGN KEY(employee_id) REFERENCES employees(employee_id);

ALTER TABLE purchase_details
ADD CONSTRAINT fk_purchase_details_purchase FOREIGN KEY(purchase_id) REFERENCES purchase(purchase_id);

ALTER TABLE purchase_details
ADD CONSTRAINT fk_purchase_details_materials FOREIGN KEY(material_id) REFERENCES materials(material_id);

ALTER TABLE materials_old_date
ADD CONSTRAINT fk_materials_old_date_materials FOREIGN KEY(material_id_old) REFERENCES materials(material_id);

ALTER TABLE materials_old_date
ADD CONSTRAINT fk_materials_old_date_employees FOREIGN KEY(modification_employee) REFERENCES employees(employee_id);

ALTER TABLE purchase_details_old_date
ADD CONSTRAINT fk_purchase_details_old_date_materials FOREIGN KEY(material_id) REFERENCES materials(material_id);

ALTER TABLE purchase_details_old_date
ADD CONSTRAINT fk_purchase_details_old_date_purchase FOREIGN KEY(purchase_id_old) REFERENCES purchase(purchase_id);

ALTER TABLE purchase_details_old_date
ADD CONSTRAINT fk_purchase_details_old_date_employees FOREIGN KEY(modification_employee) REFERENCES employees(employee_id);

ALTER TABLE purchase_old_date
ADD CONSTRAINT fk_purchase_old_date_employees FOREIGN KEY(modification_employee) REFERENCES employees(employee_id);

ALTER TABLE purchase_old_date
ADD CONSTRAINT fk_purchase_old_date_purchase FOREIGN KEY(purchase_id_old_date) REFERENCES purchase(purchase_id);
