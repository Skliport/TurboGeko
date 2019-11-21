/*

    QUERY CREADOR POR ROBERTO MORENO Y ALEJANDRO ROMERO
    FECHA DE CREACION 05/11/2019 (dd,mm,yyyy)
    FECHA DE ULTIMA EDICIÓN 12/11/2019 (dd,mm,yyyy) POR ALEJANDRO ROMERO
    QUE SE CAMBIO?
    SE ACTUALIZARON NOMBRES, ESTABAN CONFUSOS Y NO ESPECIFICABAN SU PROPÓSITO
		
*/

--------------------------------------------------
--------------------------------------------------
--------------------------------------------------
------------CREACIONES DE TABLAS------------------
--------------------------------------------------
--------------------------------------------------
--------------------------------------------------
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

ALTER TABLE materials
ADD CONSTRAINT fk_material_suppliers FOREIGN KEY(supplier_id) REFERENCES suppliers (supplier_id);

ALTER TABLE purchase_suppliers_details
ADD CONSTRAINT fk_purchase_suppliers_details_material FOREIGN KEY(material_id) REFERENCES materials (material_id);

ALTER TABLE purchase_suppliers_details
ADD CONSTRAINT fk_purchase_suppliers_details_purchase FOREIGN KEY(purchase_id) REFERENCES purchase_suppliers(purchase_id);

----solo estan con fk las propias tablas las de historial solo se clonara pero sin dependecias----

----Creacion de funciones disparadores----

CREATE FUNCTION purchase_suppliers_log_funtion() RETURNS TRIGGER
AS
$$
BEGIN
    INSERT INTO purchase_suppliers_log VALUES(OLD.purchase_id,OLD.purchase_date,
        OLD.purchase_status,OLD.observations,
        OLD.subtotal,OLD.iva,OLD.total,NOW());
    RETURN NEW;
END;
$$
LANGUAGE plpgsql;

---Asignacion de disparadores---

CREATE TRIGGER purchase_suppliers_log_update BEFORE UPDATE ON purchase_suppliers
FOR EACH ROW
EXECUTE PROCEDURE purchase_suppliers_log_funtion();

--------------------------------------------------
--------------------------------------------------
--------------------------------------------------
------------------FUNCIONES-----------------------
--------------------------------------------------
--------------------------------------------------
--------------------------------------------------

--------------------------------------------------
---------Encapsulamientos de inserciones----------
--------------------------------------------------
CREATE OR REPLACE FUNCTION insertSupplier(company_name VARCHAR(60),contact_name VARCHAR(60),
    contact_title VARCHAR(60),address VARCHAR(70),
    email VARCHAR(30),city VARCHAR(25),region VARCHAR(20),
    postal_code VARCHAR(15),country VARCHAR(15),
    phone VARCHAR(15),fax VARCHAR(25),nrc VARCHAR(30),
    nit VARCHAR(30)) RETURNS BOOLEAN
AS
$$
DECLARE
registro RECORD;
cur CURSOR FOR SELECT suppliers.company_name FROM suppliers;
BEGIN
FOR registro IN cur LOOP
    IF(registro.company_name = company_name) THEN
--Validamos la existencia de un proveedor
	RETURN false;
    END IF;
END LOOP;
INSERT INTO suppliers VALUES
    (Default,company_name,contact_name,contact_title,
    address,email,city,region,postal_code,country,
    phone,fax,nrc,nit);
RETURN true;
END;
$$
LANGUAGE plpgsql;
-------------------------------------------------
-------------------------------------------------
-------------------------------------------------

CREATE OR REPLACE FUNCTION insertMaterial(supplier_id int, material_name VARCHAR(25),units_in_stock int,
    material_measure VARCHAR(25),unit_price REAL,number_lot INT,
    expiration_date DATE,discontinued INT) RETURNS VOID
AS
$$
BEGIN
INSERT INTO materials VALUES
    (default,supplier_id,material_name,units_in_stock
     ,material_measure,unit_price,number_lot,expiration_date
     ,discontinued);
