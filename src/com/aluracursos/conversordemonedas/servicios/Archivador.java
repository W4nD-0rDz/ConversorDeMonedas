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
        Integer key = listaDeConsultas.size();
         listaDeConsultas.put(key, consulta);
    }

    public FileWriter generaArchivo(Map<Integer, Consulta> listaDeConsultas) {
        FileWriter escritura = null;
        try {
            escritura = new FileWriter("consultas.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //REVISAR: Exception in thread "main" java.lang.NullPointerException: Cannot invoke "com.google.gson.Gson.toJson(Object)" because "gson" is null
        //	at com.aluracursos.conversordemonedas.servicios.Archivador.generaArchivo(Archivador.java:31)
        //	at com.aluracursos.conversordemonedas.principal.Intermediador.menuConversor(Intermediador.java:110)
        //	at com.aluracursos.conversordemonedas.principal.Principal.main(Principal.java:12)

        Gson gson = null;
        try {
            escritura.write(gson.toJson(listaDeConsultas));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            escritura.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return escritura;
    }
}
