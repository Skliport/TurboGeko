CREATE TABLE recipe (
    id_recipe SERIAL PRIMARY KEY,
    cod_recipe varchar(20),
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
    id_category_cost int NOT NULL
);

CREATE TABLE cost_category(
    id_cost_category INT PRIMARY KEY,
    category_description VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS finished_product(
	id_finished_product SERIAL PRIMARY KEY,
	name_product VARCHAR(40),
	units_in_stock REAL,
	unit_price REAL,
	quantity INT,
	discontinued INT,
	manufacture_date DATE,
	total REAL
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

--INSERCIÓN DE DISCRIMINANTE measurement
INSERT INTO meauserement_description (id_measurement, descrip) VALUES (),(),(),();
--procedimientos almacenados
--PROCEDIMIENTO: insertar una nueva receta

CREATE OR REPLACE FUNCTION set_new_recipe(punit_cost REAL, psale_price REAL, pid_product INT)
RETURNS REAL AS $$
BEGIN

END; $$
LANGUAGE plpgsql;

--procedimiento: insertar insumos de receta

CREATE OR REPLACE FUNCTION set_new_supply_for_recipe(punit_cost REAL, psale_price REAL, pid_product INT)
RETURNS REAL AS $$
BEGIN

END; $$
LANGUAGE plpgsql;