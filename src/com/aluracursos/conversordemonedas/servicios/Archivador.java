package com.aluracursos.conversordemonedas.servicios;

import com.aluracursos.conversordemonedas.modelo.Consulta;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

public class Archivador {
    private static final String ARCHIVO_CONSULTAS = "consultas.json";

    //Este método lista las consultas
    public void guardaConsultas(Map<Integer, Consulta> listaDeConsultas, Consulta consulta) {
        Integer key = listaDeConsultas.size()+1;
        listaDeConsultas.put(key, consulta);
    }

    //Este método crea un archivo para almacenar la lista de consultas
    public void generaArchivo() {
        try {
            File archivo = new File(ARCHIVO_CONSULTAS);
            if (archivo.createNewFile()) {
                System.out.println("Se ha creado el archivo " + ARCHIVO_CONSULTAS);
            } else {
                System.out.println("Se genera archivo " + ARCHIVO_CONSULTAS);
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo " + ARCHIVO_CONSULTAS);
            e.printStackTrace();
        }
    }

    //Este método convierte la lista de consultas en JSON y lo almacena en el archivo de tipo .json
    public void archivaLista(Map<Integer, Consulta> listaDeConsultas){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new AdaptadorLocalDateTime())
                .create();
        try (FileWriter fileWriter = new FileWriter(ARCHIVO_CONSULTAS)) {
            gson.toJson(listaDeConsultas, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Este método concentra la operación de archivo de la lista de consultas
    public void manejaArchivo(Map<Integer, Consulta> listaDeConsultas){
        if(!listaDeConsultas.isEmpty()){
            generaArchivo();
            archivaLista(listaDeConsultas);
        }
    }

    //Este método elimina el archivo de lista de consultas al iniciar una nueva sesión
    public void borraArchivo(){
        File archivo = new File(ARCHIVO_CONSULTAS);
        if (archivo.exists()){
            archivo.delete();
        }
    }
}
