package com.tfgjunio.domain;


import java.io.Serializable;

public class TipoAnimal implements Serializable {

    private Long id;
    private String nombre;

    public TipoAnimal(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public TipoAnimal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}