package com.aluracursos.conversordemonedas.principal;

import com.aluracursos.conversordemonedas.servicios.Buscador;
import com.aluracursos.conversordemonedas.servicios.Calculador;
import com.aluracursos.conversordemonedas.servicios.Impresor;
import com.aluracursos.conversordemonedas.servicios.Llamador;
import com.aluracursos.conversordemonedas.modelos.Respuesta;
import com.aluracursos.conversordemonedas.modelos.Moneda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Buscador busca = new Buscador();
        Llamador llama = new Llamador();
        Calculador calculador = new Calculador();
        Impresor print = new Impresor();

//        ArrayList<String[]> lista =  new ArrayList<String[]>();
//        lista = busca.listaMoneda();
//        print.mostrarLista(lista);
//        Map<Integer, String[]> mapaDeDivisas = busca.mapeaDivisas();
//        print.muestraMapa(mapaDeDivisas);

        Moneda monedaBase = busca.generaMoneda(busca.selectorSigla());
        System.out.println("moneda base seleccionada: " + "\n" + monedaBase.toString());

        Moneda monedaTarget = busca.generaMoneda(busca.selectorSigla());
        System.out.println("moneda objetivo seleccionada: " + "\n" + monedaTarget.toString());

        String ruta = llama.generaDireccion(monedaBase.getSigla(), monedaTarget.getSigla());
        Respuesta respuesta = llama.llamadaABaseDeDatos(ruta);
        Double tasa = llama.tasaConversion(respuesta);
        double valor = scanner.nextDouble();
        print.infoCambio(monedaBase, monedaTarget, tasa, valor);

    }

}
