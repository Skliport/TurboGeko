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
CREATE OR REPLACE FUNCTION getCustomers() 
RETURNS TABLE(id integer, first_name character varying, last_name character varying, address character varying, mobile character varying, telephone character varying, dui character varying, nit character varying, nrc character varying, email character varying)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
             select * from customer;
    END;
$$ LANGUAGE plpgsql;
--2
CREATE OR REPLACE FUNCTION getOrders(pcustomer_id INTEGER) 
RETURNS TABLE(order_prod_date date,total real,order_state integer)
 AS $$
    DECLARE
        
    BEGIN
         RETURN QUERY
             select order_prod_date,total,order_state,quantity,unit_price 
             from order_production 
             inner join order_production_detail on order_production_detail.order_production_id=order_production.order_production_id
             where order_production.customer_id=pcustomer_id and order_production.order_state=1;
    END;
$$ LANGUAGE plpgsql;
--3
--4
select * from order_production where order_production.customer_id=1;
--5
select * from order_production where order_production.order_prod_date=;
select * from order_production where order_production.order_state=;
--6

--7
--8

select * from getCustomers();
