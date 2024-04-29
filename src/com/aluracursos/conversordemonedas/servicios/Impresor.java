package com.aluracursos.conversordemonedas.servicios;

import com.aluracursos.conversordemonedas.modelos.Consulta;
import com.aluracursos.conversordemonedas.modelos.Moneda;

import java.time.LocalDateTime;
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
        System.out.println("Lista de Divisas que contienen las letras ingresadas:");
        for (Map.Entry<Integer, String[]> entrada : mapa.entrySet()) {
            Integer indice = entrada.getKey();
            String[] infoDivisa = entrada.getValue();
            System.out.println(indice + " - " + infoDivisa[0] + " - " + infoDivisa[1]);
        }
        System.out.println(" ");
    }

    public static void muestraLista(Map<Integer, Consulta> listaDeConsultas) {
        System.out.println("Consultas realizadas durante la sesión:");
        for (Map.Entry<Integer, Consulta> consultasRealizadas : listaDeConsultas.entrySet()) {
            Integer indice = consultasRealizadas.getKey() + 1;
            String consulta = consultasRealizadas.toString();
            System.out.println("Consulta n°: " + indice + "\n" + consulta);
        }
        System.out.println(" ");
    }

//    public void infoCambio(Moneda monedaBase, Moneda monedaTarget, double tasa, double valor){
//        System.out.println("La tasa de conversión de " + monedaBase.getNombreCompleto() + " al " + monedaTarget.getNombreCompleto() + " es: "+ tasa);
//        System.out.println("Ingrese el monto a cambiar");
//        System.out.println(monedaBase.getSimbolo() + valor + " equivale a " + monedaTarget.getSimbolo() + calculo.cambia(valor,tasa));
//    }

    public void muestraConsulta(Consulta consulta){
        muestraFecha(consulta.getFechaHora());
        System.out.println("La tasa de conversión de " + consulta.getMonedaBase().getNombreCompleto() + " al " + consulta.getMonedaTarget().getNombreCompleto() + " es: "+ consulta.getTasa());
        if (consulta.getMonedaBase().getSimbolo() == consulta.getMonedaTarget().getSimbolo()){
            System.out.println(consulta.getMonedaBase().getSigla() + consulta.getValorACambiar() + " equivale a " + consulta.getMonedaTarget().getSigla() + consulta.getValorCambiado());
        } else {
            System.out.println(consulta.getMonedaBase().getSimbolo() + consulta.getValorACambiar() + " equivale a " + consulta.getMonedaTarget().getSimbolo() + consulta.getValorCambiado());
        }
    }

    public void muestraMoneda(Moneda moneda){
        System.out.println("Divisa seleccionada: " + moneda.toString());
    }

    public void muestraFecha(LocalDateTime fechaHora){
        System.out.println(fechaHora.getDayOfMonth() + "/" + fechaHora.getMonth() +
                "/" + fechaHora.getYear() + " (" + fechaHora.getHour() + ":" + fechaHora.getMinute() + ")" );
    }
    public void muestraMenu(String menu){
        System.out.println("");
        System.out.println(menu);
        System.out.println("");
    }

}
