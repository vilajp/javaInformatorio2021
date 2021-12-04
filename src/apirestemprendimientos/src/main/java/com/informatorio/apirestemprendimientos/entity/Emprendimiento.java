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
    @OneToMany(mappedBy = "emprendimiento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TodasLasUrl> urls = new ArrayList<>();
    private String tags;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    private Evento evento;



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
        urls.add(todasLasUrl);
        todasLasUrl.setEmprendimiento(this);
    }
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

