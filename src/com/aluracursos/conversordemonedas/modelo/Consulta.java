package com.aluracursos.conversordemonedas.modelo;

import java.time.LocalDateTime;

public class Consulta {
    private Moneda monedaBase;
    private Moneda monedaTarget;
    private Double valorACambiar;
    private Double valorCambiado;
    private LocalDateTime fechaHora;
    private Double tasa;

    public Consulta() {
        this.monedaBase = null;
        this.monedaTarget = null;
        this.valorACambiar = null;
        this.valorCambiado = null;
        this.fechaHora = null;
        this.tasa = null;
    }

    public Consulta(Moneda monedaBase, Moneda monedaTarget) {
        this.monedaBase = monedaBase;
        this.monedaTarget = monedaTarget;
        this.valorACambiar = null;
        this.valorCambiado = null;
        this.fechaHora = null;
        this.tasa = null;
    }

    public void setMonedaBase(Moneda monedaBase) {
        this.monedaBase = monedaBase;
    }

    public void setMonedaTarget(Moneda monedaTarget) {
        this.monedaTarget = monedaTarget;
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
        return "Consulta [Base: " + monedaBase.toString() + "; Target: " + monedaTarget.toString() + "; tipo de cambio:" + tasa + "]" +
                "\n [Monto base: " + monedaBase.getSimbolo() + valorACambiar + "; Monto Target: " + monedaTarget.getSimbolo() + valorCambiado + "]";
    }
}
