/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectoclienteservidor.Modelo;

/**
 *
 * @author Brandon VM
 */
public class ItemCarrito {
    private Articulo articulo;
    private  int cantidad;

    

    public ItemCarrito(Articulo articulo) {
        this.articulo = articulo;
    }
    
    

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public ItemCarrito() {
    }
    
    
    
    
    
    
    
}
