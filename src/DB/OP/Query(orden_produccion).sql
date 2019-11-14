CREATE TABLE IF NOT EXISTS order_production(
    order_production_id serial NOT NULL,
    customer_id bpchar NOT NULL,
    order_prod_date date NOT NULL,
    total REAL,
    order_state int,
    CHECK(total > 0),
    CHECK(
        order_state >= 0
        and order_state <= 3
    )
);

CREATE TABLE IF NOT EXISTS order_production_detail(
    order_production_detail_id serial NOT NULL,
    recipe_id SMALLINT NOT NULL,
    quantity int NOT NULL,
    unit_price REAL NOT NULL,
    order_production_id SMALLINT,
    CHECK(quantity > 0),
    CHECK(unit_price > 0)
);

CREATE TABLE IF NOT EXISTS product_discarded(
    product_discarded_id serial NOT NULL,
    recipe_id SMALLINT NOT NULL,
    quantity INT NOT NULL,
    order_production_id SMALLINT NOT NULL,
    CHECK(quantity > 0)
);

CREATE TABLE IF NOT EXISTS recipe (
    recipe_id SERIAL PRIMARY KEY NOT NULL,
    cod_recipe varchar(20) NOT NULL,
    unit_cost REAL NOT NULL,
    id_product int NOT NULL
);

CREATE TABLE IF NOT EXISTS cancel_order_production(
    order_production_id SMALLINT NOT NULL,
    reason varchar(500) NOT NULL,
    canceled_order_date date not null
);

ALTER TABLE
    order_production
ADD
    CONSTRAINT pk_order_production PRIMARY KEY(order_production_id);

ALTER TABLE
    order_production_detail
ADD
    CONSTRAINT pk_order_production_detial PRIMARY KEY(order_production_detail_id);

ALTER TABLE
    product_discarded
ADD
    CONSTRAINT pk_product_discarded PRIMARY KEY(product_discarded_id);

ALTER TABLE
    order_production_detail
ADD
    CONSTRAINT fk_order_production_detail_order_production FOREIGN KEY(order_production_id) REFERENCES order_production(order_production_id);

ALTER TABLE
    order_production
ADD
    CONSTRAINT fk_order_production_customer FOREIGN KEY(customer_id) REFERENCES customers(customer_id);

ALTER TABLE
    product_discarded
ADD
    CONSTRAINT fk_order_production_detail_product FOREIGN KEY(order_production_id) REFERENCES order_production(order_production_id);

ALTER TABLE
    order_production
ADD
    CONSTRAINT fk_cancel_order_production_order_production FOREIGN key (order_production_id) REFERENCES order_production(order_production_id);

ALTER TABLE
    order_production_detail
ADD
    CONSTRAINT fk_order_production_detail_recipe FOREIGN KEY (recipe_id) REFERENCES recipe (recipe_id);