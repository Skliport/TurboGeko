/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB.CI;

import Clases.Supplier;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import Interfaces.ICrudGeneric;

/**
 *
 * @author Alejandro
 */
public class SupplierImpleCrud extends EstablishConnection implements ICrudGeneric<Supplier>{

    @Override
    public boolean ingresar(Supplier generico) {
        Connection conex = super.conex();
        if (conex == null) {
            return false;
        }else{
            try {
                PreparedStatement query = conex.prepareStatement("SELECT insertSupplier('"+generico.getCompany_name()+
                    "','"+generico.getContact_name()+"','"+generico.getContact_title()+"','"+generico.getAddress()+"','" +
                    generico.getEmail()+"','"+generico.getCity()+"','"+generico.getRegion()+
                    "','"+generico.getPostal_code()+"','"+generico.getCountry()+"','"+
                    generico.getPhone()+"','"+generico.getFax()+"','"+generico.getNrc()+"','"+generico.getNit()+"')");
                boolean bol = query.execute();
                super.cerrarConex(conex);
                return bol;
            } catch (SQLException ex) {
                Logger.getLogger(SupplierImpleCrud.class.getName()).log(Level.SEVERE, null, ex);
                super.cerrarConex(conex);
                return false;
            }
        }
    }
    @Override
    public boolean actualizar(Supplier generico) {
        Connection conex = super.conex();
        ResultSet rs;
        if (conex == null) {
            return false;
        }else{
            try {
                PreparedStatement query = conex.prepareStatement("select updatesupplier(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                query.setInt(1, generico.getSupplier_id());
                query.setString(2, generico.getCompany_name());
                query.setString(3, generico.getContact_name());
                query.setString(4, generico.getContact_title());
                query.setString(5, generico.getAddress());
                query.setString(6, generico.getEmail());
                query.setString(7, generico.getCity());
                query.setString(8, generico.getRegion());
                query.setString(9, generico.getPostal_code());
                query.setString(10, generico.getCountry());
                query.setString(11, generico.getPhone());
                query.setString(12, generico.getFax());
                query.setString(13, generico.getNrc());
                query.setString(14, generico.getNit());
                rs = query.executeQuery();
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
    public boolean eliminar(Supplier generico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Supplier> obtener() {
        ArrayList<Supplier>altem = new ArrayList<>();
        ResultSet rs;
        Connection conex = super.conex();
        if (conex == null) {
            super.cerrarConex(conex);
            return altem;
        }else{
            try {
                PreparedStatement query = conex.prepareStatement("SELECT * FROM suppliers");
                rs = query.executeQuery();
                while(rs.next()){
                    altem.add(new Supplier(rs.getInt("supplier_id"), rs.getString("company_name"),
                        rs.getString("contact_name"), rs.getString("contact_title"),
                        rs.getString("address"),rs.getString("email"), rs.getString("city"),
                        rs.getString("region"), rs.getString("postal_code"), rs.getString("country"),
                        rs.getString("phone"), rs.getString("fax"), rs.getString("nrc"), rs.getString("nit")));
                }
                super.cerrarConex(conex);
            } catch (SQLException ex) {
                Logger.getLogger(SupplierImpleCrud.class.getName()).log(Level.SEVERE, null, ex);
                super.cerrarConex(conex);
            }
        }
        return altem;
    }
    
    public ArrayList<Supplier> searchSuppliers(Supplier generico) {
        ArrayList<Supplier>altem = new ArrayList<>();
        ResultSet rs;
        Connection conex = super.conex();
        try {
            PreparedStatement query = conex.prepareStatement("select * from searchsuppliers(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
                query.setString(1, generico.getCompany_name());
                query.setString(2, generico.getContact_name());
                query.setString(3, generico.getContact_title());
                query.setString(4, generico.getAddress());
                query.setString(5, generico.getEmail());
                query.setString(6, generico.getCity());
                query.setString(7, generico.getRegion());
                query.setString(8, generico.getPostal_code());
                query.setString(9, generico.getCountry());
                query.setString(10, generico.getPhone());
                query.setString(11, generico.getFax());
                query.setString(12, generico.getNrc());
                query.setString(13, generico.getNit());
            rs = query.executeQuery();
            while(rs.next()){
                altem.add(new Supplier(rs.getInt("supplier_id"), rs.getString("company_name"),
                        rs.getString("contact_name"), rs.getString("contact_title"), rs.getString("address"),
                        rs.getString("email"), rs.getString("city"), rs.getString("region"),
                        rs.getString("postal_code"), rs.getString("country"), rs.getString("phone"),
                        rs.getString("fax"), rs.getString("nrc"), rs.getString("nit")));
            }
            super.cerrarConex(conex);
        } catch (SQLException ex) {
            Logger.getLogger(SupplierImpleCrud.class.getName()).log(Level.SEVERE, null, ex);
            super.cerrarConex(conex);
        }
        
        return altem;
    }
    public Supplier searchSuppliers(int id) {
        Supplier suppliertem = null;
        ResultSet rs;
        Connection conex = super.conex();
        try {
            PreparedStatement query = conex.prepareStatement("select * from searchSuppliers(?);");
            query.setInt(0, id);
            rs = query.executeQuery();
            suppliertem = new Supplier(rs.getInt("supplier_id"), rs.getString("company_name"),
                        rs.getString("contact_name"), rs.getString("contact_title"), rs.getString("address"),
                        rs.getString("email"), rs.getString("city"), rs.getString("region"),
                        rs.getString("postal_code"), rs.getString("country"), rs.getString("phone"),
                        rs.getString("fax"), rs.getString("nrc"), rs.getString("nit"));
            super.cerrarConex(conex);
        } catch (SQLException ex) {
            Logger.getLogger(SupplierImpleCrud.class.getName()).log(Level.SEVERE, null, ex);
            super.cerrarConex(conex);
        }
        return suppliertem;
    }
}
