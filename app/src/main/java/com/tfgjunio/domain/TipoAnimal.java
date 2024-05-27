package com.tfgjunio.domain;

public class TipoAnimal {
    private long id;
    private String nombre;

    // Constructor sin id
    public TipoAnimal(String nombre) {
        this.nombre = nombre;
    }

    // Constructor con id
    public TipoAnimal(long id, String nombre) {
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

    @Override
    public String toString() {
        return nombre; // Esto asegurará que el nombre se muestre en el spinner
    }

}
