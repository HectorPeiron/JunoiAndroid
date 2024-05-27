package com.tfgjunio.domain;

public class TipoRecurso {
    private long id;
    private String nombre;

    // Constructor sin id
    public TipoRecurso(String nombre) {
        this.nombre = nombre;
    }

    // Constructor con id
    public TipoRecurso(long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
}
