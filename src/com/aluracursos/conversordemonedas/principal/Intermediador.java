package com.aluracursos.conversordemonedas.principal;

import com.aluracursos.conversordemonedas.modelos.Clave;
import com.aluracursos.conversordemonedas.modelos.Consulta;
import com.aluracursos.conversordemonedas.modelos.Moneda;
import com.aluracursos.conversordemonedas.servicios.*;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Intermediador {
    public void menuConversor() throws IOException {
        Habilitador habilita = new Habilitador();
        Buscador busca = new Buscador();
        Llamador llama = new Llamador();
        Asignador asigna = new Asignador();
        Archivador archiva = new Archivador();
        Impresor imprime = new Impresor();
        Scanner scanner = new Scanner(System.in);

        int contador = 0; int operacion = 1;

        Consulta consulta = new Consulta();
        Map<Integer, Consulta> listaDeConsultas = new TreeMap<>();
        Clave clave = new Clave();


        //Bienvenida
        System.out.println("Bienvenid@ a Global Exchange.");
        System.out.println("paso 0");
        //Acceso
        imprime.muestraMenu(1);
        clave.setNombreApi(llama.selectorAPI());
        System.out.println("paso 1 OK ");
        boolean validez = habilita.validaClave(clave.getNombreApi());
        if (validez == false) {
            clave.setClave(habilita.ingresaClave());
            habilita.guardaClave(clave);
            System.out.println("paso 2 OK");
        }
        archiva.generaArchivo();
        //construcción de una consulta
        do {
            System.out.println("paso 3 OK");
            Moneda m = null;
            do {
                m = busca.eligeMoneda();
                System.out.println("contador: " + contador);
                asigna.componeConsulta(consulta, m, contador);
                if (consulta.getMonedaBase() != null || consulta.getMonedaTarget() != null) {
                    contador++;
                    System.out.println("contador: " + contador);
                }
            } while (contador < 2);
            System.out.println("paso 4");
            imprime.muestraMoneda(consulta.getMonedaBase());
            System.out.println("paso 5");
            imprime.muestraMoneda(consulta.getMonedaTarget());
            System.out.println("paso 6");
            asigna.completaConsulta(consulta, clave);
            System.out.println("paso 7");
            imprime.muestraConsulta(consulta);
            System.out.println("paso 8");
            //almacenamiento de consultas
            archiva.guardaConsultas(listaDeConsultas, consulta);
            System.out.println("paso 9");
            System.out.println("Desea realizar una nueva operación?");
            imprime.muestraMenu(3);
            operacion = scanner.nextInt();
            if (operacion == 1) {
                consulta = new Consulta();
                contador = 0;
                System.out.println("paso 10");
            }
        } while (operacion == 1);

        System.out.println("paso 12 OK");
        Impresor.muestraLista(listaDeConsultas);

        //archiva la lista de consultas realizadas
        System.out.println("paso 13");
        archiva.archivaLista(listaDeConsultas);
        habilita.limpiaClave();
    }
}