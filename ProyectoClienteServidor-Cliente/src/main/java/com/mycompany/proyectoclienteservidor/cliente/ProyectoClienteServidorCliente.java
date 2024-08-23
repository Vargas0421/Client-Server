package com.mycompany.proyectoclienteservidor.cliente;

import com.mycompany.proyectoclienteservidor.vista.Inicio;

public class ProyectoClienteServidorCliente {

    public static void main(String[] args) {
        ProyectoClienteServidorCliente clienteApp = new ProyectoClienteServidorCliente();
        clienteApp.iniciar();
    }
    
    public void iniciar() {
        Inicio inicio = new Inicio();
        inicio.setVisible(true);
    }
}
