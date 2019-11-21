/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Date;
import java.util.ArrayList;
/**
 *
 * @author Alejandro
 */
public class Bill {
    private int purchase_id;
    private Date purchase_date;
    private int purchase_status;
    private String observations;
    private Float subtotal;
    private Float iva;
    private Float total;
    private Supplier supplier;
    private ArrayList<Material> alMaterial;
    private PurchaseDetails orderDetails;
    
    public Bill() {
        this.alMaterial = new ArrayList<>();
    }

    public Bill(int purchase_id, Date purchase_date, int purchase_status, String observations, Float subtotal, Float iva, Float total, Supplier supplier, ArrayList<Material> alMaterial, PurchaseDetails orderDetails) {
        this.purchase_id = purchase_id;
        this.purchase_date = purchase_date;
        this.purchase_status = purchase_status;
        this.observations = observations;
        this.subtotal = subtotal;
        this.iva = iva;
        this.total = total;
        this.supplier = supplier;
        this.alMaterial = alMaterial;
        this.orderDetails = orderDetails;
    }

    public int getPurchase_id() {
        return purchase_id;
    }

    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public int getPurchase_status() {
        return purchase_status;
    }

    public void setPurchase_status(int purchase_status) {
        this.purchase_status = purchase_status;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Float subtotal) {
        this.subtotal = subtotal;
    }

    public Float getIva() {
        return iva;
    }

    public void setIva(Float iva) {
        this.iva = iva;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public ArrayList<Material> getAlMaterial() {
        return alMaterial;
    }

    public void setAlMaterial(ArrayList<Material> alMaterial) {
        this.alMaterial = alMaterial;
    }

    public PurchaseDetails getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(PurchaseDetails orderDetails) {
        this.orderDetails = orderDetails;
    }
    
    
}