END;
$$
LANGUAGE plpgsql;

-------------------------------------------------
-------------------------------------------------
-------------------------------------------------

CREATE OR REPLACE FUNCTION insertPurchaseSuppliersDetails(purchase_id INT,material_id INT,
    quantity INT,unit_price REAL) RETURNS VOID
AS
$$
BEGIN
INSERT INTO purchase_suppliers_details VALUES
    (DEFAULT,purchase_id,material_id,quantity,unit_price);
END;
$$
LANGUAGE plpgsql;

-------------------------------------------------
-------------------------------------------------
-------------------------------------------------

CREATE OR REPLACE FUNCTION insertPurchaseSuppliers(purchase_date DATE,purchase_status INT,
    observations varchar(100),subtotal REAL,iva REAL,total REAL) RETURNS VOID
AS
$$
BEGIN
INSERT INTO purchase_suppliers VALUES
    (default,purchase_date,purchase_status,observations,subtotal,iva,total);
END;
$$
LANGUAGE plpgsql;



--------------------------------------------------
------------------Busquedas-----------------------
--------------------------------------------------

CREATE OR REPLACE FUNCTION searchSuppliers(pcompany_name VARCHAR(60),pcontact_name VARCHAR(60),
    pcontact_title VARCHAR(60),paddress VARCHAR(70),
    pemail VARCHAR(30),pcity VARCHAR(25),pregion VARCHAR(20),
    ppostal_code VARCHAR(15),pcountry VARCHAR(15),
    pphone VARCHAR(15),pfax VARCHAR(25),pnrc VARCHAR(30),
    pnit VARCHAR(30))
	RETURNS TABLE(supplier_id INT,company_name VARCHAR(60),contact_name VARCHAR(60),
    contact_title VARCHAR(60),address VARCHAR(70),
    email VARCHAR(30),city VARCHAR(25),region VARCHAR(20),
    postal_code VARCHAR(15),country VARCHAR(15),
    phone VARCHAR(15),fax VARCHAR(25),nrc VARCHAR(30),
    nit VARCHAR(30))
AS
$$
DECLARE
lcompany_name VARCHAR(62):= concat('%',pcompany_name,'%');
lcontact_name VARCHAR(62):= concat('%',pcontact_name,'%');
lcontact_title VARCHAR(62):= concat('%',pcontact_title,'%');
laddress VARCHAR(72):= concat('%',paddress,'%');
lemail VARCHAR(32):= concat('%',pemail,'%');
lcity VARCHAR(27):= concat('%',pcity,'%');
lregion VARCHAR(22):= concat('%',pregion,'%');
lpostal_code VARCHAR(17):= concat('%',ppostal_code,'%');
lcountry VARCHAR(17):= concat('%',pcountry,'%');
lphone VARCHAR(17):= concat('%',pphone,'%');
lfax VARCHAR(27):= concat('%',pfax,'%');
lnrc VARCHAR(32):= concat('%',pnrc,'%');
lnit VARCHAR(32):= concat('%',pnit,'%');
BEGIN
RETURN QUERY SELECT * FROM suppliers WHERE suppliers.company_name LIKE lcompany_name AND
suppliers.contact_name LIKE lcontact_name AND
suppliers.contact_title LIKE lcontact_title AND
suppliers.address LIKE laddress AND
suppliers.email LIKE lemail AND
suppliers.city LIKE lcity AND
suppliers.region LIKE lregion AND
suppliers.postal_code LIKE lpostal_code AND
suppliers.country LIKE lcountry AND
suppliers.phone LIKE lphone AND
suppliers.fax LIKE lfax AND
suppliers.nrc LIKE lnrc AND
suppliers.nit LIKE lnit;
END;
$$
LANGUAGE plpgsql;

-------------------------------------------------
-------------------------------------------------
-------------------------------------------------

