package com.mycompany.Conexion;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycompany.Controlador.LogicaArticuloControlador;
import com.mycompany.Controlador.LogicaClienteControlador;
import com.mycompany.Modelo.Cliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GestionCliente extends Thread {

    private Socket cliente;
    private LogicaClienteControlador logicaClienteControlador = new LogicaClienteControlador();
    private LogicaArticuloControlador logicaArticuloControlador = new LogicaArticuloControlador();
    private final Object lock = new Object(); // Objeto de bloqueo para sincronización
    Cliente clienteRegistrado;

    public GestionCliente(Socket cliente) {
        this.cliente = cliente;

    }

    public GestionCliente(Socket cliente, Cliente cliente1) {
        this.cliente = cliente;
        this.clienteRegistrado = cliente1;

    }

    public String procesar(String inputMe) {
        Gson gson = new Gson();
        JsonObject jsonObj;
        int idDeMetodo;
        String jsonData = "";

            if (inputMe.contains("|")) {
                String[] partes = inputMe.split("\\|", 2);
                idDeMetodo = Integer.parseInt(partes[0].trim());
                jsonData = partes[1].trim();
            } else {
                jsonObj = gson.fromJson(inputMe, JsonObject.class);
                idDeMetodo = jsonObj.get("idDeMetodo").getAsInt();
                jsonData = inputMe; // El JSON completo es el inputMe
            }

            switch (idDeMetodo) {
                case 1:
                    logicaClienteControlador.registrarUsuario(inputMe);
                    break;
                case 2:
                    logicaArticuloControlador.actualizarArticulo(inputMe);
                    break;
                case 3:
                    logicaArticuloControlador.registrarArticulo(inputMe);
                    break;
                case 4:
                    return logicaArticuloControlador.jsonArticulos();
                case 5:
                    return logicaArticuloControlador.jsonArticulos();
                case 6:
                    String a = logicaClienteControlador.jsonValidacionCliente(inputMe);
                    clienteRegistrado = gson.fromJson(a, Cliente.class); // Actualiza el cliente registrado
                    JOptionPane.showMessageDialog(null, "El cliente registrado es (caso 6): " + clienteRegistrado.toString());
                    return a;
                case 7:
                    return logicaClienteControlador.jsonClientes();
                case 8:
                    JOptionPane.showMessageDialog(null, "El cliente registrado es (caso 8): " + clienteRegistrado.toString());
                    return logicaClienteControlador.devolverClienteRegistradoAlCliente(clienteRegistrado);
                case 9:
                    if (logicaArticuloControlador.procesarCompra(jsonData)) {
                        return "Success";
                    }
                    break;
                default:
                    // Manejo de casos no definidos
                    return "Método no soportado";
            }
        

        cerrarRecursos();
        return "";
    }

    public void cerrarRecursos() {
        try {
            if (cliente != null && !cliente.isClosed()) {
                cliente.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        DataInputStream dis;
        try {
            while (true) {
                dis = new DataInputStream(this.cliente.getInputStream());
                String inputM = dis.readUTF();
                String respond = procesar(inputM);
                DataOutputStream dos = new DataOutputStream(this.cliente.getOutputStream());
                dos.writeUTF(respond);
                dis.close();
                dos.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarRecursos();
        }
    }

}
