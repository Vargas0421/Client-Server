package com.mycompany.Conexion;

import com.mycompany.Modelo.Cliente;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionServidor {

    private ServerSocket server;
    private List<GestionCliente> gestiones = new ArrayList<>();

    public ConexionServidor() {
        try {
            server = new ServerSocket(6060);
        } catch (IOException ex) {
            Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void ingresarGestion(GestionCliente e){
        gestiones.add(e);
    }

    public void iniciarSesion() {
        while (true) {
            try {
                Socket s1 = this.server.accept();
                GestionCliente gestion = new GestionCliente(s1);
                gestion.start();
                ingresarGestion(gestion);
            } catch (IOException ex) {
                Logger.getLogger(ConexionServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
