package com.mycompany.Conexion;

import com.mycompany.Modelo.Articulo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLArticulo extends ConexionMysql {

    public SQLArticulo() {
    }
// esta clase es la que extiende o hereda de la coneccion SWL

    ConexionMysql con = new ConexionMysql(); // se crean las variables necesarias para la coneccion
    Connection cn = con.getConection();

    public ResultSet obtenerArticulosTipo(String tipo) {//este metodo se crea para obtener los articulos por el tipo de articulo
        String sql = "SELECT id, tipo, nombre, cantidad, precio FROM articulos WHERE tipo = ?"; // acaa se busca por medio del tipo y lo que se hace es que para la funcino es necseario un String el cual se declara en el llamado de la funcino en la vista
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (cn != null) {
                ps = cn.prepareStatement(sql);
                ps.setString(1, tipo); //aca se setea el parametroa  utilizar
                rs = ps.executeQuery();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rs;
    }

    public boolean actualizarArticulo(Articulo articulo, int id) {// mediante esta funcion se actualiza la informacion de un articulo
        String sql = "UPDATE articulos SET tipo = ?, nombre = ?, cantidad = ?, precio = ? WHERE id = ?"; // esto se realiza meiante el id

        if (cn == null) { //si no hay conecion se indica que la coneccino no fue exitosa
            System.out.println("No se pudo establecer la conexión a la base de datos.");
            return false;
        }

        try (PreparedStatement pst = cn.prepareStatement(sql)) {// se crea el statement con el parametro sql
            pst.setString(1, articulo.getTipo()); // se setean los valores de cada parametro o cada ?
            pst.setString(2, articulo.getNombre());
            pst.setInt(3, articulo.getCantidad());
            pst.setDouble(4, articulo.getPrecio());
            pst.setInt(5, id);// aca se pasa el id para comparar al momento de actulizar por el click del mouse 

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el artículo: " + e.getMessage());
            return false;
        }
    }

}
