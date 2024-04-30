package com.aluracursos.conversordemonedas.servicios;

public class Calculador {

    //Este método calcular el valor a recibir de acuerdo con el monto ingresado y la tasa de conversión
    public double cambia(double valor, Double tasa){
        double cambio = Math.floor(valor * tasa);
        return cambio;
    }

}
