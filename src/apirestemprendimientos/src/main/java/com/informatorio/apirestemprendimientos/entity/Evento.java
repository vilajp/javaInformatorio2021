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

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String detallesEvento;
    private String estadoEvento;
    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public String getEstadoEvento() {
        return estadoEvento;
    }

    public void setEstadoEvento(String estadoEvento) {
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
