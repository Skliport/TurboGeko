----------material--------------------------
create table material(

id_material int SERIAL PRIMARY KEY,

material_name varchar(60),

company_address varchar(100),

company_phone varchar(10),

quantity_per_unit int,

discountinued int
);
--------------venta---------------
create table sale(
 
id_sale int SERIAL PRIMARY KEY,
 
num_invoice int not null,

status_sale int not null,
 
date_sale date not null,

ship_date date,
 
sub_total real not null,
 
tax real not null,

observation varchar(200),
 
total real not null,

id_customer int not null

);


-----------detalle de venta------------------


create table sale_detail(

id_sale_detail int SERIAL PRIMARY KEY,

id_product int not null,

id_sale int not null,

unit_price real,

quantity int,

discount real,

amount real
);


----------------------venta---------------------------
create table purchase(

id_buy int SERIAL PRIMARY KEY,

num_invoice int not null,

date_buy date not null,

subtotal real not null,

iva real not null,

total real not null
);


------------------------detalle venta---------------------------------
create table purchase_detail(

id_buy_detail int SERIAL PRIMARY KEY,

id_material int not null,

id_buy int not null,

quantity int not null,

discount real not null,

unit_price real not null,

amount real not null
);


-------------------producto terminado-------------------------------
create table finished_product(

id_finished_product int SERIAL PRIMARY KEY,

units_int_stock int not null,

unit_price int not null,

primary key(id_finished_product)

);


-----------------------
Create table payment_employee(

id_payment_employee int SERIAL PRIMARY KEY,

id_employee int not null,

payment_date date not null,

salary real not null,
isss real not null,

afp real not null,

notes varchar(60)
);


