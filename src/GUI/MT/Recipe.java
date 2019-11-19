/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.MT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author julio
 */
public class Recipe {
    private int idRecipe;
    private double unitCost;
    private double salePrice;
    private int idProduct;
    private int idSupplyRecipe;
    private double quantity;
    private double supplyUnitCost;
    private double supplyTotalCost;
    private int idMeasurement;
    private int idMaterial;

    public int getIdRecipe() {
        return idRecipe;
    }

    public void setIdRecipe(int idRecipe) {
        this.idRecipe = idRecipe;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getIdSupplyRecipe() {
        return idSupplyRecipe;
    }

    public void setIdSupplyRecipe(int idSupplyRecipe) {
        this.idSupplyRecipe = idSupplyRecipe;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getSupplyUnitCost() {
        return supplyUnitCost;
    }

    public void setSupplyUnitCost(double supplyUnitCost) {
        this.supplyUnitCost = supplyUnitCost;
    }

    public double getSupplyTotalCost() {
        return supplyTotalCost;
    }

    public void setSupplyTotalCost(double supplyTotalCost) {
        this.supplyTotalCost = supplyTotalCost;
    }

    public int getIdMeasurement() {
        return idMeasurement;
    }

    public void setIdMeasurement(int idMeasurement) {
        this.idMeasurement = idMeasurement;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }
    
    public void insertRecipe(double costoUni, int idProducto, ArrayList<Materiales> materiales){
        try {
            ConexionBD conex = new ConexionBD();
            String query ="SELECT set_new_recipe(?,?);";
            PreparedStatement pst = conex.getConnection().prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int idAux = rs.getInt("id_recipe");
            String query2 = "SELECT set_new_supply_for_recipe(?,?,?,?);";
            for (int i = 0; i < materiales.size(); i++) {
                PreparedStatement pstt = conex.getConnection().prepareStatement(query2);
                pstt.setInt(1, idAux);
                pstt.setInt(2, materiales.get(i).material_id);
                pstt.setInt(3, materiales.get(i).measurement);
                pstt.setDouble(4, materiales.get(i).unit_price);
                pstt.execute();
            }
            //CloseCONN
        } catch (Exception e) {
            
        }
    }
    public ArrayList<String> measurementList(){
        try {
            ConexionBD conex = new ConexionBD();
            String query = "SELECT descrip FROM measurement_description;";
            Statement st = conex.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            ArrayList<String> data = new ArrayList<String>();
            while (rs.next()) {
                 data.add(rs.getString("descrip"));
            }
            return data;
        } catch (Exception e) {
            return null;
        }
    }
    public int agregarProducto(String nombre){
        try {
            ConexionBD conex = new ConexionBD();
            String query = "SELECT insertProductForRecipe(?);";
            PreparedStatement pst = conex.getConnection().prepareStatement(query);
            pst.setString(1, nombre);
            ResultSet dd = pst.executeQuery();
            dd.next();
            int idProd = dd.getInt("id_finished_product");
            //CLOSE CON
            return idProd;
        } catch (Exception e) {
            return 0;
        }
    }
}
