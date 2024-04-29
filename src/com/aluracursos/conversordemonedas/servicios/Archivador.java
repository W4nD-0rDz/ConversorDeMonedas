package com.aluracursos.conversordemonedas.servicios;

import com.aluracursos.conversordemonedas.modelos.Consulta;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Archivador {

    public void guardaConsultas(Map<Integer, Consulta> listaDeConsultas,  Consulta consulta){
        Integer key = listaDeConsultas.size() + 1;
         listaDeConsultas.put(key, consulta);
    }

    public FileWriter generaArchivo(Map<Integer, Consulta> listaDeConsultas) throws IOException {
        FileWriter escritura = new FileWriter("consultas.json");
        Gson gson = null;
        escritura.write(gson.toJson(listaDeConsultas));
        escritura.close();
        return escritura;
    }
}
