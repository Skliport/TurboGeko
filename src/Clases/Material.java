/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import java.sql.Date;
/**
 *
 * @author Alejandro
 */
public class Material {
    private int material_id;
    private int supplier_id;
    private String material_name;
    private int units_in_stock;
    private String material_measure;
    private Float unit_price;
    private int number_lot;
    private Date expiration_date;
    private int discontinued;

    public Material() {
    }

    public Material(int material_id, int supplier_id, String material_name, int units_in_stock, String material_measure, Float unit_price, int number_lot, Date expiration_date, int discontinued) {
        this.material_id = material_id;
        this.supplier_id = supplier_id;
        this.material_name = material_name;
        this.units_in_stock = units_in_stock;
        this.material_measure = material_measure;
        this.unit_price = unit_price;
        this.number_lot = number_lot;
        this.expiration_date = expiration_date;
        this.discontinued = discontinued;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public int getUnits_in_stock() {
        return units_in_stock;
    }

    public void setUnits_in_stock(int units_in_stock) {
        this.units_in_stock = units_in_stock;
    }

    public String getMaterial_measure() {
        return material_measure;
    }

    public void setMaterial_measure(String material_measure) {
        this.material_measure = material_measure;
    }

    public Float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(Float unit_price) {
        this.unit_price = unit_price;
    }

    public int getNumber_lot() {
        return number_lot;
    }

    public void setNumber_lot(int number_lot) {
        this.number_lot = number_lot;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public int getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(int discontinued) {
        this.discontinued = discontinued;
    }
    
    
}