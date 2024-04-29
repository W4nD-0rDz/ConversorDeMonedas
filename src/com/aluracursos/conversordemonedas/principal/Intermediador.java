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

        int contador = 0;
        int operacion = 1;

        Consulta consulta = new Consulta();
        Map<Integer, Consulta> listaDeConsultas = new TreeMap<>();
        Clave clave = new Clave();


        //Bienvenida
        System.out.println("Bienvenid@ a Global Exchange.");
        System.out.println("paso 0");
        do {
            do {
                //Acceso
                imprime.muestraMenu(1);
                clave.setNombreApi(llama.selectorAPI());
                System.out.println("paso 1 OK ");

                //VER un validador de clave más elegante!!!
                if (habilita.validaClave(clave.getNombreApi()).equalsIgnoreCase("tuClaveAPI")) {
                    clave.setClave(habilita.ingresaClave());
                    habilita.guardaClave(clave);
                    System.out.println("paso 2 OK");
                }
                System.out.println("paso 3 OK");

                //elegir las divisas
                //VER de reemplazar la repetición con un bucle
                Moneda mbase;
                do {
                    mbase = busca.eligeMoneda();
                } while (mbase == null);

                System.out.println("paso 4 OK");

                //guardar la divisa elegida en la consulta
                asigna.componeConsulta(consulta, mbase, contador);
                imprime.muestraMoneda(consulta.getMonedaBase());
                if (consulta.getMonedaBase() != null) {
                    contador++;
                    System.out.println("contador: " + contador);
                }

                System.out.println("paso 6 OK");
                Moneda mTarget;
                System.out.println("paso 6a");
                do {
                    System.out.println("paso 6b");
                    mTarget = busca.eligeMoneda();
                    System.out.println("paso6c");
                } while (mTarget == null);

                System.out.println("paso 7 OK");

                //guardar la divisa elegida en la consulta
                asigna.componeConsulta(consulta, mTarget, contador);
                imprime.muestraMoneda(consulta.getMonedaTarget());
                if (consulta.getMonedaTarget() != null) {
                    contador++;
                    System.out.println("contador: " + contador);
                }


            } while (contador < 2);
            System.out.println("paso 8 OK");
            //} while (consulta.getMonedaBase() == null || consulta.getMonedaTarget() == null);

            System.out.println("paso 9 OK");

            //llamada a la API y completa la consulta (montos y tasas)


            //El problema está acá: ver Consulta y verificar que contenga las dos monedas (por lo menos)
            asigna.completaConsulta(consulta, clave);
            System.out.println("paso 10 OK");
            imprime.muestraConsulta(consulta);

            System.out.println("paso 11 OK");
            //lista las consultas realizadas
            archiva.guardaConsultas(listaDeConsultas, consulta);

            System.out.println("Desea realizar una nueva operación?");
            imprime.muestraMenu(3);
            operacion = scanner.nextInt();
        } while (operacion == 1);

        System.out.println("paso 12 OK");
        imprime.muestraLista(listaDeConsultas);


        /// revisar el archivador genera archivo dice : Exception in thread "main" java.lang.NullPointerException: Cannot invoke "com.google.gson.Gson.toJson(Object)" because "gson" is null
        //	at com.aluracursos.conversordemonedas.servicios.Archivador.generaArchivo(Archivador.java:31)
        //	at com.aluracursos.conversordemonedas.principal.Intermediador.menuConversor(Intermediador.java:110)
        //	at com.aluracursos.conversordemonedas.principal.Principal.main(Principal.java:12)
        System.out.println("paso 13");
        archiva.generaArchivo(listaDeConsultas);
        habilita.limpiaClave();
    }
}
