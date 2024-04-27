package com.aluracursos.conversordemonedas.modelos;

public class Consulta {
    private Moneda monedaBase;
    private Moneda monedaTarget;
    private Double valorACambiar;
    private Double valorCambiado;
    private Double tasa;


    public Consulta(Moneda monedaBase, Moneda monedaTarget) {
        this.monedaBase = monedaBase;
        this.monedaTarget = monedaTarget;
        this.valorACambiar = null;
        this.valorCambiado = null;
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

    public Double getTasa() {
        return tasa;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "monedaBase=" + monedaBase.toString() +
                ", monedaTarget=" + monedaTarget.toString() +
                ", tasa=" + tasa +
                '}';
    }
}
