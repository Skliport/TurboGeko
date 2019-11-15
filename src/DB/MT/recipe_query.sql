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
l_result REAL DEFAULT 0;
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