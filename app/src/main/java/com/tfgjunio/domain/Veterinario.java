package com.tfgjunio.domain;


import java.io.Serializable;

public class Veterinario implements Serializable {

    private long id;
    private String nombre;
    private String especialidad;
    private String telefono;
    private String email;
    private Crianza veterinarioCrianza;

    public Veterinario(long id, String nombre, String especialidad, String telefono, String email, Crianza veterinarioCrianza) {
        this.id = id;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
        this.veterinarioCrianza = veterinarioCrianza;
    }

    public Veterinario(String nombre, String especialidad, String telefono, String email, Crianza veterinarioCrianza) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.telefono = telefono;
        this.email = email;
        this.veterinarioCrianza = veterinarioCrianza;
    }

    public Veterinario() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Crianza getVeterinarioCrianza() {
        return veterinarioCrianza;
    }

    public void setVeterinarioCrianza(Crianza veterinarioCrianza) {
        this.veterinarioCrianza = veterinarioCrianza;
    }
}