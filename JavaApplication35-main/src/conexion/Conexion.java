/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;
import java.sql.*;
/**
 *
 * @author Whiteraven
 */
public class Conexion {
    public static final String url="jdbc:mysql://localhost:3306/universidad";
    public static final String user="root";
    public static final String pass="1234";
    
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,user,pass);
    }
    
    public void insertarDato(String nombre){
    String sql="INSERT INTO estudiante(nombre) VALUES(?)";
        try (Connection conn=getConnection();
                PreparedStatement ps=conn.prepareStatement(sql);) {
            ps.setString(1,nombre);
            ps.executeUpdate();
            System.out.println("Dato guardado");
            
        } catch (SQLException e) {
            System.out.println("Errores: "+e);
        }
        
    }
    
}