CREATE OR REPLACE FUNCTION searchSuppliers(psupplier_id INT)
	RETURNS TABLE(supplier_id INT,company_name VARCHAR(60),contact_name VARCHAR(60),
    contact_title VARCHAR(60),address VARCHAR(70),
    email VARCHAR(30),city VARCHAR(25),region VARCHAR(20),
    postal_code VARCHAR(15),country VARCHAR(15),
    phone VARCHAR(15),fax VARCHAR(25),nrc VARCHAR(30),
    nit VARCHAR(30))
AS
$$
DECLARE
lsupplier_id INT:= psupplier_id;
BEGIN
RETURN QUERY SELECT * FROM suppliers WHERE suppliers.supplier_id = lsupplier_id;
END;
$$
LANGUAGE plpgsql;
-------------------------------------------------
----------------Actualizaciones------------------
-------------------------------------------------
CREATE OR REPLACE FUNCTION updateSupplier(psupplier_id INT,pcompany_name VARCHAR(60),pcontact_name VARCHAR(60),
    pcontact_title VARCHAR(60),paddress VARCHAR(70),
    pemail VARCHAR(30),pcity VARCHAR(25),pregion VARCHAR(20),
    ppostal_code VARCHAR(15),pcountry VARCHAR(15),
    pphone VARCHAR(15),pfax VARCHAR(25),pnrc VARCHAR(30),
    pnit VARCHAR(30))
	RETURNS void
AS
$$
DECLARE
lcompany_name VARCHAR(60):= pcompany_name;
lcontact_name VARCHAR(60):= pcontact_name;
lcontact_title VARCHAR(60):= pcontact_title;
laddress VARCHAR(70):= paddress;
lemail VARCHAR(30):= pemail;
lcity VARCHAR(25):= pcity;
lregion VARCHAR(20):= pregion;
lpostal_code VARCHAR(15):= ppostal_code;
lcountry VARCHAR(15):= pcountry;
lphone VARCHAR(15):= pphone;
lfax VARCHAR(25):= pfax;
lnrc VARCHAR(30):= pnrc;
lnit VARCHAR(30):= pnit;
lsupplier_id INT := psupplier_id;
BEGIN
UPDATE suppliers set company_name= lcompany_name,contact_name= lcontact_name,
contact_title= lcontact_title,email= lemail,
address= laddress,city= lcity,
region= lregion,postal_code= lpostal_code,
country= lcountry,phone= lphone,
fax = lfax,nrc= lnrc,nit= lnit
where suppliers.supplier_id = lsupplier_id;
END;
$$
LANGUAGE plpgsql;

--------------------------------------------------
-------------------------------------------------- 
--------------------------------------------------

--------------------------------------------------
--------------------------------------------------
-------------------Vistas-------------------------
--------------------------------------------------
--------------------------------------------------
--------------------------------------------------

CREATE OR REPLACE VIEW viewLastRecordMaterial
AS SELECT * FROM materials ORDER BY material_id DESC LIMIT 1;

CREATE OR REPLACE VIEW viewLastIdMaterial
AS SELECT material_id FROM viewLastRecordMaterial;

CREATE OR REPLACE VIEW viewLastRecordFactura
AS SELECT * FROM purchase_suppliers ORDER BY purchase_id DESC LIMIT 1;

CREATE OR REPLACE VIEW viewLastIdFactura
AS SELECT purchase_id FROM viewLastRecordFactura;

