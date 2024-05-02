package com.aluracursos.conversordemonedas.principal;

import com.aluracursos.conversordemonedas.modelos.Clave;
import com.aluracursos.conversordemonedas.modelos.Consulta;
import com.aluracursos.conversordemonedas.servicios.*;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Intermediador {

    public void menuConversor() throws IOException {
        Habilitador habilita = new Habilitador();
        Asignador asigna = new Asignador();
        Archivador archiva = new Archivador();
        Impresor imprime = new Impresor();
        Map<Integer, Consulta> listaDeConsultas = new TreeMap<>();
        Clave clave = new Clave();

        //Bienvenida
        imprime.muestraMenu(0);

        //limpia archivos de la sesión anterior
        archiva.borraArchivo();

        //Acceso
        int intentos = 0;
        do {
            intentos = habilita.manejaAcceso(clave);
        } while (intentos <= 3 && !habilita.validaClave(clave));

        //Construye consultas
        do {
            Consulta consulta = new Consulta();
            //Inicia una consulta
            do {
                asigna.manejaConsulta(consulta);
            } while (consulta.getMonedaBase() == null || consulta.getMonedaTarget() == null);

            //Lanza la consulta a la Base de Datos y completa la operación
            asigna.completaConsulta(consulta, clave);
            imprime.muestraConsulta(consulta);

            //Almacena consulta
            archiva.guardaConsultas(listaDeConsultas, consulta);

            //muestra reinicio de constructor de consultas
            imprime.muestraMenu(10);

        } while (habilita.acepta());

        imprime.muestraMenu(13);
        imprime.muestraGenerico(listaDeConsultas);

        //gestiona el archivado de la lista de consultas
        archiva.manejaArchivo(listaDeConsultas);

        //resetea la clave en el archivo // ahora también la borra (remove(nombreAPI)
        habilita.limpiaClave(clave);
    }
}