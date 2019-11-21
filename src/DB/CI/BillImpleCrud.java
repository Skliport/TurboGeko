/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB.CI;

import Clases.Bill;
import Clases.Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Interfaces.ICrudGeneric;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author Alejandro
 */
public class BillImpleCrud extends EstablishConnection implements ICrudGeneric<Bill>{

    @Override
    public boolean ingresar(Bill generico) {
        Connection conex = super.conex();
        ResultSet rs;
        if (conex == null) {
            return false;
        }else{
            try {
                PreparedStatement query = conex.prepareStatement(
                        "select insertpurchasesuppliers(?, ?, ?, ?, ?, ?);");
                query.setDate(1, generico.getPurchase_date());
                query.setInt(2, generico.getPurchase_status());
                query.setString(3, generico.getObservations());
                query.setFloat(4, (generico.getSubtotal()));
                query.setFloat(5, generico.getIva());
                query.setFloat(6, generico.getTotal());
                query.execute();
                int comienzo=0;
                query = conex.prepareStatement(
                        "select * from viewLastIdMaterial;");
                rs = query.executeQuery();
                while(rs.next()){
                    comienzo = rs.getInt(1);
                }
                query = conex.prepareStatement(
                        "select insertMaterial(?,?,?,?,?,?,?,?);");
                for (Material material : generico.getAlMaterial()) {
                    query.setInt(1, generico.getSupplier().getSupplier_id());
                    query.setString(2, material.getMaterial_name());
                    query.setInt(3, material.getUnits_in_stock());
                    query.setString(4, material.getMaterial_measure());
                    query.setFloat(5, material.getUnit_price());
                    query.setInt(6, material.getNumber_lot());
                    query.setDate(7, material.getExpiration_date());
                    query.setInt(8, 1);
                    query.execute();
                }
                int finali = 0;
                 query = conex.prepareStatement(
                        "select * from viewLastIdMaterial;");
                rs = query.executeQuery();
                while(rs.next()){
                    finali = rs.getInt(1);
                }
                int pid = this.ultimo();
                int ma=0;
                for (int i = comienzo; i <finali; i++) {
                    query = conex.prepareStatement("SELECT insertpurchasesuppliersdetails('"+pid+
                    "','"+i+"','"+generico.getAlMaterial().get(ma).getNumber_lot()+"','"+generico.getAlMaterial().get(ma).getUnit_price()+"')");
                    ma++;
                    query.execute();
                }
                super.cerrarConex(conex);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(SupplierImpleCrud.class.getName()).log(Level.SEVERE, null, ex);
                super.cerrarConex(conex);
                return false;
            }
        }
    }

    @Override
    public boolean actualizar(Bill generico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Bill generico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Bill> obtener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void sebastian(int ppurchase_id,Float ptotal,int pnumber_of_payments,Float plate_fee_percentage_charge,
            Date ppurchase_date) {
        Connection conex = super.conex();
        if (conex == null) {
        }else{
            try {
                PreparedStatement query = conex.prepareStatement("SELECT new_purchase_payment_plan (?,?,?,?,?)");
                query.setInt(1, ppurchase_id);
                query.setFloat(2, ptotal);
                query.setInt(3, pnumber_of_payments);
                query.setFloat(4, plate_fee_percentage_charge);
                query.setDate(5, ppurchase_date);
                query.execute();
                super.cerrarConex(conex);
            } catch (SQLException ex) {
                Logger.getLogger(SupplierImpleCrud.class.getName()).log(Level.SEVERE, null, ex);
                super.cerrarConex(conex);
            }
        }
    }
    public int ultimo() {
        Connection conex = super.conex();
        ResultSet rs=null;
        int numero=0;
        if (conex == null) {
            return numero;
        }else{
            try {
                PreparedStatement query = conex.prepareStatement("select * from viewLastIdFactura");
                rs = query.executeQuery();
                while(rs.next()){
                    numero = rs.getInt(1);
                }
                return numero;
            } catch (SQLException ex) {
                Logger.getLogger(SupplierImpleCrud.class.getName()).log(Level.SEVERE, null, ex);
                super.cerrarConex(conex);
                return numero;
            }
        }
    }
}
