--Venta
--1
--saber todos los clientes
--2
--saber todas las ordenes por cliente que esten completadas pero que no se hayan entregado
--3
--despechar la venta o anular la venta
--4
--Busquedad de Ordenes
--5
--solo mostrar las ordenes filtradas
--6
--Bitacora de Productos
--7
--solo mostrar los productos 
--8
--cada vez que se entrega una venta o se anula se agrega un registro

--1
CREATE OR REPLACE FUNCTION getCustomersByLastName(plast_name varchar) 
RETURNS TABLE(id integer, Nombre character varying, Apellido character varying, Direccion character varying, Movil character varying, Telefono character varying, DUI character varying, NIT character varying, NRC character varying, Correo character varying)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
             select * from customer
             where customer.last_name like '%' || plast_name || '%';
    END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION getCustomersByFirstName(pfirst_name varchar) 
RETURNS TABLE(id integer, Nombre character varying, Apellido character varying, Direccion character varying, Movil character varying, Telefono character varying, DUI character varying, NIT character varying, NRC character varying, Correo character varying)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
             select * from customer
             where customer.first_name like '%' || pfirst_name || '%';
    END;
$$ LANGUAGE plpgsql;
CREATE OR REPLACE FUNCTION getCustomersById(pid integer) 
RETURNS TABLE(id integer, Nombre character varying, Apellido character varying, Direccion character varying, Movil character varying, Telefono character varying, DUI character varying, NIT character varying, NRC character varying, Correo character varying)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
             select * from customer
             where customer.customer_id=pid;
    END;
$$ LANGUAGE plpgsql;
-- drop FUNCTION getCustomers
--2
CREATE OR REPLACE FUNCTION getOrders(pcustomer_id INTEGER) 
RETURNS TABLE(Fecha date,Total real,Estado integer,Cantidad integer,Precio_Unitario real)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
             select order_prod_date,order_production.total,order_state,quantity,unit_price 
             from order_production 
             inner join order_production_detail on order_production_detail.order_production_id=order_production.order_production_id
             where order_production.customer_id=pcustomer_id and order_production.order_state=1;
    END;
$$ LANGUAGE plpgsql;
-- drop FUNCTION getOrders;
--3
--4
CREATE OR REPLACE FUNCTION getAllOrders(pcustomer_id INTEGER) 
RETURNS TABLE(Fecha date,Total real,Estado integer,Cantidad integer,Precio_Unitario real)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
             select order_prod_date,order_production.total,order_state,quantity,unit_price 
             from order_production 
             inner join order_production_detail on order_production_detail.order_production_id=order_production.order_production_id
             where order_production.customer_id=pcustomer_id;
    END;
$$ LANGUAGE plpgsql;
--5
CREATE OR REPLACE FUNCTION getOrderById(pOrderId INTEGER) 
RETURNS TABLE(Fecha date,Total real,Estado integer,Cantidad integer,Precio_Unitario real)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
             select order_prod_date,order_production.total,order_state,quantity,unit_price 
             from order_production 
             inner join order_production_detail on order_production_detail.order_production_id=order_production.order_production_id
             where order_production.order_production_id=pOrderId;
    END;
$$ LANGUAGE plpgsql;
CREATE OR REPLACE FUNCTION getOrderByDate(pOrderDate varchar) 
RETURNS TABLE(Fecha date,Total real,Estado integer,Cantidad integer,Precio_Unitario real)
 AS $$
    DECLARE
        lorderdate date;
    BEGIN
        lorderdate := (select pOrderDate::date);
         RETURN QUERY
             
             select order_prod_date,order_production.total,order_state,quantity,unit_price 
             from order_production 
             inner join order_production_detail on order_production_detail.order_production_id=order_production.order_production_id
             where order_production.order_prod_date=lorderdate;
    END;
$$ LANGUAGE plpgsql;
--6
CREATE OR REPLACE PROCEDURE insertIntoLog(pid_sale integer,porder_production_id integer,pstatus integer) 
 AS $$
    DECLARE
     
    BEGIN
         INSERT INTO product_log(pid_sale,porder_production,pupdate_time,pstatus)
         values(pid_sale,porder_production_id,pstatus);
    END;
$$ LANGUAGE plpgsql;

--
--insert sale
CREATE OR REPLACE PROCEDURE insertIntoSale(pnum_invoice varchar,pstatus_sale integer,pdate_sale varchar,pship_sale varchar, pdocument_type int,
                                           psub_total real, ptax real, ptotal real, pidcustomer int) 
 AS $$
    DECLARE
        
    BEGIN
         INSERT INTO sale(num_invoice,status_sale,date_sale, ship_date, document_type, 
                                sub_total, tax, total, id_customer)
         values(pnum_invoice,pstatus_sale,pdate_sale,pship_sale,pdocument_type,
               psub_total,ptax,ptotal,pidcustomer);
    END;
$$ LANGUAGE plpgsql;

--7
CREATE OR REPLACE FUNCTION getProducts() 
RETURNS TABLE(ID integer,Nombre character varying,Unidades_Stock integer,Precio_unitario real,cantidad integer,discontinuado integer,fecha_manufactura date,Precio_final real)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
            SELECT * 
            FROM finished_product
            GROUP BY id_finished_product,manufacture_date;
    END;
$$ LANGUAGE plpgsql;
--8
CREATE OR REPLACE PROCEDURE UpdateOrder(pid_order integer,pstatus integer) 
 AS $$
    DECLARE
        
    BEGIN
         update order_production set order_state = pstatus where order_production.order_production_id=pid_order;
    END;
$$ LANGUAGE plpgsql;

insert into finished_product(name_product,units_in_stock,unit_price,quantity,discontinued,manufacture_date,total) 
values('Cubo',15,12,15,0,(select CURRENT_DATE),30);

insert into customer(first_name,last_name,address,mobile,telephone,dui,nit,nrc,email)
values('Bryan','Palma','Mi casa','1234678','87654321','012345678-9','0210-123456-789-1','123456789123456','micorreo@algo.com');

insert into customer(first_name,last_name,address,mobile,telephone,dui,nit,nrc,email)
values('Mauricio','Flores','Mi casa #2','1234678','87654321','987654321-0','0210-123456-789-1','123456789123456','micorreo2@algo.com');

insert into recipe(unit_cost,sale_price,id_product)
values(12,15,1);
--orden no finalizada
insert into order_production(customer_id,order_prod_date,total,order_state)
values(1,(SELECT CURRENT_DATE),150,0);

insert into order_production_detail(id_recipe,quantity,unit_price,order_production_id)
values(1,15,15,1);
--fin orden no finalizada
--orden finalizada
insert into order_production(customer_id,order_prod_date,total,order_state)
values(1,(SELECT CURRENT_DATE),150,1);

insert into order_production_detail(id_recipe,quantity,unit_price,order_production_id)
values(1,15,15,2);
--fin orden finalizada

select * from getProducts();
select * from getcustomersbylastname('Pal');
select * from getcustomersbyfirstname('Mauricio');
select * from getOrders(1);
select * from getAllOrders(1);
select * from getOrderById(1);
select * from getorderbydate('20191118');

call insertintosale('2',0,'19-11-2019','25-11-2019',0,160,9.5,169.5,1);
select * from product_log

select * from sale

select now();
