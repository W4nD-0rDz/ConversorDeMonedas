package com.aluracursos.conversordemonedas.servicios;

import com.aluracursos.conversordemonedas.modelos.Clave;
import com.aluracursos.conversordemonedas.modelos.Consulta;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Scanner;

public class Asignador {
    // La función de la clase Asignador es la de manejar los métodos de la clave y el completamiento de la consulta, por medio de llamador y calculador


    //VER PROBLEMA CON LA MEMORIA SCANNER!!!
    Scanner scanner = new Scanner(System.in);

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
    //Es posible que este método sea el que sobrecargue al scanner...
    public void completaConsulta(Consulta consulta, Clave clave){
        System.out.println("Ingrese el monto que desea convertir");
        //->Aquí se llama al scanner:
        consulta.setValorACambiar(scanner.nextDouble());
        consulta.setTasa(llama.tasaConversion(llama.llamadaABaseDeDatos(llama.generaDireccion(consulta, clave))));
        consulta.setFechaHora(LocalDateTime.now());
        consulta.setValorCambiado(calculadora.cambia(consulta.getValorACambiar(), consulta.getTasa()));
        //System.out.println(consulta.toString());
    }






}
