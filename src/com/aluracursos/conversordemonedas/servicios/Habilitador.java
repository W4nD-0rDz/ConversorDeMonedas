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

    //valida la aceptación. Agregar manejo de excepciones si lo ingresado no es válido.
    public boolean acepta(){
        imprime.muestraMenu(3);
        int respuestaUsuario = Integer.parseInt(scanner.nextLine());
        return respuestaUsuario == 1;
    }

    //Este método valida que se halla ingresado la clave y que esta corresponda con la archivada
    //puede ser mejorado para verificar claves contra una BD de claves.
    public boolean validaClave(String nombreDeAPI) {
        boolean validez = false;
        try {
            Properties propiedades = new Properties();
            FileInputStream datos = new FileInputStream(ARCHIVO_CONFIG);
            propiedades.load(datos);
            String clave = String.valueOf(propiedades.getProperty(nombreDeAPI));
            if (clave.equalsIgnoreCase("tuClaveAPI")){
                return false;
            } else {
                return true;
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de propiedades: " + e.getMessage());
        } finally {
            return validez;
        }
    }

    //Solicita la clave  para que el siguiente método la guarde.
    public String ingresaClave() {
        imprime.muestraMenu(8);
        String nuevaClave = String.valueOf(scanner.nextLine());
        return nuevaClave;
    }

    //Clave: 7ebd990082fa5799c0503269 para exchangerate
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

    //Este método sobreescribe el archivo config.properties, borrando la API seleccionada y la Clave de acceso
    //Si se agrega una etapa de inicio de sesión por usuario, en la que pueda quedar grabada la clave; puede no ser necesario111
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

