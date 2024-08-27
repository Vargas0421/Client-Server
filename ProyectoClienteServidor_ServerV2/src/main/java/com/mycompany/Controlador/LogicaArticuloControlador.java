/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import com.google.gson.Gson;
import com.mycompany.Conexion.ConexionMysql;
import com.mycompany.Conexion.SQLArticulo;
import com.mycompany.Modelo.Articulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogicaArticuloControlador {

    private static final Logger LOGGER = Logger.getLogger(LogicaArticuloControlador.class.getName());

    ConexionMysql con = new ConexionMysql(); // se crean las variables necesarias para la coneccion
    Connection cn = con.getConection();
    private Connection connection;

    public boolean registrarArticulo(String json) {
        Gson gson = new Gson();
        Articulo p = gson.fromJson(json, Articulo.class);

        String sql = "INSERT INTO articulos(nombre, cantidad, precio, tipo) VALUES(?,?,?,?)";
        PreparedStatement ps = null;

        try {
            if (cn != null) {
                ps = cn.prepareStatement(sql);
                ps.setString(1, p.getNombre());
                ps.setInt(2, p.getCantidad());
                ps.setDouble(3, p.getPrecio());
                ps.setString(4, p.getTipo());
                ps.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean procesarCompra(String json) {
        List<Articulo> listaArticulosEnElServidor = obtenerArticulosLista();
        List<Articulo> listaArticulosEnElCarrito = new LinkedList<>();

        // Parsear el JSON a una lista de artículos del carrito
        Gson gson = new Gson();
        Articulo[] articulos = gson.fromJson(json, Articulo[].class);
        for (Articulo articulo : articulos) {
            listaArticulosEnElCarrito.add(articulo);
        }

        // Validar si el carrito puede ser procesado con el inventario disponible
        if (!validarCompraCarritoDeCompras(listaArticulosEnElServidor, listaArticulosEnElCarrito)) {
            return false; // Validación fallida
        }

        // Actualizar inventario en la base de datos
        return actualizarInventario(listaArticulosEnElCarrito);
    }

    private boolean actualizarInventario(List<Articulo> articulos) {
        String sql = "UPDATE articulos SET cantidad = cantidad - ? WHERE id = ?";
        try (Connection connection = con.getConection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            connection.setAutoCommit(false); // Inicia la transacción

            for (Articulo articulo : articulos) {
                ps.setInt(1, articulo.getCantidad());
                ps.setInt(2, articulo.getId());
                ps.addBatch();
            }

            ps.executeBatch();
            connection.commit(); // Confirma la transacción
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            try (Connection connection = con.getConection()) {
                if (connection != null) {
                    connection.rollback(); // Revierte en caso de error
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
            return false;
        }
    }

    private boolean validarCompraCarritoDeCompras(List<Articulo> listaArticulosEnElServidor, List<Articulo> listaArticulosEnElCarrito) {
        for (Articulo articuloEnCarrito : listaArticulosEnElCarrito) {
            boolean encontrado = false;
            for (Articulo articuloEnServidor : listaArticulosEnElServidor) {
                if (articuloEnServidor.getId() == articuloEnCarrito.getId()) {
                    if (articuloEnServidor.getCantidad() < articuloEnCarrito.getCantidad()) {
                        LOGGER.log(Level.WARNING, "No hay suficiente inventario para el artículo con ID: " + articuloEnCarrito.getId());
                        return false;
                    }
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                LOGGER.log(Level.WARNING, "Artículo no encontrado en el inventario con ID: " + articuloEnCarrito.getId());
                return false;
            }
        }
        return true;
    }

    private List<Articulo> obtenerArticulosLista() {
        List<Articulo> listaArticulos = new ArrayList<>();
        String sql = "SELECT id, tipo, nombre, cantidad, precio FROM articulos";// se crea una sentencia 

        PreparedStatement ps = null;

        if (cn != null) {
            try {
                ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Articulo articulo = new Articulo();
                    articulo.setId(rs.getInt("id"));
                    articulo.setTipo(rs.getString("tipo"));
                    articulo.setNombre(rs.getString("nombre"));
                    articulo.setCantidad(rs.getInt("cantidad"));
                    articulo.setPrecio(rs.getDouble("precio"));
                    listaArticulos.add(articulo);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SQLArticulo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaArticulos;
    }

    public String jsonArticulos() {
        Gson gson = new Gson();
        String json = gson.toJson(obtenerArticulosLista());
        return json;
    }

    public boolean actualizarArticulo(String articulorecibido) {
        String sql = "UPDATE articulos SET tipo = ?, nombre = ?, cantidad = ?, precio = ? WHERE id = ?";

        if (cn == null) {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
            return false;
        }
        Gson json = new Gson();
        Articulo articulo = json.fromJson(articulorecibido, Articulo.class);

        try (PreparedStatement pst = cn.prepareStatement(sql)) {
            pst.setString(1, articulo.getTipo());
            pst.setString(2, articulo.getNombre());
            pst.setInt(3, articulo.getCantidad());
            pst.setDouble(4, articulo.getPrecio());
            pst.setInt(5, articulo.getId());

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el artículo: " + e.getMessage());
            return false;
        }
    }

    private List<Articulo> obtenerArticulosTipo(String tipo) {//este metodo se crea para obtener los articulos por el tipo de articulo
        List<Articulo> listaArticulos = new ArrayList<>();
        String sql = "SELECT id, tipo, nombre, cantidad, precio FROM articulos WHERE tipo = ?"; // acaa se busca por medio del tipo y lo que se hace es que para la funcino es necseario un String el cual se declara en el llamado de la funcino en la vista
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (cn != null) {
                ps = cn.prepareStatement(sql);
                ps.setString(1, tipo); //aca se setea el parametroa  utilizar
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                Articulo articulo = new Articulo();
                articulo.setId(rs.getInt("id"));
                articulo.setTipo(rs.getString("tipo"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setCantidad(rs.getInt("cantidad"));
                articulo.setPrecio(rs.getDouble("precio"));
                listaArticulos.add(articulo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLArticulo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaArticulos;
    }

    public String jsonArticulosTipo(String tipo) {
        Gson gson = new Gson();
        String json = gson.toJson(obtenerArticulosTipo(tipo));
        return json;
    }

}
