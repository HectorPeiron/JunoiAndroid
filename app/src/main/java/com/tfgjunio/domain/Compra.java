package com.tfgjunio.domain;

import java.time.LocalDate;
import java.util.List;

public class Compra {
    private long id;
    private LocalDate fechaCompra;
    private String descripcion;
    private List<Recurso> compraRecurso;
    private Crianza compraCrianza;

    public Compra(LocalDate fechaCompra, String descripcion, List<Recurso> compraRecurso, Crianza compraCrianza) {
        this.fechaCompra = fechaCompra;
        this.descripcion = descripcion;
        this.compraRecurso = compraRecurso;
        this.compraCrianza = compraCrianza;
    }

    public Compra(long id, LocalDate fechaCompra, String descripcion, List<Recurso> compraRecurso, Crianza compraCrianza) {
        this.id = id;
        this.fechaCompra = fechaCompra;
        this.descripcion = descripcion;
        this.compraRecurso = compraRecurso;
        this.compraCrianza = compraCrianza;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Recurso> getCompraRecurso() {
        return compraRecurso;
    }

    public void setCompraRecurso(List<Recurso> compraRecurso) {
        this.compraRecurso = compraRecurso;
    }

    public Crianza getCompraCrianza() {
        return compraCrianza;
    }

    public void setCompraCrianza(Crianza compraCrianza) {
        this.compraCrianza = compraCrianza;
    }
}
