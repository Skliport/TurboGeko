/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author Alejandro
 */
public class PurchaseDetails {
    private int purchase_id;
    private int material_id;
    private int quantity;
    private double unit_price;

    public PurchaseDetails() {
    }

    public PurchaseDetails(int purchase_id, int material_id, int quantity, double unit_price) {
        this.purchase_id = purchase_id;
        this.material_id = material_id;
        this.quantity = quantity;
        this.unit_price = unit_price;
    }

    public int getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }
}