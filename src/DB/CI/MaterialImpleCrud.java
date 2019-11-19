/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB.CI;

import Clases.Material;
import Interfaces.ICrudGeneric;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class MaterialImpleCrud extends EstablishConnection implements ICrudGeneric<Material>{

    @Override
    public boolean ingresar(Material generico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizar(Material generico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eliminar(Material generico) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Material> obtener() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public int viewLastIdMaterial() {
        int numero=0;
        ResultSet rs=null;
        Connection conex = super.conex();
        if (conex == null) {
            return numero;
        }else{
            try {
                PreparedStatement query = conex.prepareStatement("select * from viewLastIdMaterial;");
                rs = query.executeQuery();
                while(rs.next()){
                    numero = rs.getInt(1);
                }
                super.cerrarConex(conex);
                return numero;
            } catch (SQLException ex) {
                Logger.getLogger(SupplierImpleCrud.class.getName()).log(Level.SEVERE, null, ex);
                super.cerrarConex(conex);
                return numero;
            }
        }
    }
}
