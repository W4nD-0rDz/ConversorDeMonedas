package com.aluracursos.conversordemonedas.servicios;
import com.aluracursos.conversordemonedas.enumeraciones.Divisa;
import com.aluracursos.conversordemonedas.modelo.Moneda;
import java.util.*;

public class Buscador {
    Impresor imprime = new Impresor();
    Scanner input = new Scanner(System.in);

    //Este método busca las divisas listadas en el enum Divisa de acuerdo con una letra ingresada. Devuelve un mapa de monedas que contengan el argumento
    public Map<Integer, String[]> mapeaDivisas() {
        Map<Integer, String[]> mapaDivisas = new HashMap<>();
        boolean mapaVacio = true;
        do {
            try {
                System.out.println("Ingrese al menos una letra para buscar la divisa");
                String letras = String.valueOf(input.nextLine());
                int indice = 1;
                for (Divisa divisa : Divisa.values()) {
                    if (divisa.getNombreCompleto().toLowerCase().contains(letras.toLowerCase())
                            || divisa.getSigla().toLowerCase().contains(letras.toLowerCase())) {
                        String[] infoDivisa = {divisa.getSigla(), divisa.getNombreCompleto()};
                        mapaDivisas.put(indice, infoDivisa);
                        indice++;
                    }
                }
                if (!mapaDivisas.isEmpty()) {
                    mapaVacio = false;
                } else {
                    System.out.println("Disculpas, no se encuentra ninguna divisa conteniendo esas letras." +
                            "\nIntente nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Se ha producido un error: " + e.getMessage());
                e.printStackTrace();
            }
        } while (mapaVacio);
        return mapaDivisas;
    }

    //Define la sigla para la búsqueda de divisas en enum y almacenaje en objeto Moneda
    public String selectorPorSigla() {
        String sigla = null;
        Integer indice;
        Map<Integer, String[]> mapaDivisas;
        try {
            do{
                mapaDivisas = mapeaDivisas();
                imprime.muestraMenu(12);
                imprime.muestraGenerico(mapaDivisas);
                System.out.println("Elija el código de la divisa en la lista. Si no se encuentra presione 0");
                indice = Integer.parseInt(input.nextLine());
                if (indice != 0) {
                    String[] infoDivisa = mapaDivisas.get(indice);
                    sigla = infoDivisa[0];
                }
            }while(mapaDivisas.isEmpty());
        } catch (NumberFormatException e) {
            System.out.println("Por favor ingrese el código de la divisa de su elección." +
                    "\nSi no la encuentra presione 0.");
        } catch (NullPointerException e) {
            System.out.println("Disculpas, no se encuentra ninguna divisa conteniendo esas letras." +
                    "\nIntente nuevamente.");
        }
        return sigla;
    }

    //Este método genera una instancia de Moneda con los datos almacenados en el Enum Divisa en base a una sigla dada.
    public Moneda generaMoneda(String siglaDivisa) {
        String sigla = Divisa.valueOf(siglaDivisa).getSigla();
        String nombreCompleto = Divisa.valueOf(siglaDivisa).getNombreCompleto();
        String simbolo = Divisa.valueOf(siglaDivisa).getSimbolo();
        return new Moneda(sigla, nombreCompleto, simbolo);
    }

    //Este método contiene el menú de selección de monedas
    public Moneda eligeMoneda() {
        Moneda m = null;
        boolean inputValido = false;
        do {
            try {
                imprime.muestraMenu(2);
                int option = Integer.parseInt(input.nextLine());
                switch (option) {
                    case 1:
                        m = generaMoneda("ARS");
                        break;
                    case 2:
                        m = generaMoneda("BOB");
                        break;
                    case 3:
                        m = generaMoneda("BRL");
                        break;
                    case 4:
                        m = generaMoneda("CLP");
                        break;
                    case 5:
                        m = generaMoneda("COP");
                        break;
                    case 6:
                        m = generaMoneda("USD");
                        break;
                    case 7:
                        String sigla = selectorPorSigla();
                        if (sigla != null) {
                            m = generaMoneda(sigla);
                        } else {
                            System.out.println("No hemos encontrado la divisa. Intente nuevamente.");
                        }
                        break;
                    default:
                        m = null;
                        imprime.muestraMenu(7);
                        break;
                }
                inputValido = true;
            } catch (NumberFormatException e) {
                imprime.muestraMenu(5);
            }
        } while (!inputValido);
        return m;
    }
}





