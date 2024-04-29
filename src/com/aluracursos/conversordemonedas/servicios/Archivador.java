package com.aluracursos.conversordemonedas.servicios;

import com.aluracursos.conversordemonedas.modelos.Consulta;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;


public class Archivador {
    private static final String ARCHIVO_CONSULTAS = "consultas.json";

    public void guardaConsultas(Map<Integer, Consulta> listaDeConsultas, Consulta consulta) {
        Integer key = listaDeConsultas.size();
        listaDeConsultas.put(key, consulta);
    }

    public void generaArchivo() {
        try {
            File archivo = new File(ARCHIVO_CONSULTAS);
            if (archivo.createNewFile()) {
                System.out.println("Se ha creado el archivo " + ARCHIVO_CONSULTAS);
            } else {
                System.out.println("El archivo " + ARCHIVO_CONSULTAS + " ya existe.");
            }
        } catch (IOException e) {
            System.out.println("Error al crear el archivo " + ARCHIVO_CONSULTAS);
            e.printStackTrace();
        }
    }

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
}
