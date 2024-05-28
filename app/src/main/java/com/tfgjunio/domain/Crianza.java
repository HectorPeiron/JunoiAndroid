package com.tfgjunio.domain;

import java.time.LocalDate;
import java.util.List;

public class Crianza {
    private long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Animal> crianzaAnimal;
    private List<Compra> crianzaCompra;
    private List<Baja> crianzaBaja;

    // Constructor completo con id
    public Crianza(long id, LocalDate fechaInicio, LocalDate fechaFin, List<Animal> crianzaAnimal, List<Compra> crianzaCompra, List<Baja> crianzaBaja) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.crianzaAnimal = crianzaAnimal;
        this.crianzaCompra = crianzaCompra;
        this.crianzaBaja = crianzaBaja;
    }

    // Constructor solo con fecha de inicio
    public Crianza(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    // Constructor solo con fecha de finalización
    public Crianza(long id, LocalDate fechaFin) {
        this.id = id;
        this.fechaFin = fechaFin;
    }

    public Crianza() {

    }

    public Crianza(long crianzaId) {
    }

    // Getters y setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Animal> getCrianzaAnimal() {
        return crianzaAnimal;
    }

    public void setCrianzaAnimal(List<Animal> crianzaAnimal) {
        this.crianzaAnimal = crianzaAnimal;
    }

    public List<Compra> getCrianzaCompra() {
        return crianzaCompra;
    }

    public void setCrianzaCompra(List<Compra> crianzaCompra) {
        this.crianzaCompra = crianzaCompra;
    }

    public List<Baja> getCrianzaBaja() {
        return crianzaBaja;
    }

    public void setCrianzaBaja(List<Baja> crianzaBaja) {
        this.crianzaBaja = crianzaBaja;
    }
}
