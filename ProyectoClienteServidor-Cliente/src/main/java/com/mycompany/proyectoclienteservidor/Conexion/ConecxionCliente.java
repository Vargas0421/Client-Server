package com.mycompany.proyectoclienteservidor.Conexion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConecxionCliente {

    private Socket cliente;

    public ConecxionCliente() {

        System.out.println("Creando conexion...");
        try {
            cliente = new Socket("127.0.0.1", 6060);
        } catch (IOException ex) {
            Logger.getLogger(ConecxionCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String gestionarCliente(String json) throws IOException {
        DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
        dos.writeUTF(json);
        DataInputStream dis = new DataInputStream(cliente.getInputStream());
        String a;
        a = dis.readUTF();
        dos.close();
        dis.close();
        return a;
    }

    public void enviarInformacion(String informacinoParaEnviar) throws IOException {
        DataOutputStream dos = new DataOutputStream(cliente.getOutputStream());
        dos.writeUTF(informacinoParaEnviar);

    }

    public String iniciarConexion(String Jason) {

        String a = "";
        try {
            a = gestionarCliente(Jason);
            cliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return a;

    }

}
