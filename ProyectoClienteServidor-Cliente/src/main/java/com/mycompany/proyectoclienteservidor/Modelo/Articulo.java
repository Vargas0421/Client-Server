package com.mycompany.proyectoclienteservidor.Modelo;

public class Articulo {// en esta clase se define el modelo edel articulo

    private String nombre;
    private int id;
    private int cantidad;
    private Double precio;
    private String tipo;
    private int idDeMetodo;

    public int getIdDeMetodo() {
        return idDeMetodo;
    }

    public void setIdDeMetodo(int idDeMetodo) {
        this.idDeMetodo = idDeMetodo;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Articulo() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
