package GUI.MT;

import java.sql.*;


public class ConexionBD {
    private final String SERVER = "jdbc:postgresql://localhost:5432/puu";
    private final String USER = "postgres";
    private final String PASS = "Martinez8305";
    private final String DRIVER = "org.postgresql.Driver";
    private Connection CONEX = null;

    public ConexionBD() {
        try {
            Class.forName(DRIVER);
            CONEX = DriverManager.getConnection(SERVER, USER, PASS);
            
            System.out.println("Conexion iniciada con exito");
        } catch (ClassNotFoundException | SQLException e) {
            //Aquí devolvemos el mensaje de que fallo la conexion
            System.out.println("Error al conectar " + e);
        }
    }
    
    public void close() {
        try {
            CONEX.close();
            System.out.println("Conexion cerrada");
        } catch (SQLException e) {
            System.out.println("Error al cerra conexion " + e);
        }
    }

    public Connection getConnection() {
        return CONEX;
    }

}
