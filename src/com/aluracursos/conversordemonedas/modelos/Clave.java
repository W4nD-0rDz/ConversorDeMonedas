package com.aluracursos.conversordemonedas.modelos;

public class Clave {
    private String nombreApi;
    private String clave;

    public Clave(){

    };

    public Clave(String nombreApi, String clave) {
        this.nombreApi = nombreApi;
        this.clave = clave;
    }

    public String getNombreApi() {
        return nombreApi;
    }

    public void setNombreApi(String nombreApi) {
        this.nombreApi = nombreApi;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
