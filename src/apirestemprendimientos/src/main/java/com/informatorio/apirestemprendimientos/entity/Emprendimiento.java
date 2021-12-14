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
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Emprendimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Debe Ingresar un nombre")
    private String nombre;
    private String descripcion;
    private String contenido;
    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;
    /*@NotBlank(message="Debe ingresar un monto para objetivo")*/
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
    private Usuario usuario;
    @JsonIgnore
    @ManyToMany(mappedBy = "emprendimientosEvento")
    private List<Evento> listaEventos = new ArrayList<>();

    public Emprendimiento() {
        this.publicado = Boolean.FALSE;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(Evento evento) {
        this.listaEventos.add(evento);
        evento.setEmprendimientosEvento(this);

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

