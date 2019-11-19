CREATE TABLE recipe (
    id_recipe SERIAL PRIMARY KEY,
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
    id_recipe int id_material int
);

CREATE TABLE measurement_description(
    id_measurement SERIAL PRIMARY KEY,
    description varchar(60)
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
    CONSTRAINT fk_supply_material FOREIGN KEY (id_material) REFERENCES material (id_material);

ALTER TABLE
    recipe
ADD
    CONSTRAINT fk_recipe_product FOREIGN KEY(id_product) REFERENCES product (id_product);

ALTER TABLE
    monthly_cost
ADD
    CONSTRAINT fk_monthly_category FOREIGN KEY (id_category_cost) REFERENCES cost_category(id_cost_category);

--procedimientos almacenados
--PROCEDIMIENTO: calcular el costo de producción de un producto

CREATE OR REPLACE FUNCTION get_unit_cost_product(p_recipe_id INTEGER)
RETURNS REAL AS $$
DECLARE
l_result REAL := 0;
l_rec_mat RECORD;
l_cur_mat CURSOR(p_recipe_id INTEGER)
    FOR SELECT total_cost FROM supply_for_recipe
    WHERE recipe_id = p_recipe_id;
BEGIN
    OPEN l_cur_mat(p_recipe_id);
    LOOP
        FETCH l_cur_mat INTO l_rec_mat;
        EXIT WHEN NOT FOUND;
        SET l_result = l_result + l_rec_mat.total_cost;
    END LOOP;
    CLOSE l_cur_mat;
    RETURN l_result;
END; $$
LANGUAGE plpgsql;

--INSERCIÓN DE DISCRIMINANTE measurement
INSERT INTO meauserement_description (id_measurement, descrip) 
VALUES (1,'Kilogramo'),(2,'Miligramo'),(3,'Metro'),(4,'Centímetro'),
        (5,'Milímetro'),(6,'Litro'), (7,'Mililitro'),(8,'Onza'), (9,'Gramo'),
        (10,'Libra');
--procedimientos almacenados
--PROCEDIMIENTO: insertar una nueva receta

CREATE OR REPLACE FUNCTION set_new_recipe(punit_cost REAL, pid_product INT)
RETURNS INT AS $$
DECLARE
l_id INTEGER:=0;
BEGIN
    INSERT INTO recipe (unit_cost, sale_price, id_product) VALUES (punit_cost, psale_price, pid_product);
    SELECT id_recipe INTO l_id FROM recipe ORDER BY id_recipe DESC;
    RETURN l_id AS id_recipe;
END; $$
LANGUAGE plpgsql;

--procedimiento: insertar insumos de receta

CREATE OR REPLACE FUNCTION set_new_supply_for_recipe(pid_recipe INT, pid_material INT, pid_measurement INT, punit_cost REAL)
RETURNS VOID AS $$
BEGIN
    INSERT INTO supply_for_recipe(unit_cost,total_cost,id_measurement, id_recipe, id_material) 
    VALUES (punit_cost,pid_measurement, pid_recipe, pid_material);
END; $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insertProductForRecipe(pNombre VARCHAR(100))
RETURNS INT AS $$
DECLARE
l_id INTEGER :=0;
BEGIN
    INSERT INTO finished_product (name_product) VALUES (pNombre);
    SELECT id_finished_product INTO l_id FROM finished_product ORDER BY id_finished_product DESC LIMIT 1;
    RETURN l_id AS id_finished_product;
END;$$
LANGUAGE PLPGSQL;