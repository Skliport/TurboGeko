
package Entidades.PP;

import java.sql.Timestamp;

public class AvancePago {
    
    //Campos - Atributos.
    private int purchase_payment_id;
    private double paid_amount;
    private Timestamp upfront_payment_date;
    
    //Constructor.
    public AvancePago(){       
    }
    
    //Métodos de acceso - Set.
    public void setPurchase_payment_id(int purchase_payment_id){
        this.purchase_payment_id = purchase_payment_id; 
    }
    
    public void setPaid_amount(double paid_amount){
        this.paid_amount = paid_amount; 
    }
    
    public void setUpfront_payment_date(Timestamp upfront_payment_date){
        this.upfront_payment_date = upfront_payment_date; 
    }
     
    //Métodos de acceso - Get.
    public int getPurchase_payment_id(){return this.purchase_payment_id;}
    public double getPaid_amount(){return this.paid_amount;}
    public Timestamp getUpfront_payment_date(){return this.upfront_payment_date;}
}