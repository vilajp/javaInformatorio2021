package com.informatorio.apirestemprendimientos.entity;

/*EMPRENDIMIENTO:
● id (autogenerado)
● nombre
● descripción
● contenido (cuerpo de la publicación)
● fecha de creación (o alta)
● Objetivo: $ (recaudacion)
● publicado (true o false)
● URL (capturas) - puede tener 0 o varias
● Tags (ejemplo: #salud, #diversion, etc. Obs: el “#” es decorado)
propios:
-usuario*/

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Emprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String contenido;
    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;
    private BigInteger objetivo;
    private Boolean publicado;
    @JsonIgnore
    @OneToMany(mappedBy = "emprendimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodasLasUrl> urls = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "emprendimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodasLasTags> tags = new ArrayList<>();
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Evento evento;


    public Evento getEvento() {
        return evento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
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

    public List<TodasLasUrl> getUrls() {
        return urls;
    }

    public void agregarUrl(TodasLasUrl todasLasUrl) {
        this.urls.add(todasLasUrl);
        todasLasUrl.setEmprendimiento(this);
    }

    public List<TodasLasTags> getTags() {
        return tags;
    }

    public void agregarTags(TodasLasTags variasTags) {
         this.tags.add(variasTags);
         variasTags.setEmprendimiento(this);
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

