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

public class Llamador {
    Scanner input = new Scanner(System.in);
    Impresor imprime = new Impresor();
    private String apiRuta = ("https://v6.exchangerate-api.com/v6/");

    //Este método permite seleccionar entre una lista de API disponibles. Solo una está en uso porque las demás son pagas.
    public String selectorAPI() {
        String nombreDeApi = null;
        do {
            try {
                imprime.muestraMenu(1);
                int apiNumero = Integer.parseInt(input.nextLine());
                switch (apiNumero) {
                    case 1:
                        nombreDeApi = "exchangerate";
                        break;
                    case 2:
                        imprime.muestraMenu(16);
                        break; //deshabilitada
                    case 3:
                        imprime.muestraMenu(16);
                        break; //deshabilitada
                    default:
                        imprime.muestraMenu(7);
                        break;
                }
            } catch (NumberFormatException e) {
                imprime.muestraMenu(5);
            }
        } while (nombreDeApi == null);
        return nombreDeApi;
    }

    //Estos métodos construyen las direcciones que apuntan a la API
    public String generaDireccion(Consulta consulta, Clave clave) {
        String direccion = apiRuta + clave.getClave() + "/pair/" + consulta.getMonedaBase().getSigla() + "/" + consulta.getMonedaTarget().getSigla();
        return direccion;
    }
    public String generaDireccionPrueba(Clave clave) {
        String direccion = apiRuta + clave.getClave() + "/latest/USD";
        return direccion;
    }

    //Este método apunta a la API y captura la respuesta
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
            System.out.println("Error de conexión con API");
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            System.out.println("La conexión fue interrumpida");
            throw new RuntimeException(e);
        }
        Respuesta respuesta = new Gson().fromJson(response.body(), Respuesta.class);
        return respuesta;
    }

    //Captura el Json de respuesta y devuelve conversion_rate
    public Double tasaConversion(Respuesta respuesta) {
        Double tasaDeConversion = 0.0;
        if (respuesta == null) {
            throw new IllegalArgumentException("La respuesta de la API es nula");
        } else if (respuesta != null) {
            tasaDeConversion = respuesta.conversion_rate();
        }
        return tasaDeConversion;
    }
}
