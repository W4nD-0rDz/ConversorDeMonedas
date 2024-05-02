package com.aluracursos.conversordemonedas.servicios;

import com.aluracursos.conversordemonedas.modelos.Clave;
import com.aluracursos.conversordemonedas.modelos.Respuesta;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Habilitador {
    private static final String ARCHIVO_CONFIG = "config.properties";
    Impresor imprime = new Impresor();
    Llamador llama = new Llamador();
    Scanner scanner = new Scanner(System.in);

    //valida la aceptación. Agregar manejo de excepciones si lo ingresado no es válido.
    public boolean acepta() {
        boolean aceptacion = false;
        try {
            imprime.muestraMenu(3);
            int respuestaUsuario = Integer.parseInt(scanner.nextLine());
            if (respuestaUsuario == 1) {
                aceptacion = true;
            }
        } catch (NumberFormatException e) {
            imprime.muestraMenu(5);
        }
        return aceptacion;
    }

    //Solicita la clave  para que el siguiente método la guarde.
    public String ingresaClave() {
        imprime.muestraMenu(8);
        String nuevaClave = null;
        try {
            nuevaClave = String.valueOf(scanner.nextLine());
            return nuevaClave;
        } catch (IllegalStateException e) {
            imprime.muestraMenu(17);
        } finally {
            if (nuevaClave == null) {
                imprime.muestraMenu(8);
                nuevaClave = String.valueOf(scanner.nextLine());
            }
        }
        return nuevaClave;
    }

    //Este método valida la clave solo si la respuesta de la BD NO es error
    public boolean validaClave(Clave clave) {
        boolean claveValida = false;
        String direccionPrueba = llama.generaDireccionPrueba(clave);
        String mensaje = null;
        Respuesta respuesta = null;
        try {
            respuesta = llama.llamadaABaseDeDatos(direccionPrueba);
            mensaje = respuesta.result().toString();
            if (!mensaje.equals("error")) {
                claveValida = true;
            } else {
                imprime.muestraMenu(17);
            }
        } catch (JsonSyntaxException e) {
            imprime.muestraMenu(18);
        } catch (IllegalArgumentException e) {
            imprime.muestraMenu(18);
        }
        return claveValida;
    }

    //Este método almacena la clave en el archivo config.properties
    public void guardaClave(Clave clave) {
        try (InputStream input = new FileInputStream(ARCHIVO_CONFIG)) {
            Properties prop = new Properties();
            prop.load(input);
            prop.setProperty(clave.getNombreApi(), clave.getClave());
            System.out.println("¡La clave se ha actualizado y almacenado correctamente!");
            try (OutputStream output = new FileOutputStream(ARCHIVO_CONFIG)) {
                prop.store(output, null);
            } catch (IOException io) {
                io.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Este método asegura que si la clave es válida, sea almacenada para la sesión. De lo contrario pide la clave nuevamenta.
    public int manejaAcceso(Clave clave) {
        int intentos = 0;
        String nombreAPI = llama.selectorAPI();
        String apiKey;
        boolean claveValida = false;
        int contadorIntentos = 0;
        do {
            apiKey = ingresaClave();
            clave.setNombreApi(nombreAPI);
            clave.setClave(apiKey);
            claveValida = validaClave(clave);
            if (claveValida) {
                limpiaClave(clave);
                guardaClave(clave);
            } else if (!claveValida) {
                contadorIntentos++;
                if (contadorIntentos < 3) {
                    System.out.println(3 - contadorIntentos + " intentos restantes");
                }
            }
        } while (!claveValida && contadorIntentos < 3);
        if (!claveValida && contadorIntentos >= 3) {
            System.out.println("Se han superado los tres intentos.");
            imprime.muestraMenu(17);
            imprime.muestraMenu(7);
            limpiaClave(clave);
        }
        intentos = contadorIntentos;
        return intentos;
    }

    //Este método sobreescribe el archivo config.properties, borrando la API seleccionada y la Clave de acceso
    public void limpiaClave(Clave clave) {
        try (InputStream input = new FileInputStream(ARCHIVO_CONFIG)) {
            Properties prop = new Properties();
            prop.load(input);
            prop.remove(clave.getNombreApi());
            try (OutputStream output = new FileOutputStream(ARCHIVO_CONFIG)) {
                prop.store(output, null);
            } catch (IOException io) {
                io.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    //Innecesario por ahora; sirve cuando conexiones a distintas API
//    public String obtieneClave(Clave clave) {
//        String apiKeyArchivada = null;
//        try {
//            Properties propiedades = new Properties();
//            FileInputStream datos = new FileInputStream(ARCHIVO_CONFIG);
//            propiedades.load(datos);
//            apiKeyArchivada = propiedades.getProperty(clave.getNombreApi());
//            return apiKeyArchivada;
//        } catch (IOException e) {
//            System.err.println("Error al leer el archivo de propiedades: " + e.getMessage());
//            return null;
//        }
//    }
}

