package conexion;

import java.sql.*;

public class Conexion {

    private Connection con;

    public Conexion() {
        try {
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/crudjava",
                "root",
                "adrian0704" // 
            );
            System.out.println("Conectado");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public Connection getConexion() {
        return con;
    }

    public void insertarDato(String nombre) {
        try {
            String sql = "INSERT INTO personas(nombre) VALUES(?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void actualizarDato(int id, String nombre) {
        try {
            String sql = "UPDATE personas SET nombre=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void eliminarDato(int id) {
        try {
            String sql = "DELETE FROM personas WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}