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
	fax VARCHAR(25) NOT NULL
);

CREATE TABLE materials(
	material_id SERIAL PRIMARY KEY,
	supplier_id INT NOT NULL,
	material_name VARCHAR(25) NOT NULL,
	units_in_stock INT NOT NULL,
	material_measure VARCHAR(25) NOT NULL,
	unit_price REAL NOT NULL,
	number_lot INT NOT NULL,
	expiration_date DATE NOT NULL,
	discontinued INT NOT NULL
);

CREATE TABLE purchase(
	purchase_id SERIAL PRIMARY KEY,
	employee_id INT NOT NULL,
	purchase_date DATE NOT NULL,
	purchase_status INT NOT NULL,
	observations TEXT NOT NULL,
	delay REAL NOT NULL,
	installment INT NOT NULL,
	days INT NOT NULL,
	subtotal REAL NOT NULL,
	iva REAL NOT NULL,
	total REAL NOT NULL
);

CREATE TABLE purchase_details(
	purchase_id INT NOT NULL,
	material_id INT NOT NULL,
	quantity INT NOT NULL,
	unit_price REAL NOT NULL
);

CREATE TABLE employees(
	employee_id SERIAL PRIMARY KEY,
	name VARCHAR(20)
);

CREATE TABLE suppliers_log(
	supplier_id INT NOT NULL,
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
	modification_date_hour TIMESTAMP NOT NULL
);

CREATE TABLE materials_log(
	material_id INT NOT NULL,
	supplier_id INT NOT NULL,
	material_name VARCHAR(25) NOT NULL,
	units_in_stock INT NOT NULL,
	material_measure VARCHAR(25) NOT NULL,
	unit_price REAL NOT NULL,
	number_lot INT NOT NULL,
	expiration_date DATE NOT NULL,
	discontinued INT NOT NULL,
	modification_date_hour TIMESTAMP NOT NULL
);

CREATE TABLE purchase_log(
	purchase_id INT NOT NULL,
	employee_id INT NOT NULL,
	purchase_date DATE NOT NULL,
	purchase_status INT NOT NULL,
	observations TEXT NOT NULL,
	delay REAL NOT NULL,
	installment INT NOT NULL,
	days INT NOT NULL,
	subtotal REAL NOT NULL,
	iva REAL NOT NULL,
	total REAL NOT NULL,
	modification_date_hour TIMESTAMP NOT NULL
);

CREATE TABLE purchase_details_log(
	purchase_id INT NOT NULL,
	material_id INT NOT NULL,
	quantity INT NOT NULL,
	unit_price REAL NOT NULL,
	modification_date_hour TIMESTAMP NOT NULL
);

ALTER TABLE materials
ADD CONSTRAINT fk_material_supplier FOREIGN KEY(supplier_id) REFERENCES suppliers (supplier_id);

ALTER TABLE purchase_details
ADD CONSTRAINT fk_purchase_details_material FOREIGN KEY(material_id) REFERENCES materials (material_id);

ALTER TABLE purchase_details
ADD CONSTRAINT fk_purchase_details_purchase FOREIGN KEY(purchase_id) REFERENCES purchase (purchase_id);

ALTER TABLE purchase
ADD CONSTRAINT fk_purchase_employees FOREIGN KEY(employee_id) REFERENCES employees(employee_id);
----solo estan con fk las propias tablas las de historial solo se clonara pero sin dependecias----

----Creacion de funciones disparadores----
CREATE FUNCTION suppliers_log_funtion() RETURNS TRIGGER
AS
$$
BEGIN
	INSERT INTO suppliers_log VALUES(OLD.supplier_id,OLD.company_name,OLD.contact_name,OLD.contact_title,
									OLD.address,OLD.email,
									OLD.city, OLD.region,OLD.postal_code,
									OLD.country,OLD.phone,OLD.fax,NOW());
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE FUNCTION materials_log_funtion() RETURNS TRIGGER
AS
$$
BEGIN
	INSERT INTO materials_log VALUES(OLD.material_id,OLD.supplier_id,OLD.material_name,
									OLD.units_in_stock,OLD.material_measure,
									OLD.unit_price, OLD.number_lot,OLD.expiration_date,
									OLD.discontinued,NOW());
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE FUNCTION purchase_log_funtion() RETURNS TRIGGER
AS
$$
BEGIN
	INSERT INTO purchase_log VALUES(OLD.purchase_id,OLD.employee_id ,OLD.purchase_date,
									OLD.purchase_status,OLD.observations,
									OLD.delay, OLD.installment,OLD.days,
									OLD.subtotal,OLD.iva,OLD.total,NOW());
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

CREATE FUNCTION purchase_details_log_funtion() RETURNS TRIGGER
AS
$$
BEGIN
	INSERT INTO purchase_details_log VALUES(OLD.purchase_id,OLD.material_id ,OLD.quantity,
									OLD.purchase_status,OLD.observations,
									OLD.unit_price,NOW());
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

---Asignacion de disparadores---
CREATE TRIGGER suppliers_log_update BEFORE UPDATE ON suppliers
FOR EACH ROW
EXECUTE PROCEDURE suppliers_log_funtion();

CREATE TRIGGER materials_log_update BEFORE UPDATE ON materials
FOR EACH ROW
EXECUTE PROCEDURE materials_log_funtion();

CREATE TRIGGER purchase_log_update BEFORE UPDATE ON purchase
FOR EACH ROW
EXECUTE PROCEDURE purchase_log_funtion();

CREATE TRIGGER purchase_log_update BEFORE UPDATE ON purchase_details
FOR EACH ROW
EXECUTE PROCEDURE purchase_details_log_funtion();
