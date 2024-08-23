package com.mycompany.proyectoclienteservidor_serverv2;

import com.mycompany.Conexion.ConexionServidor;

public class ProyectoClienteServidor_ServerV2 {

    public static void main(String[] args) {
        ConexionServidor servidor = new ConexionServidor();
        servidor.iniciarSesion();
    }
}
