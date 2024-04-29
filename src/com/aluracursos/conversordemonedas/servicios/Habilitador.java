package com.aluracursos.conversordemonedas.servicios;

import com.aluracursos.conversordemonedas.modelos.Clave;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Habilitador {
    private static final String ARCHIVO_CONFIG = "config.properties";
    Impresor imprime = new Impresor();
    Scanner scanner = new Scanner(System.in);


    public String validaClave(String nombreDeAPI) {
        try {
            Properties propiedades = new Properties();
            FileInputStream datos = new FileInputStream(ARCHIVO_CONFIG);
            propiedades.load(datos);
            return propiedades.getProperty(nombreDeAPI);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de propiedades: " + e.getMessage());
            return null;
        }
    }

    public void guardaClave(Clave clave) {
        try (InputStream input = new FileInputStream(ARCHIVO_CONFIG)) {
            Properties prop = new Properties();
            prop.load(input);
            prop.setProperty(clave.getNombreApi(), clave.getClave());
            try (OutputStream output = new FileOutputStream(ARCHIVO_CONFIG)) {
                prop.store(output, null);
                imprime.muestraMenu(9);
            } catch (IOException io) {
                io.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String ingresaClave() {
        imprime.muestraMenu(8);
        String nuevaClave = String.valueOf(scanner.nextLine());
        return nuevaClave;
    }

    public void limpiaClave() {
        try (InputStream input = new FileInputStream(ARCHIVO_CONFIG)) {
            Properties prop = new Properties();
            prop.load(input);
            String nuevaClave = "tuClaveAPI";
            prop.setProperty("nombreAPI", nuevaClave);
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

