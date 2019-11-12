CREATE TABLE recipe (id_recipe SERIAL PRIMARY KEY,  
unit_cost REAL, sale_price REAL, 
id_product INT);

CREATE TABLE supply_for_recipe(id_supply SERIAL PRIMARY KEY, 
quantity REAL, unit_cost REAL, 
total_cost REAL,id_measurement int, id_recipe int
id_material int);

CREATE TABLE measurement_description(id_measurement SERIAL PRIMARY KEY,
descrip varchar(60));

CREATE TABLE monthly_cost (id_cost SERIAL PRIMARY KEY,
 _cost REAL, id_category_cost int NOT NULL);

CREATE TABLE cost_category(id_cost_category INT PRIMARY KEY, 
category_description VARCHAR(200));


ALTER TABLE supply_for_recipe ADD CONSTRAINT fk_supply_recipe FOREIGN KEY (id_recipe)
REFERENCES recipe (id_recipe);

ALTER TABLE supply_for_recipe ADD CONSTRAINT fk_supply_measurement FOREIGN KEY (id_measurement)
REFERENCES measurement_description (id_measurement);

ALTER TABLE supply_for_recipe ADD CONSTRAINT fk_supply_material FOREIGN KEY (id_material)
REFERENCES material (id_material);

ALTER TABLE recipe ADD CONSTRAINT fk_recipe_product FOREIGN KEY(id_product)
REFERENCES finished_product (id_finished_product);

ALTER TABLE monthly_cost ADD CONSTRAINT fk_monthly_category FOREIGN KEY (id_category_cost)
REFERENCES cost_category(id_cost_category);

--raw_income deberá ser calculado
--PROCEDIMIENTO ALMACENADO PARA CALCULAR EL raw_income

CREATE OR REPLACE FUNCTION raw_income(p_product_id INTEGER)
RETURNS REAL AS $$
DECLARE
rec RECORD;
cur CURSOR ()
    FOR SELECT
BEGIN

END;
$$
LANGUAGE plpgsql

CREATE OR REPLACE FUNCTION production_product_cost(p_product_id INTEGER)
RETURNS REAL AS $$
DECLARE
rec RECORD;
cur CURSOR ()
    FOR SELECT
BEGIN

END;
$$
LANGUAGE plpgsql