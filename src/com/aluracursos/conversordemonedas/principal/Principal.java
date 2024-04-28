package com.aluracursos.conversordemonedas.principal;

import com.aluracursos.conversordemonedas.modelos.Clave;
import com.aluracursos.conversordemonedas.modelos.Consulta;
import com.aluracursos.conversordemonedas.servicios.*;
import com.aluracursos.conversordemonedas.modelos.Moneda;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Buscador busca = new Buscador();
        Llamador llama = new Llamador();
        Calculador calculadora = new Calculador();
        Impresor print = new Impresor();
        Asignador asigna = new Asignador();
        Habilitador habilita = new Habilitador();
        Consulta consulta;
        Moneda monedaBase = null;
        Moneda monedaTarget = null;
        Clave clave = null;
        String nombreApi = null;
        int option=1;
        int apiNombre;
        int cont = 0;
        Moneda m = new Moneda();

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

        //Menu
        //Bienvenida
        System.out.println("Bienvenid@ a GlobalExchange.");
        do {
            System.out.println(menuApi);
            apiNombre = scanner.nextInt();
            switch(apiNombre){
                case 1:
                    nombreApi = "exchangerate";
                    break;
                case 2:
                    nombreApi = "coingecko";
                    break;
                case 3:
                    nombreApi = "openexchangerates";
                    break;
                default:
                    System.out.println("Intente nuevamente.");
                    break;
            }
            System.out.print("Ingrese su clave de acceso a la base de datos: ");
            clave = new Clave(nombreApi,scanner.nextLine());
            habilita.guardaClave(clave);

            System.out.println(menuMonedas);
            option = scanner.nextInt();

            switch (option){
                case 1:
                    m = busca.generaMoneda("ARS");
                    //print.muestraMoneda(m);
                    break;
                case 2:
                    m = busca.generaMoneda("BOB");
                    //print.muestraMoneda(m);
                    break;
                case 3:
                    m = busca.generaMoneda("BRL");
                    //print.muestraMoneda(m);
                    break;
                case 4:
                    m = busca.generaMoneda("CLP");
                    //print.muestraMoneda(m);
                    break;
                case 5:
                    m = busca.generaMoneda("COP");
                    //print.muestraMoneda(m);
                    break;
                case 6:
                    m = busca.generaMoneda("USD");
                    //print.muestraMoneda(m);
                    break;
                case 7:
                    m = busca.generaMoneda(busca.selectorSigla());
                    //print.muestraMoneda(m);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Intente nuevamente.");
                    break;
            }

            if (cont == 0){
                monedaBase = m;
                System.out.print("Moneda elegida: ");
                print.muestraMoneda(monedaBase);
                //falta acepta si/no. Si la rta es no; cont-- menu switch;
            } else if (cont == 1){
                monedaTarget = m;
                System.out.print("Moneda de cambio: ");
                print.muestraMoneda(monedaTarget);
                //falta acepta si/no. Si la rta es no; cont-- menu switch;
            }
            cont++;
            consulta = new Consulta(monedaBase, monedaTarget);
        } while(option != 0 && cont <2);

        asigna.completaConsulta(consulta, clave);
        print.muestraConsulta(consulta);










//        Moneda monedaBase = busca.generaMoneda(busca.selectorSigla());
//        System.out.println("moneda base seleccionada: " + "\n" + monedaBase.toString());
//
//        Moneda monedaTarget = busca.generaMoneda(busca.selectorSigla());
//        System.out.println("moneda objetivo seleccionada: " + "\n" + monedaTarget.toString());
//
//        String ruta = llama.generaDireccion(monedaBase.getSigla(), monedaTarget.getSigla());
//        Respuesta respuesta = llama.llamadaABaseDeDatos(ruta);
//        Double tasa = llama.tasaConversion(respuesta);
//        System.out.print("Ingrese monto a cambiar: ");
//        double valor = scanner.nextDouble();
//
//        print.infoCambio(monedaBase, monedaTarget,tasa, valor);
    }

}
