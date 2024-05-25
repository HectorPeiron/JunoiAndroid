package com.tfgjunio.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Animal implements Serializable {

    private Long id;
    private LocalDate fechaLlegada;
    private int numero;
    private int bajas;
    private String sexo;
    private BigDecimal peso;
    private TipoAnimal animalTipoAnimal;
    private Crianza animalCrianza;

    public Animal(Long id, LocalDate fechaLlegada, int numero, int bajas, String sexo, BigDecimal peso, TipoAnimal animalTipoAnimal, Crianza animalCrianza) {
        this.id = id;
        this.fechaLlegada = fechaLlegada;
        this.numero = numero;
        this.bajas = bajas;
        this.sexo = sexo;
        this.peso = peso;
        this.animalTipoAnimal = animalTipoAnimal;
        this.animalCrianza = animalCrianza;
    }

    public Animal() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDate fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getBajas() {
        return bajas;
    }

    public void setBajas(int bajas) {
        this.bajas = bajas;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public TipoAnimal getAnimalTipoAnimal() {
        return animalTipoAnimal;
    }

    public void setAnimalTipoAnimal(TipoAnimal animalTipoAnimal) {
        this.animalTipoAnimal = animalTipoAnimal;
    }

    public Crianza getAnimalCrianza() {
        return animalCrianza;
    }

    public void setAnimalCrianza(Crianza animalCrianza) {
        this.animalCrianza = animalCrianza;
    }
}
