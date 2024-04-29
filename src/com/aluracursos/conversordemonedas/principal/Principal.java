package com.aluracursos.conversordemonedas.principal;


import java.io.IOException;



public class Principal {

    public static void main(String[] args) throws IOException {
        Intermediador menuOperativo = new Intermediador();
        menuOperativo.menuConversor();
    }
}


