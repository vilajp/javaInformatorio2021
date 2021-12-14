package com.informatorio.apirestemprendimientos.entity;

/*EVENTO:
● id (autogenerado)
● Detalles del evento (Descripción, info de auspiciantes, premio)
● fecha de creación (o alta)
● fecha de cierre (o alta)
● Estado: ABIERTO | EN CURSO | FINALIZADO
● suscriptores (Emprendimientos que se registraron)
● premio: $
*/

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.informatorio.apirestemprendimientos.dto.EstadoEvento;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Debe ingresar los detalles del evento")
    private String detallesEvento;
    private EstadoEvento estadoEvento;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Emprendimiento> emprendimientosEvento = new ArrayList<>();
    private BigInteger premio;
    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;
    private LocalDateTime fechaDeCierre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDetallesEvento() {
        return detallesEvento;
    }

    public void setDetallesEvento(String detallesEvento) {
        this.detallesEvento = detallesEvento;
    }

    public EstadoEvento getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(EstadoEvento estadoEvento) {
        this.estadoEvento = estadoEvento;
    }

    public void agregarEmprendimiento(Emprendimiento emprendimiento) {
        emprendimientosEvento.add(emprendimiento);
    }

    public void removerEmprendimiento(Emprendimiento emprendimiento) {
        emprendimientosEvento.remove(emprendimiento);
    }

    public BigInteger getPremio() {
        return premio;
    }

    public void setPremio(BigInteger premio) {
        this.premio = premio;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public LocalDateTime getFechaDeCierre() {
        return fechaDeCierre;
    }

    public void setFechaDeCierre(LocalDateTime fechaDeCierre) {
        this.fechaDeCierre = fechaDeCierre;
    }

    public List<Emprendimiento> getEmprendimientosEvento() {
        return emprendimientosEvento;
    }

    public void setEmprendimientosEvento(Emprendimiento emprendimiento) {
        this.emprendimientosEvento.add(emprendimiento);
        emprendimiento.setListaEventos(this);

    }

    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", detallesEvento='" + detallesEvento + '\'' +
                ", estadoEvento='" + estadoEvento + '\'' +
                ", emprendimientos=" + emprendimientosEvento +
                ", premio=" + premio +
                ", fechaDeCreacion=" + fechaDeCreacion +
                ", fechaDeCierre=" + fechaDeCierre +
                '}';
    }
}
