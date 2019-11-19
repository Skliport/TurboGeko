
package Entidades.PP;

import java.sql.Timestamp;

public class MoraCuota {
    
    //Campos - Atributos.
    private int purchase_payment_id;
    private String status;
    private double total, paid_amount;
    private Timestamp payment_date;
    
    //Constructor.
    public MoraCuota(){       
    }
    
    //Métodos de acceso - Set.
    public void setPurchase_payment_id(int purchase_payment_id){
        this.purchase_payment_id = purchase_payment_id; 
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
    
    public void setPayment_date(Timestamp payment_date){
        this.payment_date = payment_date; 
    }
     
    //Métodos de acceso - Get.
    public int getPurchase_payment_id(){return this.purchase_payment_id;}
    public String getStatus(){return this.status;}
    public double getTotal(){return this.total;}
    public double getPaid_amount(){return this.paid_amount;}
    public Timestamp getPayment_date(){return this.payment_date;}
}
