package com.aluracursos.conversordemonedas.servicios;

import com.aluracursos.conversordemonedas.modelo.Clave;
import com.aluracursos.conversordemonedas.modelo.Consulta;
import com.aluracursos.conversordemonedas.modelo.Moneda;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Asignador {
    Habilitador habilita = new Habilitador();
    Impresor imprime = new Impresor();
    Llamador llama = new Llamador();
    Calculador calculadora = new Calculador();
    Buscador busca = new Buscador();
    Scanner scanner = new Scanner(System.in);

    //Este método asegura el ingreso del monto a cambiar y maneja las excepciones
    private double obtenerValorACambiar() {
        double valorACambiar = 0;
        try {
            do {
                imprime.muestraMenu(6);
                valorACambiar = Double.parseDouble(scanner.nextLine());
                if(valorACambiar<=0){
                    imprime.muestraMenu(4);
                }
            } while (valorACambiar <= 0);
        } catch (NumberFormatException e) {
            imprime.muestraMenu(4);
            valorACambiar = Double.parseDouble(scanner.nextLine());
        }catch (InputMismatchException e) {
                imprime.muestraMenu(4);
            System.out.println("No es necesario ingresar el símbolo de la moneda");
        } catch (NoSuchElementException e) {
            imprime.muestraMenu(6);
            System.out.println(e.getMessage());
            System.out.println("Se ha producido un error al leer la entrada");
        } catch (IllegalStateException e) {
            imprime.muestraMenu(7);
            System.out.println(e.getMessage());
            System.out.println("Se ha producido un error interno.");
        }
            return valorACambiar;
    }

    //Este método inserta las monedas en la consulta
    public void componeConsulta(Consulta consulta, Moneda moneda, boolean esMonedaBase){
        if(esMonedaBase) {
            consulta.setMonedaBase(moneda);
            imprime.muestraMoneda(consulta.getMonedaBase());
        }else {
            consulta.setMonedaTarget(moneda);
            imprime.muestraMoneda(consulta.getMonedaTarget());
        }
    }

    //Este método completa la consulta con los datos necesarios para hacer la llamada a la API y la respuesta de esta.
    public void completaConsulta(Consulta consulta, Clave clave) {
        try {
            consulta.setValorACambiar(obtenerValorACambiar());
            consulta.setTasa(llama.tasaConversion(llama.llamadaABaseDeDatos(llama.generaDireccion(consulta, clave))));
            if (consulta.getTasa() != null) {
                consulta.setFechaHora(LocalDateTime.now());
                consulta.setValorCambiado(calculadora.cambia(consulta.getValorACambiar(), consulta.getTasa()));
            }
        }catch(NumberFormatException e) {
            System.out.println("El valor ingresado no es válido" + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Error al ingresar los datos: " + e.getMessage());
        } catch (NoSuchElementException e) {
            System.out.println("Error al leer los datos ingresados: " + e.getMessage());
        } catch (IllegalStateException e){
            System.out.println("Error interno: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error general del sistema: " + e.getMessage());
        }
    }

    //Este método concentra la operación de las monedas en la consulta
    public void manejaConsulta(Consulta consulta){
        Moneda moneda;
        do {
            do {
                imprime.muestraMenu(14);
                moneda = busca.eligeMoneda();
            } while (moneda == null);
            imprime.muestraMoneda(moneda);
            imprime.muestraMenu(11);
            if (habilita.acepta()) {
                componeConsulta(consulta, moneda, true);
                if (consulta.getMonedaBase() == null) {
                    imprime.muestraMenu(14);
                }
            }
        } while (consulta.getMonedaBase() == null);
        do {
            do {
                imprime.muestraMenu(15);
                moneda = busca.eligeMoneda();
            } while (moneda == null);
            imprime.muestraMoneda(moneda);
            imprime.muestraMenu(11);
            if (habilita.acepta()) {
                componeConsulta(consulta, moneda, false);
                if (consulta.getMonedaTarget() == null) {
                    imprime.muestraMenu(15);
                }
            }
        } while (consulta.getMonedaTarget() == null);
    }
}

