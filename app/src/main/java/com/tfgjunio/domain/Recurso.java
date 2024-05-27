package com.tfgjunio.domain;

public class Recurso {
    private long id;
    private String nombre;
    private float cantidad;
    private float precio;
    private String proveedor;
    private String descripcion;
    private TipoRecurso tipoRecurso;
    private TipoAnimal recursoTipoAnimal;
    private Unidad recursoUnidad;

    // Constructor sin id
    public Recurso(String nombre, float cantidad, float precio, String proveedor, String descripcion, TipoRecurso tipoRecurso, TipoAnimal recursoTipoAnimal, Unidad recursoUnidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.proveedor = proveedor;
        this.descripcion = descripcion;
        this.tipoRecurso = tipoRecurso;
        this.recursoTipoAnimal = recursoTipoAnimal;
        this.recursoUnidad = recursoUnidad;
    }

    // Constructor con id
    public Recurso(long id, String nombre, float cantidad, float precio, String proveedor, String descripcion, TipoRecurso tipoRecurso, TipoAnimal recursoTipoAnimal, Unidad recursoUnidad) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.proveedor = proveedor;
        this.descripcion = descripcion;
        this.tipoRecurso = tipoRecurso;
        this.recursoTipoAnimal = recursoTipoAnimal;
        this.recursoUnidad = recursoUnidad;
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

    public TipoRecurso getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(TipoRecurso tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
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
