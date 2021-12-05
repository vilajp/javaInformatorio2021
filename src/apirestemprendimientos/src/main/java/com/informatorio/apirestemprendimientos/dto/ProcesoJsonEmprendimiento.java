package com.informatorio.apirestemprendimientos.dto;


import com.informatorio.apirestemprendimientos.entity.Evento;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ProcesoJsonEmprendimiento {

    private String nombre;
    private String descripcion;
    private String contenido;
    private BigInteger objetivo;
    private Boolean publicado;
    private List<String> urls = new ArrayList<>();
    private List<String> tags = new ArrayList<>();
    private Evento evento;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public BigInteger getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(BigInteger objetivo) {
        this.objetivo = objetivo;
    }

    public Boolean getPublicado() {
        return publicado;
    }

    public void setPublicado(Boolean publicado) {
        this.publicado = publicado;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
