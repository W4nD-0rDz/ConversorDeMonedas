package com.aluracursos.conversordemonedas.modelos;

public class Moneda {

    private String monedaBase;
    private String monedaSalida;

    public Moneda(String monedaBase, String monedaSalida) {
        this.monedaBase = monedaBase;
        this.monedaSalida = monedaSalida;
    }

    public String getMonedaBase() {
        return monedaBase;
    }

    public void setMonedaBase(String monedaBase) {
        this.monedaBase = monedaBase;
    }

    public String getMonedaSalida() {
        return monedaSalida;
    }

    public void setMonedaSalida(String monedaSalida) {
        this.monedaSalida = monedaSalida;
    }

    @Override
    public String toString() {
        return "Moneda{" +
                "monedaBase='" + monedaBase + '\'' +
                ", monedaSalida='" + monedaSalida + '\'' +
                '}';
    }
}
