package com.tfgjunio.domain;


import java.io.Serializable;

public class Recurso implements Serializable {

    private Long id;
    private String nombre;
    private float cantidad;
    private float precio;
    private String proveedor;
    private String descripcion;
    private TipoRecurso recursoTipoRecurso;
    private TipoAnimal recursoTipoAnimal;
    private Unidad recursoUnidad;

    public Recurso(Long id, String nombre, float cantidad, float precio, String proveedor, String descripcion, TipoRecurso recursoTipoRecurso, TipoAnimal recursoTipoAnimal, Unidad recursoUnidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.proveedor = proveedor;
        this.descripcion = descripcion;
        this.recursoTipoRecurso = recursoTipoRecurso;
        this.recursoTipoAnimal = recursoTipoAnimal;
        this.recursoUnidad = recursoUnidad;
    }

    public Recurso() {
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

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoRecurso getRecursoTipoRecurso() {
        return recursoTipoRecurso;
    }

    public void setRecursoTipoRecurso(TipoRecurso recursoTipoRecurso) {
        this.recursoTipoRecurso = recursoTipoRecurso;
    }

    public TipoAnimal getRecursoTipoAnimal() {
        return recursoTipoAnimal;
    }

    public void setRecursoTipoAnimal(TipoAnimal recursoTipoAnimal) {
        this.recursoTipoAnimal = recursoTipoAnimal;
    }

    public Unidad getRecursoUnidad() {
        return recursoUnidad;
    }

    public void setRecursoUnidad(Unidad recursoUnidad) {
        this.recursoUnidad = recursoUnidad;
    }
}