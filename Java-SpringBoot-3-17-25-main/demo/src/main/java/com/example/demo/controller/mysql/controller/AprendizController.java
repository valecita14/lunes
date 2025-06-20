package com.example.demo.controller.mysql.controller;

import org.springframework.web.bind.annotation.*;
import com.example.demo.controller.mysql.Conexion;
import com.example.demo.controller.mysql.model.Aprendiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/aprendiz")
public class AprendizController {

    private Conexion conexion = new Conexion();

    @GetMapping
    public List<Aprendiz> obtenerAprendices() {
        List<Aprendiz> aprendices = new ArrayList<>();
        try (Connection conn = conexion.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM aprendiz")) {

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nombre = rs.getString("nombre");
                String telefono = rs.getString("telefono");
                aprendices.add(new Aprendiz(id, nombre, telefono));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aprendices;
    }

    @GetMapping("/{id}")
    public Aprendiz obtenerAprendiz(@PathVariable Long id) {
        Aprendiz aprendiz = null;
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM aprendiz WHERE id = ?")) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String telefono = rs.getString("telefono");
                    aprendiz = new Aprendiz(id, nombre, telefono);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aprendiz;
    }

    @PostMapping
    public String crearAprendiz(@RequestBody Aprendiz aprendiz) {
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO aprendiz (nombre, telefono) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, aprendiz.getNombre());
            stmt.setString(2, aprendiz.getTelefono());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return "Aprendiz creado con ID: " + generatedKeys.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error al crear el aprendiz";
    }

    @PutMapping("/{id}")
    public String actualizarAprendiz(@PathVariable Long id, @RequestBody Aprendiz aprendiz) {
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement("UPDATE aprendiz SET nombre = ?, telefono = ? WHERE id = ?")) {
            stmt.setString(1, aprendiz.getNombre());
            stmt.setString(2, aprendiz.getTelefono());
            stmt.setLong(3, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                return "Aprendiz actualizado con ID: " + id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error al actualizar el aprendiz";
    }

    @DeleteMapping("/{id}")
    public String eliminarAprendiz(@PathVariable Long id) {
        try (Connection conn = conexion.conectar();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM aprendiz WHERE id = ?")) {
            stmt.setLong(1, id);
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                return "Aprendiz eliminado con ID: " + id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Error al eliminar el aprendiz";
    }
}

