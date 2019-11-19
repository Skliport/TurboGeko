
package Entidades.PP;

import java.sql.Date;

public class PlanDePago {
    
    //Campos - Atributos.
    private int purchase_payment_plan_id, purchase_id, payment_plan_status_id, number_of_payments;
    private String status, company_name;
    private double total, amount_paid, remaining_amount, late_fee_percentage_charge, 
    late_fee_total, late_fee_paid_amount;
    private Date purchase_date;
    
    //Constructor.
    public PlanDePago(){       
    }
    
    //Métodos de acceso - Set.
    public void setPurchase_payment_plan_id(int purchase_payment_plan_id){
        this.purchase_payment_plan_id = purchase_payment_plan_id; 
    }
    
    public void setPurchase_id(int purchase_id){
        this.purchase_id = purchase_id; 
    }
    
    public void setPayment_plan_status_id(int payment_plan_status_id){
        this.payment_plan_status_id = payment_plan_status_id; 
    }
    
    public void setNumber_of_payments(int number_of_payments){
        this.number_of_payments = number_of_payments; 
    }
    
    public void setStatus(String status){
        this.status = status; 
    }
    
    public void setSupplier(String company_name){
        this.company_name = company_name; 
    }
    
    public void setTotal(double total){
        this.total = total; 
    }
    
    public void setAmount_paid(double amount_paid){
        this.amount_paid = amount_paid; 
    }
    
    public void setRemaining_amount(double remaining_amount){
        this.remaining_amount = remaining_amount; 
    }
    
    public void setLate_fee_percentage_charge(double late_fee_percentage_charge){
        this.late_fee_percentage_charge = late_fee_percentage_charge; 
    }
    
    public void setLate_fee_total(double late_fee_total){
        this.late_fee_total = late_fee_total; 
    }
    
    public void setLate_fee_paid_amount(double late_fee_paid_amount){
        this.late_fee_paid_amount = late_fee_paid_amount; 
    }
    
    public void setPurchase_date(Date purchase_date){
        this.purchase_date = purchase_date; 
    }
     
    //Métodos de acceso - Get.
    public int getPurchase_payment_plan_id(){return this.purchase_payment_plan_id;}
    public int getPurchase_id(){return this.purchase_id;}
    public int getPayment_plan_status_id(){return this.payment_plan_status_id;}
    public int getNumber_of_payments(){return this.number_of_payments;}
    public String getStatus(){return this.status;}
    public String getSupplier(){return this.company_name;}
    public double getTotal(){return this.total;}
    public double getAmount_paid(){return this.amount_paid;}
    public double getRemaining_amount(){return this.remaining_amount;}
    public double getLate_fee_percentage_charge(){return this.late_fee_percentage_charge;}
    public double getLate_fee_total(){return this.late_fee_total;}
    public double getLate_fee_paid_amount(){return this.late_fee_paid_amount;}
    public Date getPurchaseDate(){return this.purchase_date;}
}