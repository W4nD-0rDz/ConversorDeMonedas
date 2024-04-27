package com.aluracursos.conversordemonedas.servicios;

import com.aluracursos.conversordemonedas.modelos.Consulta;
import com.aluracursos.conversordemonedas.modelos.Moneda;

import java.util.List;
import java.util.Map;

public class Impresor {
    Calculador calculo = new Calculador();

//    public static <T> void muestraLista(List<T> lista) {
//        System.out.println("Lista:");
//        for (T elemento : lista) {
//            System.out.println(elemento.toString());
//        }
//    }
//
//    public static <T> void mostrarLista(List<T> lista) {
//        System.out.println("Lista:");
//        for (T elemento : lista) {
//            System.out.println(elemento.toString());
//        }
//    }

    public static void muestraMapa(Map<Integer, String[]> mapa) {
        System.out.println("Mapa de Divisas:");
        for (Map.Entry<Integer, String[]> entrada : mapa.entrySet()) {
            Integer indice = entrada.getKey();
            String[] infoDivisa = entrada.getValue();
            System.out.println(indice + " - " + infoDivisa[0] + " - " + infoDivisa[1]);
        }
        System.out.println(" ");
    }

    public void infoCambio(Moneda monedaBase, Moneda monedaTarget, double tasa, double valor){
        System.out.println("La tasa de conversión de " + monedaBase.getNombreCompleto() + " al " + monedaTarget.getNombreCompleto() + " es: "+ tasa);
        System.out.println("Ingrese el monto a cambiar");
        System.out.println(monedaBase.getSimbolo() + valor + " equivale a " + monedaTarget.getSimbolo() + calculo.cambia(valor,tasa));
    }

    public void infoCambioII(Consulta consulta){
        System.out.println("La tasa de conversión de " + consulta.getMonedaBase().getNombreCompleto() + " al " + consulta.getMonedaTarget().getNombreCompleto() + " es: "+ consulta.getTasa());
        System.out.println("Ingrese el monto a cambiar");
        System.out.println(consulta.getMonedaBase().getSimbolo() + consulta.getValorACambiar() + " equivale a " + consulta.getMonedaTarget().getSimbolo() + calculo.cambia(consulta.getValorACambiar(),consulta.getTasa()));
    }

    //al infoCambio podría agregarse un verificador de símbolos; si fueran iguales agregar la sigla en su reemplazo.



    public void muestraMoneda(Moneda moneda){
        System.out.println(moneda.toString());
    }

}
