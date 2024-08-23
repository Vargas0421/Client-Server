package com.mycompany.proyectoclienteservidor.Modelo;

public class Cliente {

    private int id;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private int id_tipo;
    private double billetera;
    private int idDeMetodo;

    public int getIdDeMetodo() {
        return idDeMetodo;
    }

    public void setIdDeMetodo(int idDeMetodo) {
        this.idDeMetodo = idDeMetodo;
    }

    public Cliente() {
    }

    public int getId_tipo() {
        return id_tipo;
    }

    public void setId_tipo(int id_tipo) {
        this.id_tipo = id_tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBilletera() {
        return billetera;
    }

    public void setBilletera(double billetera) {
        this.billetera = billetera;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo + ", password=" + password + ", id_tipo=" + id_tipo + ", billetera=" + billetera + '}';
    }

}
