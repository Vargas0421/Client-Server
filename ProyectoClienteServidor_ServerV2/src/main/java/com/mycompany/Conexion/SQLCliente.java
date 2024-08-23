package com.mycompany.Conexion;

import com.mycompany.Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLCliente extends ConexionMysql {

    ConexionMysql con = new ConexionMysql(); // se crean las variables necesarias para la coneccion
    Connection cn = con.getConection();

    public boolean registrar(Cliente cliente) {
        String sql = "INSERT INTO ususarios (password, nombre, apellido, correo, id_tipo) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = getConection();

            if (con != null) {
                ps = con.prepareStatement(sql);

                ps.setString(1, cliente.getPassword());
                ps.setString(2, cliente.getNombre());
                ps.setString(3, cliente.getApellido());
                ps.setString(4, cliente.getCorreo());
                ps.setInt(5, cliente.getId_tipo());
                ps.executeUpdate();
                return true;
            } else {
                Logger.getLogger(SQLCliente.class.getName()).log(Level.SEVERE, "Conexión a la base de datos no disponible.");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLCliente.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                Logger.getLogger(SQLCliente.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    public ResultSet obtenerClientes() {
        String sql = "SELECT id, nombre, apellido, correo FROM ususarios";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            if (cn != null) {
                ps = cn.prepareStatement(sql);
                rs = ps.executeQuery();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return rs;
    }
}
