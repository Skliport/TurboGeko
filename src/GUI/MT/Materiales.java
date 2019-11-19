package GUI.MT;

import java.awt.HeadlessException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author cesar
 */
public class Materiales {

    public int material_id;
    public int id_supplier;
    public String material_name;
    public int units_in_stock;
    public String material_measure;
    public double unit_price;
    public int number_lot;
    public Date expiration_date;
    public int discontinued;

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public int getId_supplier() {
        return id_supplier;
    }

    public void setId_supplier(int id_supplier) {
        this.id_supplier = id_supplier;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public int getUnits_in_stock() {
        return units_in_stock;
    }

    public void setUnits_in_stock(int units_in_stock) {
        this.units_in_stock = units_in_stock;
    }

    public String getMaterial_measure() {
        return material_measure;
    }

    public void setMaterial_measure(String material_measure) {
        this.material_measure = material_measure;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public int getNumber_lot() {
        return number_lot;
    }

    public void setNumber_lot(int number_lot) {
        this.number_lot = number_lot;
    }

    public Date getExpiration_date() {
        return expiration_date;
    }

    public void setExpiration_date(Date expiration_date) {
        this.expiration_date = expiration_date;
    }

    public int getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(int discontinued) {
        this.discontinued = discontinued;
    }

    public ArrayList<Materiales> CargarMateriales() {
        
        ArrayList<Materiales> Lma = new ArrayList<Materiales>();
        try {
            ConexionBD conn = new ConexionBD();
            int contador = 1;
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM materials ORDER BY material_id ASC");
            while (rs.next()) 
            {
                Materiales Ma = new Materiales();
                Ma.setMaterial_id(rs.getInt(1));
                Ma.setId_supplier(rs.getInt(2));
                Ma.setMaterial_name(rs.getString(3));
                Ma.setUnits_in_stock(rs.getInt(4));
                Ma.setMaterial_measure(rs.getString(5));
                Ma.setUnit_price(rs.getDouble(6));
                Ma.setNumber_lot(rs.getInt(7));
                Ma.setExpiration_date(rs.getDate(8));
                Ma.setDiscontinued(rs.getInt(9));
                Lma.add(Ma);
               
            }
            conn.close();
             return Lma;
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Revisar los datos ingresados");
        }
        return Lma;
    }
    
        public ArrayList<Materiales> CargarRecetas(int id) {
        
        ArrayList<Materiales> Lma = new ArrayList<Materiales>();
        try {
            ConexionBD conn = new ConexionBD();
            int contador = 1;
            Statement stm = conn.getConnection().createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM materials where material_id =? ORDER BY material_id ASC");
            while (rs.next()) 
            {
                Materiales Ma = new Materiales();
                Ma.setMaterial_id(rs.getInt(1));
                Ma.setId_supplier(rs.getInt(2));
                Ma.setMaterial_name(rs.getString(3));
                Ma.setUnits_in_stock(rs.getInt(4));
                Ma.setMaterial_measure(rs.getString(5));
                Ma.setUnit_price(rs.getDouble(6));
                Ma.setNumber_lot(rs.getInt(7));
                Ma.setExpiration_date(rs.getDate(8));
                Ma.setDiscontinued(rs.getInt(9));
                Lma.add(Ma);
               
            }
            conn.close();
             return Lma;
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Revisar los datos ingresados");
        }
        return Lma;
    }
}