--------------------------------------------------
--------------------------------------------------
--------------------------------------------------
------------------Pruebas-------------------------
--------------------------------------------------
--------------------------------------------------
--------------------------------------------------
/*---generados---
--Suppliers
insert into suppliers values
(Default,'La Constancia','Bebidas','Distribución de alimentos',
'San Salvador Calle 3 San Juan Pablo #5','laconstancia@gmail.com','San Salvador','Zona Central',
'5098','El Salvador','+5032440-6998','2656965989898','2555656678','564892155698'),
(Default,'Pasteleria BYO','Pasteles','Llenado',
'San Salvador Calle 7 San Juan #9','pbyo@gmail.com','San Salvador','Zona Central',
'5096','El Salvador','+5032440-8791','2656912576349','2555656678','564892155698'),
(Default,'Pan bendicion','Pan','pan',
'San Salvador Calle 8 Mateo #9','pbsv@gmail.com','San Salvador','Zona Central',
'500596','El Salvador','+5032430-8791','2656915668349','2578978','456426')
(Default,'banban','Pasteles','Llenado',
'San Salvador Calle 1 San Juan #1','banbansv@gmail.com','San Salvador','Zona Central',
'79696','El Salvador','+5032440-8791','2656912235649','789896678','14514')
(Default,'Papas tm','Cuero','expansion',
'San Salvador Calle 12 Soloza #9','acm1pt@gmail.com','San Salvador','Zona Central',
'509336','El Salvador','+5032440-8791','25161576349','2231312','0223892155698')
(Default,'Pasteleria BYO','Pasteles','Llenado',
'San Salvador Calle 7 San Juan #9','pbyo@gmail.com','San Salvador','Zona Central',
'5132','El Salvador','+5032440-8791','2656912576349','74585669','412421')
(Default,'Pasteleria BYO','Pasteles','Llenado',
'San Salvador Calle 7 San Juan #9','pbyo@gmail.com','San Salvador','Zona Central',
'02','El Salvador','+5032440-8791','2656912576349','7458589696','4747585')
(Default,'Pasteleria BYO','Pasteles','Llenado',
'San Salvador Calle 7 San Juan #9','pbyo@gmail.com','San Salvador','Zona Central',
'021','El Salvador','+5032440-8791','2656912576349','69965858474','74745658')
(Default,'Pasteleria BYO','Pasteles','Llenado',
'San Salvador Calle 7 San Juan #9','pbyo@gmail.com','San Salvador','Zona Central',
'2013','El Salvador','+5032440-8791','2656912576349','2555656678','564634521')
(Default,'Pasteleria BYO','Pasteles','Llenado',
'San Salvador Calle 7 San Juan #9','pbyo@gmail.com','San Salvador','Zona Central',
'7996','El Salvador','+5032440-8791','2656912576349','2555656678','456566556')
(Default,'banbasgh','tijeras','Llenado',
'San Salvador Calle 7 San Juan #9','bgb@gmail.com','San Salvador','Zona Central',
'46456','El Salvador','+5032440-8791','2656912576349','2555656678','4545545')
(Default,'Pasteleria y mas','Pasteles','varios',
'San Salvador Calle 6 vomit #78','pym@gmail.com','San Salvador','Zona Central',
'4456','El Salvador','+5032569495','56987423576349','5698472356678','56789635498');

--Materiales
insert into materials values
(default,1,'Envase 3l',40,'Litros',0.5,2,'2019-06-02',0),
(default,2,'Pan bagett',40,'Gramos',0.5,2,'2019-06-01',0);

--venta
insert into purchase_suppliers values
(default,'2019-05-9',1,'Compra pagada en dias sin alcanze',1,3,5.2);

UPDATE purchase_suppliers set purchase_status = 3 where purchase_suppliers.purchase_id = 1;
select * from suppliers;
select * from purchase_suppliers;
select * from purchase_suppliers_log;
select * from materials
select insertMaterial(1, 'bb',2,'2', 25, 3, '2019-06-05', 5);
SELECT insertSupplier('hola', 'celo', 'de', 'sin calle',
    'balrking ', 'imagination', 'todas', '5098', 'maravillas',
    '50398', '5656', '55656', '56');


SELECT * from searchSuppliers('hola', 'celo', 'de', 'sin calle',
    'balrking ', 'imagination', 'todas', '5098', 'maravillas',
    '50398', '5656', '55656', '56');


SELECT updateSupplier(1,'cambio', 'ce', 'de', 'wewe',
    'balrking ', 'imagination', 'todas', '5098', 'maravillas',
    '50398', '5656', '55656', '56');

--------------------------------------------
/*