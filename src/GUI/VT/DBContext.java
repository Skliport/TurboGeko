/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.VT;
import java.sql.*;
/**
 *
 * @author bryan
 */
public class DBContext {
    Connection Conn;
    String url ="jdbc:postgresql://localhost:5433/TurboGeko";

    public DBContext() {
        try{
            Class.forName("org.postgresql.Driver");
            Conn= DriverManager.getConnection(url,"postgres","postgres");
            System.out.println("Exito");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
}
