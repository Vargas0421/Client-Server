/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Controlador;

import com.google.gson.Gson;
import com.mycompany.Conexion.ConexionMysql;
import com.mycompany.Conexion.SQLCliente;
import com.mycompany.Modelo.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class LogicaClienteControlador {

    DefaultTableModel modelo = new DefaultTableModel();
    ConexionMysql con = new ConexionMysql(); // se crean las variables necesarias para la coneccion
    Connection cn = con.getConection();
    private Connection connection;

    private List<Cliente> obtenerClientesLista() {
        List<Cliente> listaClientes = new ArrayList<>();
        String sql = "SELECT id, nombre, apellido, correo, billetera FROM ususarios";
        PreparedStatement ps = null;

        if (cn != null) {
            try {
                ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setBilletera(rs.getDouble("billetera"));
                    listaClientes.add(cliente);
                }
            } catch (SQLException ex) {
                Logger.getLogger(SQLCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listaClientes;
    }

    public String jsonClientes() {
        Gson gson = new Gson();
        String json = gson.toJson(obtenerClientesLista());
        return json;
    }

   /* public String mantenerClienteDuranteLaSesion(String json, Cliente c) {
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(json, Cliente.class);
        String clienteRegistrado = null;

        return clienteRegistrado;
    }

    public Cliente eviarClienteAestion(String json) {
        Cliente a = validacionDeSesion(json);

        return a;
    }*/
    
    

    public void registrarUsuario(String json) {
        SQLCliente modSql = new SQLCliente();
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(json, Cliente.class);

        if (modSql.registrar(cliente)) {
            JOptionPane.showMessageDialog(null, "Registro guardado");
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar");
        }
    }

    public String devolverClienteRegistradoAlCliente(Cliente cliente) {
        Gson gson = new Gson();
        String json = gson.toJson(cliente); 
        return json;
    }

    private Cliente validacionDeSesion(String json) {
        String sql = "SELECT id, nombre, apellido, correo, id_tipo, billetera FROM ususarios WHERE correo = ? AND password = ?";
        SQLCliente modSql = new SQLCliente();
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(json, Cliente.class);

        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, cliente.getCorreo());
            ps.setString(2, cliente.getPassword());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setId_tipo(rs.getInt("id_tipo"));
                    cliente.setBilletera(rs.getDouble("billetera"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LogicaClienteControlador.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cliente;
    }

    public Cliente pasarCliente(String a) {
        Gson gson = new Gson();
        Cliente cliente = gson.fromJson(a, Cliente.class);
        return cliente;
    }

    public String jsonValidacionCliente(String jason) {
        Gson gson = new Gson();
        String json = gson.toJson(validacionDeSesion(jason));
        return json;
    }

}
