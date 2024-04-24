package com.aluracursos.conversordemonedas.modelos;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

public class Clave {
    private static final String ARCHIVO_CONFIG = "config.properties";

    public String obtieneClave() {
        try {
            Properties propiedades = new Properties();
            FileInputStream datos = new FileInputStream(ARCHIVO_CONFIG);
            propiedades.load(datos);
            return propiedades.getProperty("api.key");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de propiedades: " + e.getMessage());
            return null;
        }
    }

    public void guardaClave(){
        try (InputStream input = new FileInputStream(ARCHIVO_CONFIG)) {
            Properties prop = new Properties();
            prop.load(input);
            String nuevaClave = ingresaClave();
            prop.setProperty("api.key", nuevaClave);
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

    public String ingresaClave() {
        Scanner inputKey = new Scanner(System.in);
        System.out.println("Ingrese su clave de acceso a la Base de Datos");
        String nuevaClave = inputKey.nextLine();
        return nuevaClave;
    }

    public void limpiaClave(){
        try (InputStream input = new FileInputStream(ARCHIVO_CONFIG)) {
            Properties prop = new Properties();
            prop.load(input);
            String nuevaClave = "tuClaveAPI";
            prop.setProperty("api.key", nuevaClave);
            try (OutputStream output = new FileOutputStream(ARCHIVO_CONFIG)) {
                prop.store(output, null);
            } catch (IOException io) {
                io.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
