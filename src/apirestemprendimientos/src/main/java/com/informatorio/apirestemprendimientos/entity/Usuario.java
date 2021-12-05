package com.informatorio.apirestemprendimientos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*USUARIO:
● id (autogenerado)
● nombre
● apellido
● email (unico)
● password (se almacenará pero no deberá ser mostrado)
● fecha de creación (o alta)
● ciudad
● provincia
● país
● tipo: USUARIO | COLABORADOR | OWNER*/

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String pais;
    private String ciudad;
    private String provincia;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Emprendimiento> emprendimientos = new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voto> votos = new ArrayList<>();
    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;

    public List<Emprendimiento> getEmprendimientos() {
        return emprendimientos;
    }

    public List<Voto> getVotos(){
        return votos;
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public void agregarEmprendimiento(Emprendimiento emprendimiento) {
        emprendimientos.add(emprendimiento);
        emprendimiento.setUsuario(this);
    }

    public void removerEmprendimiento(Emprendimiento emprendimiento) {
        emprendimientos.remove(emprendimiento);
        emprendimiento.setUsuario(null);
    }

    public void agregarVoto(Voto voto) {
        votos.add(voto);
        voto.setUsuario(this);
    }

    public void removerVoto(Voto voto) {
        votos.remove(voto);
        voto.setUsuario(null);
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", pais='" + pais + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", email='" + email + '\'' +
                ", emprendimientos=" + emprendimientos +
                ", votos=" + votos +
                ", fechaDeCreacion=" + fechaDeCreacion +
                '}';
    }
}