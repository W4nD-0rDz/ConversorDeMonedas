package com.aluracursos.conversordemonedas.servicios;


import com.aluracursos.conversordemonedas.enumeraciones.Divisa;
import com.aluracursos.conversordemonedas.modelos.Moneda;

import java.util.*;

import static java.lang.Integer.parseInt;


public class Buscador {
    private String monedaBase;
    private String monedaObjetivo;
    Impresor imprime = new Impresor();
    Scanner input = new Scanner(System.in);
    Habilitador habilita = new Habilitador();

    //Este método busca las divisas listadas en el enum Divisa de acuerdo con una letra ingresada. Devuelve un mapa de monedas que contengan el argumento
    public Map<Integer, String[]> mapeaDivisas() {
        System.out.println("Ingrese al menos una letra para buscar la divisa");
        String letras = String.valueOf(input.nextLine());
        Map<Integer, String[]> mapaDivisas = new HashMap<>();
        int indice = 1;
        for (Divisa divisa : Divisa.values()) {
            if (divisa.getNombreCompleto().toLowerCase().contains(letras.toLowerCase()) || divisa.getSigla().toLowerCase().contains(letras.toLowerCase())) {
                String[] infoDivisa = {divisa.getSigla(), divisa.getNombreCompleto()};
                mapaDivisas.put(indice, infoDivisa);
                indice++;
            }
        }
        return mapaDivisas;
    }

    //Define la sigla para la búsqueda de divisas en enum y almacenaje en objeto Moneda
    public String selectorPorSigla() {
        String sigla = null; Integer indice;
        Map<Integer, String[]> mapaDivisas = mapeaDivisas();
        imprime.muestraMenu(12);
        imprime.muestraGenerico(mapaDivisas);
        System.out.println("Elija el código de la divisa en la lista. Si no se encuentra presione 0");
        indice = Integer.parseInt(input.nextLine());
        if (indice != 0) {
            String[] infoDivisa = mapaDivisas.get(indice);
            sigla = infoDivisa[0];
        }
        return sigla;
    }

    //Este método genera una instancia de Moneda con los datos almacenados en el Enum Divisa en base a una sigla dada.
    public Moneda generaMoneda(String siglaDivisa) {
        String sigla = Divisa.valueOf(siglaDivisa).getSigla();
        String nombreCompleto = Divisa.valueOf(siglaDivisa).getNombreCompleto();
        String simbolo = Divisa.valueOf(siglaDivisa).getSimbolo();
        return new Moneda(sigla, nombreCompleto, simbolo);
    }

    //Este método contiene el menú de selección de monedas
    public Moneda eligeMoneda(){
        Moneda m = new Moneda();
        do{
            imprime.muestraMenu(2);
            int option = Integer.parseInt(input.nextLine());
            switch (option) {
                case 1:
                    m = generaMoneda("ARS"); break;
                case 2:
                    m = generaMoneda("BOB"); break;
                case 3:
                    m = generaMoneda("BRL"); break;
                case 4:
                    m = generaMoneda("CLP"); break;
                case 5:
                    m = generaMoneda("COP"); break;
                case 6:
                    m = generaMoneda("USD"); break;
                case 7:
                    m = generaMoneda(selectorPorSigla()); break;
                default:
                    m = null;
                    imprime.muestraMenu(7); break;
            }
        }while (m == null);
        return m;
    }
}





