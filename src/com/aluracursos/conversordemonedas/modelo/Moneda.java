package com.aluracursos.conversordemonedas.modelo;

public class Moneda {

    private String sigla;
    private String nombreCompleto;
    private String simbolo;

    public Moneda(){};

    public Moneda(String sigla, String nombreCompleto, String simbolo) {
        this.sigla = sigla;
        this.nombreCompleto = nombreCompleto;
        this.simbolo = simbolo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSímbolo(String símbolo) {
        this.simbolo = símbolo;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @Override
    public String toString() {
        return "Moneda: (" + sigla + ") " + nombreCompleto + " (Símbolo: " + simbolo + ").";
    }
}
