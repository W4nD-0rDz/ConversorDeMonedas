package com.aluracursos.conversordemonedas.servicios;


import com.aluracursos.conversordemonedas.enumeraciones.Divisa;
import com.aluracursos.conversordemonedas.modelos.Moneda;

import java.util.*;

public class Buscador {
    private String monedaBase;
    private String monedaObjetivo;
    Impresor imprime = new Impresor();
    Scanner input = new Scanner(System.in);



    //buscador de moneda en enum de acuerdo con una letra ingresada. Devuelve una lista de monedas posibles

    public Map<Integer, String[]> mapeaDivisas() {
        System.out.println("Ingrese al menos una letra para buscar la divisa");
        String letras = input.nextLine();
        Map<Integer, String[]> mapaDivisas = new HashMap<>();
        int indice = 1;
        for (Divisa divisa : Divisa.values()) {
            if (divisa.getNombreCompleto().toLowerCase().contains(letras.toLowerCase())) {
                String[] infoDivisa = {divisa.getSigla(), divisa.getNombreCompleto()};
                mapaDivisas.put(indice, infoDivisa);
                indice++;
            }
        }
        return mapaDivisas;
    }
    public String selectorSigla(){
        String sigla = null;
        Integer indice;
        System.out.println("Elija el código de la divisa en la lista. Si no se encuentra presione 0");
        Map<Integer, String[]> mapaDivisas = mapeaDivisas();
        imprime.muestraMapa(mapaDivisas);
        indice = input.nextInt();
        input.nextLine(); //para vaciar el contenido de input
        if (indice != 0){
            String[] infoDivisa = mapaDivisas.get(indice);
            sigla = infoDivisa[0];
        }
        return sigla;
    };

    public Moneda generaMoneda(String siglaDivisa){
        String sigla = Divisa.valueOf(siglaDivisa).getSigla() ;
        String nombreCompleto = Divisa.valueOf(siglaDivisa).getNombreCompleto();
        String símbolo = Divisa.valueOf(siglaDivisa).getSimbolo();
        Moneda moneda = new Moneda(sigla,nombreCompleto,símbolo);
        return moneda;
    }



//    public ArrayList<String[]> buscaMoneda(){
//        Scanner input = new Scanner(System.in);
//        System.out.println("Ingrese al menos una letra para buscar la divisa");
//        String letras = input.nextLine();
//        for (Divisa divisa : Divisa.values()) {
//            if (divisa.getNombreCompleto().contains(letras) || divisa.getSigla().contains(letras)) {
//                String[] infoDivisa = {divisa.getSigla(), divisa.getNombreCompleto()};
//                divisasFiltradas.add(infoDivisa);
//            }
//        }
//        return divisasFiltradas;
//    };

//    public ArrayList<String[]> listaMoneda(){
//        Scanner input = new Scanner(System.in);
//        System.out.println("Ingrese al menos una letra para buscar la divisa");
//        String letras = input.nextLine();
//
//        ArrayList<String[]> divisasFiltradas = new ArrayList<>();
//        int index = 0;
//        for (Divisa divisa : Divisa.values()) {
//            if (divisa.getNombreCompleto().contains(letras) || divisa.getSigla().contains(letras)) {
//                String[] infoDivisa = {Integer.toString(index), divisa.getSigla(), divisa.getNombreCompleto()};
//                divisasFiltradas.add(infoDivisa);
//                index++;
//            }
//        }
//        return divisasFiltradas;
//    }



}



    //método que recibe una lista de monedas, la muestra y le pide al usuario que elija una, y setea la moneda de entrada o de salida
    //public String eligeDivisa(List<Moneda> listaDeMonedas){}


    // método que recibe una sigla y devuelve un símbolo de la moneda
    //public String buscaSimbolo(String sigla){}





