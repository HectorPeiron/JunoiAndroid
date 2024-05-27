package com.tfgjunio.domain;

import java.time.LocalDate;

public class Baja {
    private long id;
    private LocalDate fecha;
    private int cantidad;
    private TipoBaja bajaTipoBaja;
    private Crianza bajaCrianza;

    public Baja(LocalDate fecha, int cantidad, TipoBaja bajaTipoBaja, Crianza bajaCrianza) {
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.bajaTipoBaja = bajaTipoBaja;
        this.bajaCrianza = bajaCrianza;
    }

    public Baja(long id, LocalDate fecha, int cantidad, TipoBaja bajaTipoBaja, Crianza bajaCrianza) {
        this.id = id;
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.bajaTipoBaja = bajaTipoBaja;
        this.bajaCrianza = bajaCrianza;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public TipoBaja getBajaTipoBaja() {
        return bajaTipoBaja;
    }

    public void setBajaTipoBaja(TipoBaja bajaTipoBaja) {
        this.bajaTipoBaja = bajaTipoBaja;
    }

    public Crianza getBajaCrianza() {
        return bajaCrianza;
    }

    public void setBajaCrianza(Crianza bajaCrianza) {
        this.bajaCrianza = bajaCrianza;
    }
}
