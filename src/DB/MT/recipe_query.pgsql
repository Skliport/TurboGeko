--tabla receta, almacenará a partir de los productos existentes
--unifica el producto con su coste de producción unitario y su precio de venta unitario
CREATE TABLE recipe (id_recipe SERIAL PRIMARY KEY,  
unit_cost REAL, sale_price REAL, 
id_product INT);

--tabla suministros, almacenará a partir de los materiales existentes, 
--aqui se detalla la cantidad de material que se necesita y que forma parte de una receta 
--(una receta tiene muchos suministros)
CREATE TABLE supply_for_recipe(id_supply SERIAL PRIMARY KEY, 
quantity REAL, unit_cost REAL, 
total_cost REAL,id_measurement int, id_recipe int
id_material int);

--tabla discriminante para los tipos de medidas (galones, libras, litros, onzas)
CREATE TABLE measurement_description(id_measurement SERIAL PRIMARY KEY,
descrip varchar(60));

--tabla que permite guardar costos extra de la empresa 
CREATE TABLE monthly_cost (id_cost SERIAL PRIMARY KEY,
 _cost REAL, cost_detail varchar(100),
--cost_detail (ej: 2 libras de cafe, una cafetera, 
--dulces, chilate, yuca con pepesca) 
id_category_cost int NOT NULL);

--discriminante para las categorías de los costos (servicios, alimentacion, viáticos, salario)
CREATE TABLE cost_category(id_cost_category INT PRIMARY KEY, 
category_description VARCHAR(200));

--llaves foráneas
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
--PROCEDIMIENTO ALMACENADO PARA CALCULAR EL coste de producción de un artículo
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