package com.aluracursos.conversordemonedas.servicios;

import com.aluracursos.conversordemonedas.modelos.Clave;
import com.aluracursos.conversordemonedas.modelos.Consulta;
import com.aluracursos.conversordemonedas.modelos.Respuesta;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Llamador {
    Scanner input = new Scanner(System.in);
    Impresor imprime = new Impresor();
    private String apiRuta = ("https://v6.exchangerate-api.com/v6/");

    public String selectorAPI(){
        String nombreDeApi= null;
        imprime.muestraMenu(5);
        int apiNumero = Integer.parseInt(input.nextLine());
        do {
            switch (apiNumero) {
                case 1:
                    nombreDeApi = "exchangerate"; break;
                case 2:
                    nombreDeApi = "coingecko"; break;
                case 3:
                    nombreDeApi = "openexchangerates"; break;
                default:
                    imprime.muestraMenu(7); break;
            }
        }while(nombreDeApi == null);
        return nombreDeApi;
    };

    //En proceso de prueba: se hardcodea con acceso a exchangeRate, porque las otras Api sugeridas en la consigna son pagas.
    public String generaDireccion(Consulta consulta, Clave clave){
        String direccion = apiRuta + clave.getClave() + "/pair/"+ consulta.getMonedaBase().getSigla() + "/" + consulta.getMonedaTarget().getSigla();
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


    //Captura el Json de respuesta y devuelve conversion_rate
    public Double tasaConversion(Respuesta respuesta){
        Double tasaDeConversion = respuesta.conversion_rate();
        return tasaDeConversion;
    }
}
