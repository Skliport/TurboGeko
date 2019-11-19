/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.RC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Eli
 */
public class Conexion {

    Connection con = null;

    public Connection getConnection() throws ClassNotFoundException {

        try {
            Class.forName("org.postgresql.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:postgresql://localhost:5432/TurboGekoFinalVI",
                    "postgres", "postgres");
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Error; " + ex);
        }
        return con;
    }
}
