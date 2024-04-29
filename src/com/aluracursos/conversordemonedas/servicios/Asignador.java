package com.aluracursos.conversordemonedas.servicios;

import com.aluracursos.conversordemonedas.modelos.Clave;
import com.aluracursos.conversordemonedas.modelos.Consulta;
import com.aluracursos.conversordemonedas.modelos.Moneda;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Asignador {

    // La función de la clase Asignador es la de manejar los métodos de la clave y el completamiento de la consulta, por medio de llamador y calculador
    //VER PROBLEMA CON LA MEMORIA SCANNER!!!

    Scanner scanner = new Scanner(System.in);
    Impresor imprime = new Impresor();
    Llamador llama = new Llamador();
    Calculador calculadora = new Calculador();
    private static final String ARCHIVO_CONFIG = "config.properties";


    //Clave: 7ebd990082fa5799c0503269 para exchangerate
    public void archivaClave(String nombreApi, String clave) {
        try (InputStream input = new FileInputStream(ARCHIVO_CONFIG)) {
            Properties prop = new Properties();
            prop.load(input);
            prop.setProperty(nombreApi, clave);
            try (OutputStream output = new FileOutputStream(ARCHIVO_CONFIG)) {
                prop.store(output, null);
                System.out.println("¡La clave se ha actualizado correctamente!");
            } catch (IOException io) {
                io.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Con la construcción de la clase Clave, puede que sea redundante...
    public String obtieneClave(String nombreApi) {
        try {
            Properties propiedades = new Properties();
            FileInputStream datos = new FileInputStream(ARCHIVO_CONFIG);
            propiedades.load(datos);
            return propiedades.getProperty(nombreApi);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de propiedades: " + e.getMessage());
            return null;
        }
    }

    public void limpiaClave(){
        try (InputStream input = new FileInputStream(ARCHIVO_CONFIG)) {
            Properties prop = new Properties();
            prop.load(input);
            prop.setProperty("nombreApi", "tuClaveAPI");
            try (OutputStream output = new FileOutputStream(ARCHIVO_CONFIG)) {
                prop.store(output, null);
            } catch (IOException io) {
                io.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Este método completa la consulta con los datos necesarios para hacer la llamada a la API y la respuesta de esta.
    public void completaConsulta(Consulta consulta, Clave clave){
        imprime.muestraMenu(6);
        //->Aquí se llama al scanner:
        Double valorACambiar = Double.parseDouble(scanner.nextLine());
        System.out.println("paso a");
        if(valorACambiar >= 0){
            consulta.setValorACambiar(valorACambiar);
            System.out.println("paso b");
        } else {
            imprime.muestraMenu(4);
        }
        System.out.println("paso c");

        consulta.setTasa(llama.tasaConversion(llama.llamadaABaseDeDatos(llama.generaDireccion(consulta, clave))));
        System.out.println("paso d");
        consulta.setFechaHora(LocalDateTime.now());
        System.out.println("paso e");
        consulta.setValorCambiado(calculadora.cambia(consulta.getValorACambiar(), consulta.getTasa()));
        System.out.println("paso f");
        //System.out.println(consulta.toString());
        System.out.println("paso g");
    }

    //valida la aceptación. Agregar manejo de excepciones si lo ingresado no es válido.
    //tal vez conviene que este método pregunte al usuario y así se puede agregar el try catch
    public boolean acepta(int respuestaUsuario){
        return respuestaUsuario == 1;
    }

    //Este método no está componiendo la consulta. No guarda la moneda dentro de la consulta
    public void componeConsulta(Consulta consulta, Moneda moneda, int cont){
        System.out.println("a");
        imprime.muestraMoneda(moneda);
        System.out.println("¿Acepta la selección?");
        imprime.muestraMenu(3);
        System.out.println("c");
        int respuestaUsuario = Integer.parseInt(scanner.nextLine());
        //scanner.nextLine();
        System.out.println("d");
        if(acepta(respuestaUsuario) && cont == 0){
            consulta.setMonedaBase(moneda);
            imprime.muestraMoneda(consulta.getMonedaBase());
            System.out.println("e0");
        } else if (acepta(respuestaUsuario) && cont ==1){
            consulta.setMonedaTarget(moneda);
            imprime.muestraMoneda(consulta.getMonedaTarget());
            System.out.println("e1");
        }
        System.out.println("f");
    }


//
//
//
//
//    do{
//        imprime.muestra
//
//
//    }while (acepta = false || cont < 2)
//
//    do {
//        imprime.muestraMoneda(m);
//        System.out.println(menuAcepta);
//
//        if (habilita.acepta(respuestaUsuario) == true){
//
//        }
//        try {
//
//        } catch (NumberFormatException e) {
//            System.out.println("Por favor, ingrese solo números (1 ó 0).");
//            continue;
//        }
//
//        if (respuestaAcepta == 1) {
//            if (cont == 0) {
//                monedaBase = m;
//                cont++;
//            } else if (cont == 1) {
//                monedaTarget = m;
//                imprime.muestraMoneda(monedaTarget);
//                cont++;
//            }
//        } else if (respuestaAcepta == 0) {
//            if (cont == 0) {
//                cont = 0;
//            } else if (cont == 1) {
//                continue;
//            }
//        } else {
//            System.out.println("Por favor, ingrese solo los números 1 o 0.");
//            continue;
//        }
//    } while (cont < 2);
//
//
//            if (cont == 0) {
//        imprime.muestraMoneda(m);
//        System.out.println(menuAcepta);
//        int respuestaAcepta = parseInt(scanner.next());
//        if (respuestaAcepta == 1) {
//            monedaBase = m;
//            cont++;
//        } else {
//            cont = 0;
//            continue;
//        }
//    } else if (cont == 1) {
//        imprime.muestraMoneda(m);
//        System.out.println(menuAcepta);
//        int respuestaAcepta = parseInt(scanner.next());
//        if (respuestaAcepta == 1) {
//            monedaTarget = m;
//            imprime.muestraMoneda(monedaTarget);
//            cont++;
//        } else {
//            cont = 1;
//            continue;
//        }
    
}

