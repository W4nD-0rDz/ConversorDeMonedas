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

        Map<Integer, Consulta> listaDeConsultas = new TreeMap<>();
        Clave clave = new Clave();

        //Bienvenida
        imprime.muestraMenu(0);
        //Acceso
        imprime.muestraMenu(1);
        clave.setNombreApi(llama.selectorAPI());

        if (!habilita.validaClave(clave.getNombreApi())) {
            clave.setClave(habilita.ingresaClave());
            habilita.guardaClave(clave);
        }
        archiva.generaArchivo();

        do {
            //Inicia una consulta
            Consulta consulta = new Consulta();
            int contador = 0;

            //FUNCIONA pero VER de optimizar!!!
            do {
                if (contador == 0) {
                    do {
                        imprime.muestraMenu(14);
                        consulta.setMonedaBase(busca.eligeMoneda());
                        if (consulta.getMonedaBase() != null) {
                            contador++;
                            System.out.println(contador);
                            imprime.muestraMoneda(consulta.getMonedaBase());
                        }
                    } while (consulta.getMonedaBase() == null);
                } else if (contador == 1) {
                    do {
                        imprime.muestraMenu(15);
                        consulta.setMonedaTarget(busca.eligeMoneda());
                        if (consulta.getMonedaTarget() != null) {
                            contador++;
                            imprime.muestraMoneda(consulta.getMonedaTarget());
                            System.out.println(contador);
                        }
                    } while (consulta.getMonedaTarget() == null);
                }
            } while (consulta.getMonedaBase() == null || consulta.getMonedaTarget() == null);

            //Lanza la consulta a la Base de Datos y completa la operación
            asigna.completaConsulta(consulta, clave);
            imprime.muestraConsulta(consulta);

            //Almacena consulta
            archiva.guardaConsultas(listaDeConsultas, consulta);

            //Reinicia el armador de consultas
            imprime.muestraMenu(10);

        } while (habilita.acepta());
        imprime.muestraMenu(13);
        imprime.muestraGenerico(listaDeConsultas);

        //archiva la lista de consultas realizadas
        archiva.archivaLista(listaDeConsultas);

        //resetea la clave en el archivo
        habilita.limpiaClave();
    }
}