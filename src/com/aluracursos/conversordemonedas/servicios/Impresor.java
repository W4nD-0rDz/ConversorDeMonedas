package com.aluracursos.conversordemonedas.servicios;

import com.aluracursos.conversordemonedas.modelos.Consulta;
import com.aluracursos.conversordemonedas.modelos.Moneda;

import java.time.LocalDateTime;
import java.util.Map;

public class Impresor {
    Calculador calculo = new Calculador();
    String menuMonedas = """
                Seleccione el código de la moneda:
                1. ARS - Peso argentino
                2. BOB - Boliviano boliviano
                3. BRL - Real brasileño
                4. CLP - Peso chileno
                5. COP - Peso colombiano
                6. USD - Dólar estadounidense
                7. otro
                0. salir
                """;
    String menuApi = """
                Seleccione una agencia de cambio de divisas:
                1. Exchange Rate
                2. Crypto
                3. Open Exchange
                """;
    String menuAcepta = """
                1. Si
                0. No
                """;
    String preguntaMonto = "Ingrese el monto a convertir";
    String montoValido = "Por favor, ingrese un monto válido mayor a 0";
    String valorValido = "Por favor ingrese un valor válido";
    String reIntente = "Por favor, intente nuevamente.";
    String ingreseClave = "Ingrese su clave de acceso a la Base de Datos";
    String claveOK = "¡La clave se ha actualizado correctamente!";

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
    public void muestraMenu(int ordenMenu){
        System.out.println("");
        switch (ordenMenu){
            case 1:
                System.out.println(menuApi); break;
            case 2:
                System.out.println(menuMonedas); break;
            case 3:
                System.out.println(menuAcepta); break;
            case 4:
                System.out.println(montoValido); break;
            case 5:
                System.out.println(valorValido); break;
            case 6:
                System.out.println(preguntaMonto); break;
            case 7:
                System.out.println(reIntente); break;
            case 8:
                System.out.println(ingreseClave); break;
            case 9:
                System.out.println(claveOK); break;
            default:
                System.out.println("");
                break;
        }
        System.out.println("");
    }

}
