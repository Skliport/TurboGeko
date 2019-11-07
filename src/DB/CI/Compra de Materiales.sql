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

CREATE TABLE purchase(
	purchase_id SERIAL PRIMARY KEY,
	purchase_date DATE NOT NULL,
	purchase_status INT NOT NULL,
	observations varchar(100),
	installment INT NOT NULL,
	months INT,
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

CREATE TABLE purchase_log(
	purchase_id INT NOT NULL,
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


ALTER TABLE materials
ADD CONSTRAINT fk_material_supplier FOREIGN KEY(supplier_id) REFERENCES suppliers (supplier_id);

ALTER TABLE purchase_details
ADD CONSTRAINT fk_purchase_details_material FOREIGN KEY(material_id) REFERENCES materials (material_id);

ALTER TABLE purchase_details
ADD CONSTRAINT fk_purchase_details_purchase FOREIGN KEY(purchase_id) REFERENCES purchase (purchase_id);

----solo estan con fk las propias tablas las de historial solo se clonara pero sin dependecias----

----Creacion de funciones disparadores----

CREATE FUNCTION purchase_log_funtion() RETURNS TRIGGER
AS
$$
BEGIN
	INSERT INTO purchase_log VALUES(OLD.purchase_id,OLD.purchase_date,
									OLD.purchase_status,OLD.observations,
									OLD.delay, OLD.installment,OLD.days,
									OLD.subtotal,OLD.iva,OLD.total,NOW());
	RETURN NEW;
END;
$$
LANGUAGE plpgsql;

---Asignacion de disparadores---

CREATE TRIGGER purchase_log_update BEFORE UPDATE ON purchase
FOR EACH ROW
EXECUTE PROCEDURE purchase_log_funtion();


---generados---

insert into suppliers values(Default,'Alejandro','ninguno','Todos',
							 'San sebas','balrking07@gmail.com','Santa Ana','Zona Ocidental',
							'5098','El Salvador','+50378698723','5005');
							
update suppliers set company_name = 'Ba', contact_name = ' s',contact_title= 'todo',address = 'Santa Ana',
email = 'barlkinyahoo', city = 'Santa Ana', region = 'Norte', postal_code = 'Ninguno',country = 'China', phone = '7356',
fax = '2' where supplier_id = 1

select * from suppliers
select * from suppliers_log
