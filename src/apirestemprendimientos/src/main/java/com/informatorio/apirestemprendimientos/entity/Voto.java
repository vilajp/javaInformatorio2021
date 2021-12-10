package com.informatorio.apirestemprendimientos.entity;

/*VOTO:
● id (autogenerado)
● generado por (mobile, web, servicio)
● Usuario (username)
● fecha de creación (o alta)
atributos mios:
- emprendimiento
- instanciaDelVoto: si es dentro de un evento o fuera del evento - se restan cuando entra al evento.
● Observación: Se asume que los votos son LIKES (no hay negativos)*/

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Voto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String generadoDesde;
    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Emprendimiento emprendimiento;
    private Boolean enEvento;

    public Boolean getEnEvento() {
        return enEvento;
    }

    public void setEnEvento(Boolean enEvento) {
        this.enEvento = enEvento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Emprendimiento getEmprendimiento() {
        return emprendimiento;
    }

    public void setEmprendimiento(Emprendimiento emprendimiento) {
        this.emprendimiento = emprendimiento;
    }

    public String getGeneradoDesde() {
        return generadoDesde;
    }

    public void setGeneradoDesde(String generadoDesde) {
        this.generadoDesde = generadoDesde;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Voto{" +
                "id=" + id +
                ", generadoDesde='" + generadoDesde + '\'' +
                ", fechaDeCreacion=" + fechaDeCreacion +
                ", usuario=" + usuario +
                '}';
    }
}
