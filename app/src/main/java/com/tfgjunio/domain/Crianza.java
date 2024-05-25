package com.tfgjunio.domain;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Crianza implements Serializable {

    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Animal crianzaAnimal;
    private List<Compra> crianzaCompra;
    private Veterinario crianzaVeterinario;

    public Crianza(Long id, LocalDate fechaInicio, LocalDate fechaFin, Animal crianzaAnimal, List<Compra> crianzaCompra, Veterinario crianzaVeterinario) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.crianzaAnimal = crianzaAnimal;
        this.crianzaCompra = crianzaCompra;
        this.crianzaVeterinario = crianzaVeterinario;
    }

    public Crianza() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Animal getCrianzaAnimal() {
        return crianzaAnimal;
    }

    public void setCrianzaAnimal(Animal crianzaAnimal) {
        this.crianzaAnimal = crianzaAnimal;
    }

    public List<Compra> getCrianzaCompra() {
        return crianzaCompra;
    }

    public void setCrianzaCompra(List<Compra> crianzaCompra) {
        this.crianzaCompra = crianzaCompra;
    }

    public Veterinario getCrianzaVeterinario() {
        return crianzaVeterinario;
    }

    public void setCrianzaVeterinario(Veterinario crianzaVeterinario) {
        this.crianzaVeterinario = crianzaVeterinario;
    }
}