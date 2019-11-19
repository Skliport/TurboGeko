
package Entidades.PP;

import java.sql.Timestamp;
import java.sql.Date;

public class Cuota {
    
    //Campos - Atributos.
    private int purchase_payment_id, purchase_id, payment_status_id, number_of_payment;
    private String status;
    private double total, paid_amount, late_fee_charge;
    private Date due_date;
    private Timestamp payment_date;
    
    //Constructor.
    public Cuota(){       
    }
    
    //Métodos de acceso - Set.
    public void setPurchase_payment_id(int purchase_payment_id){
        this.purchase_payment_id = purchase_payment_id; 
    }
    
    public void setPurchase_id(int purchase_id){
        this.purchase_id = purchase_id; 
    }
    
    public void setPayment_status_id(int payment_status_id){
        this.payment_status_id = payment_status_id; 
    }
    
    public void setNumber_of_payment(int number_of_payment){
        this.number_of_payment = number_of_payment; 
    }
    
    public void setStatus(String status){
        this.status = status; 
    }
    
    public void setTotal(double total){
        this.total = total; 
    }
    
    public void setPaid_amount(double paid_amount){
        this.paid_amount = paid_amount; 
    }
    
    public void setLateFeeCharge(double late_fee_charge){
        this.late_fee_charge = late_fee_charge; 
    }
    
    public void setDue_date(Date due_date){
        this.due_date = due_date; 
    }
    
    public void setPayment_date(Timestamp payment_date){
        this.payment_date = payment_date; 
    }
     
    //Métodos de acceso - Get.
    public int getPurchase_payment_id(){return this.purchase_payment_id;}
    public int getPurchase_id(){return this.purchase_id;}
    public int getPayment_status_id(){return this.payment_status_id;}
    public int getNumber_of_payment(){return this.number_of_payment;}
    public String getStatus(){return this.status;}
    public double getTotal(){return this.total;}
    public double getPaid_amount(){return this.paid_amount;}
    public double getLateFeeCharge(){return this.late_fee_charge;}
    public Date getDue_date(){return this.due_date;}
    public Timestamp getPayment_date(){return this.payment_date;}
}