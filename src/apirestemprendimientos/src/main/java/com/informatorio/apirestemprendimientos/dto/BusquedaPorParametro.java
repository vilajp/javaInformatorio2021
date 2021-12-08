package com.informatorio.apirestemprendimientos.dto;

import java.util.Map;

public class BusquedaPorParametro {
    private Map<String, String> parametro;

    public String getParametro() {
        return String.valueOf(parametro.values());
    }

    public void setParametro(Map<String, String> parametro) {
        this.parametro = parametro;
    }
}
