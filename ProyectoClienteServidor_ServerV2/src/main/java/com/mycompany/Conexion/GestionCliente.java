package com.mycompany.Conexion;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mycompany.Controlador.LogicaArticuloControlador;
import com.mycompany.Controlador.LogicaClienteControlador;
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
    //private DataInputStream dis;
    //private DataOutputStream dos;

    public GestionCliente(Socket cliente) {
        this.cliente = cliente;
        //this.dis = new DataInputStream(cliente.getInputStream());
        //this.dos = new DataOutputStream(cliente.getOutputStream());
        // this.logicaClienteControlador = new LogicaClienteControlador();
        //this.logicaArticuloControlador = new LogicaArticuloControlador();
    }

    public String procesar(String inputMe) {

        Gson gson = new Gson();
        JsonObject jsonObj = gson.fromJson(inputMe, JsonObject.class);
        int idDeMetodo = jsonObj.get("idDeMetodo").getAsInt();
        switch (idDeMetodo) {
            case 1://Funcion para registar un cliente
                logicaClienteControlador.registrarUsuario(inputMe);
                break;
            case 2:
                logicaArticuloControlador.actualizarArticulo(inputMe);
            case 3:
                logicaArticuloControlador.registrarArticulo(inputMe);
                break;
            case 4:
                return logicaArticuloControlador.jsonArticulos();
            case 5:
                return logicaArticuloControlador.jsonArticulos();
            case 6:
                return logicaClienteControlador.jsonValidacionCliente(inputMe);
            case 7:
                //JOptionPane.showMessageDialog(null, logicaClienteControlador.jsonClientes());
                return logicaClienteControlador.jsonClientes();

        }
        cerrarRecursos();
        return "";
    }

    private String recibirMensaje(String a) throws IOException {
        return a;
    }

    private void enviarMensaje(String mensaje) throws IOException {
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
            dis = new DataInputStream(this.cliente.getInputStream());
            String inputM = dis.readUTF();
            String respond = procesar(inputM);
            DataOutputStream dos = new DataOutputStream(this.cliente.getOutputStream());
            dos.writeUTF(respond);
            dos.close();
            dis.close();
        } catch (IOException ex) {
            Logger.getLogger(GestionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
