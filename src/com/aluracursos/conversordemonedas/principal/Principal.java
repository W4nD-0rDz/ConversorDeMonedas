package com.aluracursos.conversordemonedas.principal;

import com.aluracursos.conversordemonedas.modelos.Consulta;
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
        Calculador calculadora = new Calculador();
        Impresor print = new Impresor();
        Consulta consulta;
        Moneda monedaBase = null;
        Moneda monedaTarget = null;
        int option=1;
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

        //Menu
        //Bienvenida
        System.out.println("Bienvenido.");
        do {
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

        //Ingrese el monto que desea convertir.
        System.out.println("Ingrese el monto que desea convertir");
        consulta.setValorACambiar(scanner.nextDouble());
        consulta.setTasa(llama.tasaConversion(llama.llamadaABaseDeDatos(llama.generaDireccion(consulta))));
        System.out.println(consulta.toString());
        consulta.setValorCambiado(calculadora.cambia(consulta.getValorACambiar(), consulta.getTasa()));
        print.infoCambioII(consulta);
        //generar la instancia del objeto consulta
        //enviar la consulta con los métodos de llamada
        //almacener la respuesta en la consulta
        //devolver el resultado
        //Ingrese el código de la moneda en la que posee el dinero.
        //Ingrese el código de la moneda a la que desea cambiar el dinero.
        //Ingrese el monto que desea convertir.
        //Confirme la transacción.










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
