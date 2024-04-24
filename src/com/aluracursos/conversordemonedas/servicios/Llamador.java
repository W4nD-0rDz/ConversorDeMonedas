package com.aluracursos.conversordemonedas.servicios;

import com.aluracursos.conversordemonedas.modelos.Clave;
import com.aluracursos.conversordemonedas.modelos.Respuesta;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Llamador {
    private String apiRuta = ("https://v6.exchangerate-api.com/v6/");
    Clave clave = new Clave();

    public String generaDireccion(String monedaBase, String monedaSalida) {
        String apiKey = clave.obtieneClave();
        String direccion = apiRuta + apiKey + "/pair/"+ monedaBase + "/" + monedaSalida;
        return direccion;
    }

    //llama a base de datos
    public Respuesta llamadaABaseDeDatos(String direccion) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error al codificar la búsqueda");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            Respuesta respuesta = new Gson().fromJson(response.body(), Respuesta.class);
            return respuesta;
        }
    }


    //Captura el Json de respuesta y devuelve conversion_rate pára las monedas definidas
    public Double tasaConversion(Respuesta respuesta){
        Double tasaDeConversion = respuesta.conversion_rate();
        return tasaDeConversion;
    }





}
