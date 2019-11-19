CREATE OR REPLACE FUNCTION validacion(in precipe_id int, in pquantity int)
RETURNS INTEGER
LANGUAGE plpgsql
AS $$
DECLARE 
 cursorMaterials cursor FOR SELECT id_material , quantity from supply_for_recipe where id_recipe = precipe_id;
 recordM record;
 lcant_mat INT:=0;
 lcant_mat_in_stock INT:=0;
 lcant_mat_for_recipe INT :=0;
 lcant_material INT:=0;
 BEGIN
  SELECT count(id_material) INTO lcant_mat_for_recipe from supply_for_recipe where id_recipe = precipe_id;
 Open cursorMaterials;
 LOOP
 FETCH cursorMaterials INTO recordM;
 EXIT WHEN NOT FOUND;
 SELECT ceiling(recordM.quantity*pquantity) ,units_in_stock INTO lcant_mat , lcant_mat_in_stock from materials where material_id = recordM.id_material;
 IF(lcant_mat <= lcant_mat_in_stock)THEN
 lcant_material = lcant_material + 1;
 END IF;
 END LOOP;
 Close cursorMaterials;
  IF(lcant_material = lcant_mat_for_recipe) THEN
  RETURN 0;
  ELSE
  RETURN 1;
  END IF;
 END;
 $$