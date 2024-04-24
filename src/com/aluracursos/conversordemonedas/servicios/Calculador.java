package com.aluracursos.conversordemonedas.servicios;

public class Calculador {

    public double cambia(double valor, Double tasa){
        double cambio = Math.floor(valor * tasa);
        return cambio;
    }



}
