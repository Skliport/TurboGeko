CREATE TABLE suppliers(
	supplier_id SERIAL PRIMARY KEY,
	company_name VARCHAR(60) NOT NULL,
	contact_name VARCHAR(60) NOT NULL,
	contact_title VARCHAR(60) NOT NULL,
	addres VARCHAR(70) NOT NULL,
	city VARCHAR(25) NOT NULL,
	region VARCHAR(20) NOT NULL,
	postal_code VARCHAR(15) NOT NULL,
	country VARCHAR(15) NOT NULL,
	phone VARCHAR(15) NOT NULL,
	fax VARCHAR(25) NOT NULL,
	homepage VARCHAR(30) NOT NULL
);

CREATE TABLE material(
	material_id SERIAL PRIMARY KEY,
	supplier_id INT NOT NULL,
	material_name VARCHAR(25) NOT NULL,
	units_in_stock INT NOT NULL,
	material_measure VARCHAR(25) NOT NULL,
	unit_price REAL NOT NULL,
	number_lot INT NOT NULL,
	purchase_date DATE NOT NULL,
	expiration_date DATE NOT NULL,
	discontinued INT NOT NULL
);

CREATE TABLE purchase(
	purchase_id SERIAL PRIMARY KEY,
	employee_id INT NOT NULL,
	purchase_date DATE NOT NULL,
	observations TEXT NOT NULL,
	purchase_status INT NOT NULL,
	payment_terms INT NOT NULL,
	subtotal REAL NOT NULL,
	iva REAL NOT NULL,
	total REAL NOT NULL
);

CREATE TABLE employees(
	employee_id SERIAL PRIMARY KEY,
	first_name VARCHAR(20)
);

CREATE TABLE purchase_details(
	purchase_id INT NOT NULL,
	material_id INT NOT NULL
);

CREATE TABLE purchase_old(
	purchase_id INT NOT NULL,
	employee_id INT NOT NULL,
	purchase_date DATE NOT NULL,
	observations TEXT NOT NULL,
	purchase_status INT NOT NULL,
	payment_terms INT NOT NULL,
	subtotal REAL NOT NULL,
	iva REAL NOT NULL,
	total REAL NOT NULL,
	modification_date DATE NOT NULL,
	modification_employee INT NOT NULL
);

CREATE TABLE purchase_details_old(
	purchase_id INT NOT NULL,
	material_id INT NOT NULL,
        modification_date DATE NOT NULL,
	modification_employee INT NOT NULL
);

CREATE TABLE material_old(
	material_id INT NOT NULL,
	supplier_id INT NOT NULL,
	material_name VARCHAR(25) NOT NULL,
	units_in_stock INT NOT NULL,
	material_measure VARCHAR(25) NOT NULL,
	unit_price REAL NOT NULL,
	number_lot INT NOT NULL,
	purchase_date DATE NOT NULL,
	expiration_date DATE NOT NULL,
	discontinued INT NOT NULL,
    modification_date DATE NOT NULL,
	modification_employee INT NOT NULL
);

CREATE TABLE suppliers_old(
	supplier_id INT NOT NULL,
	company_name VARCHAR(60) NOT NULL,
	contact_name VARCHAR(60) NOT NULL,
	contact_title VARCHAR(60) NOT NULL,
	addres VARCHAR(70) NOT NULL,
	city VARCHAR(25) NOT NULL,
	region VARCHAR(20) NOT NULL,
	postal_code VARCHAR(15) NOT NULL,
	country VARCHAR(15) NOT NULL,
	phone VARCHAR(15) NOT NULL,
	fax VARCHAR(25) NOT NULL,
	homepage VARCHAR(30) NOT NULL,
	modification_date DATE NOT NULL,
	modification_employee INT NOT NULL
);

ALTER TABLE purchase
ADD CONSTRAINT fk_purchase_employees FOREIGN KEY(employee_id) REFERENCES employees(employee_id);

ALTER TABLE material
ADD CONSTRAINT fk_material_supplier FOREIGN KEY(supplier_id) REFERENCES suppliers (supplier_id);