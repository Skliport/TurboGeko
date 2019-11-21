CREATE OR REPLACE FUNCTION updateM (in precipe_id int, in pquantity int)
RETURNS VARCHAR(30)
LANGUAGE plpgsql
AS $$
DECLARE
cursorM cursor FOR SELECT id_material , quantity from supply_for_recipe where id_recipe = precipe_id;
 record_up record;
 BEGIN
  Open cursorM;
 LOOP
 FETCH cursorM INTO record_up;
 EXIT WHEN NOT FOUND;
 UPDATE materials SET units_in_stock = units_in_stock - record_up.quantity * pquantity where material_id = record_up.id_material ;
 END LOOP;
 Close cursorM;
 RETURN 'se ha actualizado con exito';
 END;
$$