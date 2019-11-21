/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB.CI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Alejandro
 */
public class EstablishConnection {
    public Connection conex(){
        Connection conec = null;
        try {
            Class.forName("org.postgresql.Driver");
            try {
                conec = DriverManager.getConnection("jdbc:postgresql://127.0.0.1/vf","postgres","1234");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "¡Problemas al realizar la conexión\ncon la base de datos!","Mensaje del sistema",JOptionPane.ERROR_MESSAGE);
                System.out.println(ex);
                Logger.getLogger(EstablishConnection.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(0);
            }
        } catch (ClassNotFoundException v) {
        }
        return conec;
    }
    
    public void cerrarConex(Connection conex){
        try {
            conex.close();
        } catch (SQLException ex) {
            Logger.getLogger(EstablishConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
