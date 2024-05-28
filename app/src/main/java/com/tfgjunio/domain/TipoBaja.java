package com.tfgjunio.domain;

public class TipoBaja {
    private long id;
    private String nombre;

    @Override
    public String toString() {
        return nombre; // Esto asegurará que el nombre se muestre en el spinner
    }

    // Constructor sin id
    public TipoBaja(String nombre) {
        this.nombre = nombre;
    }

    // Constructor con id
    public TipoBaja(long id, String nombre) {
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
