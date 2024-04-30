package com.aluracursos.conversordemonedas.servicios;

import com.aluracursos.conversordemonedas.modelos.Clave;
import com.aluracursos.conversordemonedas.modelos.Consulta;
import com.aluracursos.conversordemonedas.modelos.Moneda;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Asignador {
    // La función de la clase Asignador es la de manejar los métodos de la clave y el completamiento de la consulta, por medio de llamador y calculador
    Habilitador habilita = new Habilitador();
    Impresor imprime = new Impresor();
    Llamador llama = new Llamador();
    Calculador calculadora = new Calculador();
    Scanner scanner = new Scanner(System.in);

    //Este método completa la consulta con los datos necesarios para hacer la llamada a la API y la respuesta de esta.
    public void completaConsulta(Consulta consulta, Clave clave) {
        imprime.muestraMenu(6);
        Double valorACambiar = Double.parseDouble(scanner.nextLine());
        if (valorACambiar >= 0) {
            consulta.setValorACambiar(valorACambiar);
        } else {
            imprime.muestraMenu(4);
        }
        consulta.setTasa(llama.tasaConversion(llama.llamadaABaseDeDatos(llama.generaDireccion(consulta, clave))));
        consulta.setFechaHora(LocalDateTime.now());
        consulta.setValorCambiado(calculadora.cambia(consulta.getValorACambiar(), consulta.getTasa()));
    }

    //Este método agrega la moneda dada según el ciclo indicado por el contador 0-> base 1-> target
    public void componeConsulta(Consulta consulta, Moneda moneda, int cont) {
        boolean aceptacion = habilita.acepta();
        if (aceptacion && cont == 0) {
            System.out.println("Moneda Base");
            consulta.setMonedaBase(moneda);
            imprime.muestraMoneda(consulta.getMonedaBase());
        } else if (aceptacion && cont == 1) {
            System.out.println("Moneda Target");
            consulta.setMonedaTarget(moneda);
            imprime.muestraMoneda(consulta.getMonedaTarget());
        }
    }
}

