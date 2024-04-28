package com.aluracursos.conversordemonedas.modelos;

import java.time.LocalDateTime;

public class Consulta {
    private Moneda monedaBase;
    private Moneda monedaTarget;
    private Double valorACambiar;
    private Double valorCambiado;
    private LocalDateTime fechaHora;
    private Double tasa;


    public Consulta(Moneda monedaBase, Moneda monedaTarget) {
        this.monedaBase = monedaBase;
        this.monedaTarget = monedaTarget;
        this.valorACambiar = null;
        this.valorCambiado = null;
        this.fechaHora = null;
        this.tasa = null;
    }

    public void setTasa(Double tasa) {
        this.tasa = tasa;
    }

    public void setValorACambiar(Double valorACambiar) {
        this.valorACambiar = valorACambiar;
    }

    public void setValorCambiado(Double valorCambiado) {
        this.valorCambiado = valorCambiado;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Moneda getMonedaBase() {
        return monedaBase;
    }

    public Moneda getMonedaTarget() {
        return monedaTarget;
    }

    public Double getValorACambiar() {
        return valorACambiar;
    }

    public Double getValorCambiado() {
        return valorCambiado;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Double getTasa() {
        return tasa;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "Fecha: " + fechaHora +
                "monedaBase=" + monedaBase.toString() +
                ", monedaTarget=" + monedaTarget.toString() +
                ", tasa=" + tasa +
                '}';
    }
}