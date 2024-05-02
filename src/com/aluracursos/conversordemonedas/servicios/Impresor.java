package com.aluracursos.conversordemonedas.servicios;
import com.aluracursos.conversordemonedas.modelo.Consulta;
import com.aluracursos.conversordemonedas.modelo.Moneda;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;

public class Impresor {
    Calculador calculo = new Calculador();
    //TEXTOS PARA MENÚ
    String bienvenida = "Bienvenid@ a Alura Global Exchange.";
    String menuMonedas = """
            Seleccione el código de la moneda:
            1. ARS - Peso argentino
            2. BOB - Boliviano boliviano
            3. BRL - Real brasileño
            4. CLP - Peso chileno
            5. COP - Peso colombiano
            6. USD - Dólar estadounidense
            7. otra divisa...
            """;
    String menuApi = """
            Seleccione una agencia de cambio de divisas:
            1. Exchange Rate
            2. Crypto
            3. Open Exchange
            """;
    String menuAcepta = """
            1. Si
            0. No
            """;
    String preguntaAcepta = "¿Está ud. de acuerdo?";
    String preguntaMonto = "Ingrese el monto a convertir";
    String montoValido = "Por favor, ingrese un monto válido mayor a 0";
    String valorValido = "Por favor, ingrese un número válido";
    String reIntente = "Por favor, intente nuevamente.";
    String ingreseClave = "Ingrese su clave de acceso a la Base de Datos: ";
    String claveOK = "Se ha actualizado la clave";
    String reinicia = "¿Desea realizar una nueva operación?";
    String tituloLista = "Lista de Divisas que contienen las letras ingresadas:";
    String tituloConsultas = "Consultas realizadas durante la sesión:";
    String divisaBase = "Por favor, seleccione la divisa del monto a convertir";
    String divisaTarget = "Por favor, seleccione la divisa objetivo de la conversión";
    String apiDeshabilitada = "API temporalmente deshabilitada";
    String claveInvalida = "Clave inválida";
    String errorAPI = "Ha habido un error en la conexión debido a clave inválida.";

    //Este método muestra los textos de interacción con el usuario
    public void muestraMenu(int ordenMenu) {
        System.out.println("");
        switch (ordenMenu) {
            case 0:
                System.out.println(bienvenida);
                break;
            case 1:
                System.out.println(menuApi);
                break;
            case 2:
                System.out.println(menuMonedas);
                break;
            case 3:
                System.out.println(menuAcepta);
                break;
            case 4:
                System.out.println(montoValido);
                break;
            case 5:
                System.out.println(valorValido);
                break;
            case 6:
                System.out.println(preguntaMonto);
                break;
            case 7:
                System.out.println(reIntente);
                break;
            case 8:
                System.out.print(ingreseClave);
                break;
            case 9:
                System.out.println(claveOK);
                break;
            case 10:
                System.out.println(reinicia);
                break;
            case 11:
                System.out.println(preguntaAcepta);
                break;
            case 12:
                System.out.println(tituloLista);
                break;
            case 13:
                System.out.println(tituloConsultas);
                break;
            case 14:
                System.out.println(divisaBase);
                break;
            case 15:
                System.out.println(divisaTarget);
                break;
            case 16:
                System.out.println(apiDeshabilitada);
                break;
            case 17:
                System.out.println(claveInvalida);
                break;
            case 18:
                System.out.println(errorAPI);
                break;
            default:
                System.out.println("");
                break;
        }
    }

    //Este método muestra el contenido de mapas
    public <T> void muestraGenerico(Map<Integer, T> mapa) {
        for (Map.Entry<Integer, T> entrada : mapa.entrySet()) {
            Integer indice = entrada.getKey();
            T valor = entrada.getValue();
            String info;
            if (valor instanceof String[]) {
                String[] datoDivisa = (String[]) valor;
                info = Arrays.toString(datoDivisa);
            } else {
                info = valor.toString();
            }
            LocalDateTime fechaHora;
            if (valor instanceof Consulta) {
                fechaHora = ((Consulta) valor).getFechaHora();
                muestraFecha(fechaHora);
            }
            System.out.println(indice + " - " + info);
        }
        System.out.println(" ");
    }

    //Este método imprime por consola la información contenida en la Consulta
    public void muestraConsulta(Consulta consulta) {
        muestraFecha(consulta.getFechaHora());
        System.out.println("La tasa de conversión de " + consulta.getMonedaBase().getNombreCompleto() + " al " + consulta.getMonedaTarget().getNombreCompleto() + " es: " + consulta.getTasa());
        if (consulta.getMonedaBase().getSimbolo() == consulta.getMonedaTarget().getSimbolo()) {
            System.out.println(consulta.getMonedaBase().getSigla() + " " + consulta.getValorACambiar() + " equivale a " + consulta.getMonedaTarget().getSigla() + " " + consulta.getValorCambiado());
        } else {
            System.out.println(consulta.getMonedaBase().getSimbolo() + " " + consulta.getValorACambiar() + " equivale a " + consulta.getMonedaTarget().getSimbolo() + " " + consulta.getValorCambiado());
        }
    }

    //Este método imprime por consola la instancia de Moneda
    public void muestraMoneda(Moneda moneda) {
        System.out.println("Divisa seleccionada: " + moneda.toString());
    }

    //Este método imprime por consola fechas
    public void muestraFecha(LocalDateTime fechaHora) {
        System.out.println(String.format("%d/%d/%d (%02d:%02d)",
                fechaHora.getDayOfMonth(), fechaHora.getMonthValue(), fechaHora.getYear(),
                fechaHora.getHour(), fechaHora.getMinute()));
    }


}
