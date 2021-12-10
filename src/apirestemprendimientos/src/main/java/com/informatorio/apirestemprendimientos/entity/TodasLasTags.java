package com.informatorio.apirestemprendimientos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class TodasLasTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Emprendimiento emprendimiento;

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

    public Emprendimiento getEmprendimiento() {
        return emprendimiento;
    }

    public void setEmprendimiento(Emprendimiento emprendimiento) {
        this.emprendimiento = emprendimiento;
    }

    @Override
    public String toString() {
        return "TodasLasTags{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", emprendimiento=" + emprendimiento +
                '}';
    }
}
