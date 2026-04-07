package javaapplication35;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Formulario extends JFrame {

    Connection con;

    JTextField txtId, txtNombre;
    JTable tabla;
    DefaultTableModel modelo;

    public Formulario() {

        // 🔌 CONEXIÓN
        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/crudjava",
                    "root",
                    "adrian0704" // cambia tu contraseña
            );
            System.out.println("Conectado");
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }

        setTitle("CRUD JAVA");
        setSize(450, 420);
        setLayout(null);

        // CAMPOS
        JLabel lblId = new JLabel("ID:");
        lblId.setBounds(20, 20, 50, 20);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(80, 20, 100, 25);
        add(txtId);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 60, 70, 20);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(80, 60, 250, 25);
        add(txtNombre);

        // TABLA
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(20, 200, 390, 150);
        add(scroll);

        // BOTONES
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(20, 110, 100, 30);
        add(btnGuardar);

        JButton btnMostrar = new JButton("Mostrar");
        btnMostrar.setBounds(130, 110, 100, 30);
        add(btnMostrar);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(240, 110, 100, 30);
        add(btnBuscar);

        JButton btnModificar = new JButton("Modificar");
        btnModificar.setBounds(20, 150, 120, 30);
        add(btnModificar);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(150, 150, 100, 30);
        add(btnEliminar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(260, 150, 100, 30);
        add(btnLimpiar);

        // 🟢 GUARDAR
        btnGuardar.addActionListener(e -> {
            try {
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO personas(nombre) VALUES(?)"
                );
                ps.setString(1, txtNombre.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Guardado");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        // 🔵 MOSTRAR (LLENA TABLA)
        btnMostrar.addActionListener(e -> {
            try {
                modelo.setRowCount(0);

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM personas");

                while (rs.next()) {
                    Object[] fila = {
                        rs.getInt("id"),
                        rs.getString("nombre")
                    };
                    modelo.addRow(fila);
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        // 🔍 BUSCAR
        btnBuscar.addActionListener(e -> {
            try {
                modelo.setRowCount(0);

                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM personas WHERE id=?"
                );
                ps.setInt(1, Integer.parseInt(txtId.getText()));
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    Object[] fila = {
                        rs.getInt("id"),
                        rs.getString("nombre")
                    };
                    modelo.addRow(fila);
                } else {
                    JOptionPane.showMessageDialog(null, "No encontrado");
                }

            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        // 🟡 MODIFICAR
        btnModificar.addActionListener(e -> {
            try {
                PreparedStatement ps = con.prepareStatement(
                        "UPDATE personas SET nombre=? WHERE id=?"
                );
                ps.setString(1, txtNombre.getText());
                ps.setInt(2, Integer.parseInt(txtId.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Actualizado");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        // 🔴 ELIMINAR
        btnEliminar.addActionListener(e -> {
            try {
                PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM personas WHERE id=?"
                );
                ps.setInt(1, Integer.parseInt(txtId.getText()));
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Eliminado");
            } catch (Exception ex) {
                System.out.println(ex);
            }
        });

        // ⚪ LIMPIAR
        btnLimpiar.addActionListener(e -> {
            txtId.setText("");
            txtNombre.setText("");
            modelo.setRowCount(0);
        });

        // 🔥 CLICK EN TABLA
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tabla.getSelectedRow();
                txtId.setText(tabla.getValueAt(fila, 0).toString());
                txtNombre.setText(tabla.getValueAt(fila, 1).toString());
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Formulario();
    }
}