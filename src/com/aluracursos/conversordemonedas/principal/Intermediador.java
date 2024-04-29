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

        Consulta consulta; Map<Integer, Consulta> listaDeConsultas = new TreeMap<>();

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

        //Bienvenida
        System.out.println("Bienvenid@ a Global Exchange.");
        do {
            do {
                //Acceso
                System.out.println(menuApi);
                String nombreDeApi = llama.selectorAPI();
                String claveAcceso = habilita.ingresaClave();
                Clave clave = new Clave(nombreDeApi, claveAcceso);
                habilita.guardaClave(clave);

                System.out.println(menuMonedas);
                //Función construir consulta a API
                do {
                    //elegir las divisas
                    Moneda m;
                    do {
                        m = busca.eligeMoneda();
                        contador++;
                    } while (m == null);
                    System.out.println(contador);
                    //guardar la divisa elegida en la consulta
                    consulta = asigna.generaConsulta(m, contador);
                } while (consulta.getMonedaBase() == null || consulta.getMonedaTarget() == null);
                System.out.println(contador);
                //llamada a la API y completa la consulta (montos y tasas)
                asigna.completaConsulta(consulta, clave);
                imprime.muestraConsulta(consulta);
            } while (contador < 2);
            //lista las consultas realizadas
            archiva.guardaConsultas(listaDeConsultas, consulta);
            System.out.println("Desea realizar una nueva operación?");
            operacion = scanner.nextInt();
        } while (operacion == 1);

        imprime.muestraLista(listaDeConsultas);
        habilita.limpiaClave();
        scanner.close();
        archiva.generaArchivo(listaDeConsultas);
    }
}
