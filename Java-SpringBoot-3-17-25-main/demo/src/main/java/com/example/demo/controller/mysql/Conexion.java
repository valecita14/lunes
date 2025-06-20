package com.example.demo.controller.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private String url = "jdbc:mysql://localhost:3306/api"; // URL de la base de datos
    private String usuario = "root"; // Usuario de la base de datos
    private String contraseña = ""; // Contraseña de la base de datos
    private Connection conexion;

    public Connection conectar() {
        try {
            if (conexion == null || conexion.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(url, usuario, contraseña);
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
        }
        return conexion;
    }

    public void desconectar() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al desconectar: " + e.getMessage());
        }
    }


}
