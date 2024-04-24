package com.aluracursos.conversordemonedas.principal;

import com.aluracursos.conversordemonedas.servicios.Calculador;
import com.aluracursos.conversordemonedas.servicios.Llamador;
import com.aluracursos.conversordemonedas.modelos.Respuesta;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Llamador llama = new Llamador();
        Calculador calculador = new Calculador();
        String ruta = llama.generaDireccion("ARS","USD");
        Respuesta respuesta = llama.llamadaABaseDeDatos(ruta);
        Double tasa = llama.tasaConversion(respuesta);
        System.out.println("La tasa de conversión del dólar al peso es: " + tasa);
        System.out.println("Ingrese el monto a cambiar");
        double valorACambiar = scanner.nextDouble();
        double valorCambiado = calculador.cambia(valorACambiar,tasa);

        System.out.println("$" + valorACambiar + " equivale a USD" + valorCambiado);
    }

}
