
package Entidades.PP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBAdmin {
    
    //Atributos / Campos.
    private String usuario, password;
    private String db = "jdbc:postgresql://localhost:5432/PP_test";
    Connection conexion = null;
  
    //Constructor.
    public DBAdmin(){
    }
    
    //Constructor - Conexión inicial.
    public DBAdmin(String usuario, String password) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException{
        
        this.usuario = usuario; 
        this.password = password;
      
        try
        {
            //Instanciar conexión.
            Class.forName("org.postgresql.Driver").newInstance();
            this.conexion = DriverManager.getConnection(this.db, this.usuario, this.password); 
            System.out.println("Conexión establecida con éxito");
        }
        catch(SQLException ex){System.out.println(ex);} 
    }
    
    //Conectar.
    public void conectar() {
        try { this.conexion = DriverManager.getConnection(this.db, this.usuario, this.password); }
        catch(SQLException ex){System.out.println(ex);} 
    }
    
    //Ejecución de comandos.
    public void ejecutarComando(String comando) throws SQLException {
        try 
        {
            Statement com = this.conexion.createStatement();
            com.executeUpdate(comando); //Ejecución.
            System.out.println("Efectuado con éxito.");
        }
        catch(SQLException ex) {System.out.println(ex);}
    }
    
    //Ejecución de consultas.
    public ResultSet ejecutarConsulta(String consulta) throws SQLException{
        Statement st = this.conexion.createStatement();  //Objeto tipo statement que maneja la consulta.
        return st.executeQuery(consulta); //Ejecutando la consulta.
    }
    
    //Cerrar conexión db.
    public void cerrarConexion() throws SQLException {
        this.conexion.close();
    }
    
    //Métodos de acceso - Set.
    public void setUsuario(String usuario){
        this.usuario = usuario;   
    }
    
    public void setPassword(String password){
        this.password = password;   
    }
    
    //Métodos de acceso - Get.
    public Connection getConexion(){return this.conexion;} 
    public String getUsuario(){return this.usuario;} 

    // 1. Recupera listade planes de pago. - Realiza filtros por estado y búsqueda.
    public ArrayList getPlanesDePago(int status_filter, String filter, String expression) throws SQLException{
        try 
        {
            ArrayList<PlanDePago> lPlanDePago = new ArrayList<>();

            this.conectar();

            //Sentencia SQL de la consulta
            String consulta = "SELECT * FROM getPurchasePaymentPlan();";

            // - - - - - Filtros por estado - - - - - //
            switch(status_filter) {
                case 1: //Pendiente.
                    consulta = "SELECT * FROM (SELECT * FROM getPurchasePaymentPlan())sc1 WHERE " +
                    "payment_plan_status_id = 1;";
                break;

                case 2: //Cancelado.
                    consulta = "SELECT * FROM (SELECT * FROM getPurchasePaymentPlan())sc2 WHERE " +
                    "payment_plan_status_id = 2;";
                break;

                case 3: //En mora.
                    consulta = "SELECT * FROM (SELECT * FROM getPurchasePaymentPlan())sc1 WHERE " +
                    "late_fee_paid_amount<late_fee_total;";
                break;
            }

            // - - - - Búsquedas por filtro y expresión - - - - //
            switch(filter) {
                case "purchase_id": // Busq. por purchase_id.
                    consulta = "SELECT * FROM getPurchasePaymentPlanByPurchaseId('"+expression+"');";
                break;

                case "company_name": // Busq. por company_name.
                    consulta = "SELECT * FROM getPurchasePaymentPlanBySupplierName('"+expression+"');";
                break;

                case "purchase_date": // Busq. por purchase_date.
                    consulta = "SELECT * FROM getPurchasePaymentPlanByPurchaseDate('"+expression+"');";
                break;
            }

            //Obteniendo ResultSet.
            ResultSet rs = this.ejecutarConsulta(consulta);

            //Mostrando contenido del ResultSet.
            while(rs.next())
            {
               lPlanDePago.add(new PlanDePago());
               lPlanDePago.get(lPlanDePago.size()-1).setPurchase_payment_plan_id(rs.getInt("purchase_payment_plan_id"));
               lPlanDePago.get(lPlanDePago.size()-1).setPurchase_id(rs.getInt("purchase_id"));
               lPlanDePago.get(lPlanDePago.size()-1).setPayment_plan_status_id(rs.getInt("payment_plan_status_id"));
               lPlanDePago.get(lPlanDePago.size()-1).setNumber_of_payments(rs.getInt("number_of_payments"));
               lPlanDePago.get(lPlanDePago.size()-1).setStatus(rs.getString("name"));
               lPlanDePago.get(lPlanDePago.size()-1).setSupplier(rs.getString("company_name"));
               lPlanDePago.get(lPlanDePago.size()-1).setTotal(rs.getDouble("total"));
               lPlanDePago.get(lPlanDePago.size()-1).setAmount_paid(rs.getDouble("amount_paid"));
               lPlanDePago.get(lPlanDePago.size()-1).setRemaining_amount(rs.getDouble("remaining_amount"));
               lPlanDePago.get(lPlanDePago.size()-1).setLate_fee_percentage_charge(rs.getDouble("late_fee_percentage_charge"));
               lPlanDePago.get(lPlanDePago.size()-1).setPurchase_date(rs.getDate("purchase_date"));
               lPlanDePago.get(lPlanDePago.size()-1).setLate_fee_total(rs.getDouble("late_fee_total"));
               lPlanDePago.get(lPlanDePago.size()-1).setLate_fee_paid_amount(rs.getDouble("late_fee_paid_amount"));
            }

            this.cerrarConexion();
            return lPlanDePago; 
            
        } catch(SQLException ex) {System.out.println(ex); return null;}
    }  
    
    // 2. Recupera lista de cuotas por compra.
    public ArrayList getCuotas(int purchase_id) throws SQLException{
        try 
        { 
            ArrayList<Cuota> lCuota = new ArrayList<>();

            this.conectar();

            //Sentencia SQL de la consulta
            String consulta = "SELECT * FROM getPurchasePayment('"+purchase_id+"');";

            //Obteniendo ResultSet.
            ResultSet rs = this.ejecutarConsulta(consulta);

            //Mostrando contenido del ResultSet.
            while(rs.next())
            {
               lCuota.add(new Cuota());
               lCuota.get(lCuota.size()-1).setPurchase_payment_id(rs.getInt("purchase_payment_id"));
               lCuota.get(lCuota.size()-1).setPurchase_id(rs.getInt("purchase_id"));
               lCuota.get(lCuota.size()-1).setPayment_status_id(rs.getInt("payment_status_id"));
               lCuota.get(lCuota.size()-1).setTotal(rs.getDouble("total"));
               lCuota.get(lCuota.size()-1).setPaid_amount(rs.getDouble("paid_amount"));
               lCuota.get(lCuota.size()-1).setLateFeeCharge(rs.getDouble("late_fee_charge"));
               lCuota.get(lCuota.size()-1).setDue_date(rs.getDate("due_date"));
               lCuota.get(lCuota.size()-1).setPayment_date(rs.getTimestamp("payment_date"));
               lCuota.get(lCuota.size()-1).setStatus(rs.getString("name"));
               lCuota.get(lCuota.size()-1).setNumber_of_payment(lCuota.size());  
            }

            this.cerrarConexion();
            return lCuota; 
        } catch(SQLException ex) {System.out.println(ex); return null;}
    }  
    
    // 3. Recupera lista de moras por compra.
    public ArrayList getMoraDeCuotas(int purchase_id) throws SQLException{
        try 
        {
            ArrayList<MoraCuota> lMoraCuota = new ArrayList<>();

            this.conectar();

            //Sentencia SQL de la consulta
            String consulta = "SELECT * FROM getLateFeePayments('"+purchase_id+"');";

            //Obteniendo ResultSet.
            ResultSet rs = this.ejecutarConsulta(consulta);

            //Mostrando contenido del ResultSet.
            while(rs.next())
            {
               lMoraCuota.add(new MoraCuota());
               lMoraCuota.get(lMoraCuota.size()-1).setPurchase_payment_id(rs.getInt("purchase_payment_id"));
               lMoraCuota.get(lMoraCuota.size()-1).setStatus(rs.getString("status"));
               lMoraCuota.get(lMoraCuota.size()-1).setTotal(rs.getDouble("total"));
               lMoraCuota.get(lMoraCuota.size()-1).setPaid_amount(rs.getDouble("paid_amount"));
               lMoraCuota.get(lMoraCuota.size()-1).setPayment_date(rs.getTimestamp("payment_date"));
            }

            this.cerrarConexion();
            return lMoraCuota; 
        } catch(SQLException ex) {System.out.println(ex); return null;}
    }  
    
    // 4. Recuperar lista de avances de pago para compra.
    public ArrayList getAvancesDePago(int purchase_id) throws SQLException{
        try 
        {
            ArrayList<AvancePago> lAvancePago = new ArrayList<>();

            this.conectar();

            //Sentencia SQL de la consulta
            String consulta = "SELECT * FROM getUpfrontPayments('"+purchase_id+"');";

            //Obteniendo ResultSet.
            ResultSet rs = this.ejecutarConsulta(consulta);

            //Mostrando contenido del ResultSet.
            while(rs.next())
            {
               lAvancePago.add(new AvancePago());
               lAvancePago.get(lAvancePago.size()-1).setPurchase_payment_id(rs.getInt("purchase_payment_id"));
               lAvancePago.get(lAvancePago.size()-1).setPaid_amount(rs.getDouble("paid_amount"));
               lAvancePago.get(lAvancePago.size()-1).setUpfront_payment_date(rs.getTimestamp("upfront_payment_date"));
            }

            this.cerrarConexion();
            return lAvancePago; 
        } catch(SQLException ex) {System.out.println(ex); return null;}
    }  
}