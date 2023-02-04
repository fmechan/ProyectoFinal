/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AccesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Conexion {
    
    private static final String URL = "jdbc:postgresql://localhost:5433/dbVentaAuto";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "usat2023";
    
    private static Connection conexion;
    
    public Connection getConexion(){
        if (conexion == null) {
            try{

                Class.forName("org.postgresql.Driver");
                conexion = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return conexion;
    }
    
    public static void cerrar() throws SQLException{
        if (conexion != null) {
            conexion.close();
        }
    }
    
}
