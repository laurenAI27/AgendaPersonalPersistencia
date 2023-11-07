package com.example.agendapersonalpersistencia;

import java.io.Serializable;

public class Contacto implements Serializable {

    //Creación de los atributos de la clase
    private int id;
    private String nombre;
    private String movil;
    private String email;

    //Constructor de la clase con los 4 parámetros
    public Contacto(int id, String nombre, String movil, String email) {
        this.id = id;
        this.nombre = nombre;
        this.movil = movil;
        this.email = email;
    }

    public Contacto() {
    }

    //Generación de Getters y Setters para llamar a los atributos del constructor
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

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
