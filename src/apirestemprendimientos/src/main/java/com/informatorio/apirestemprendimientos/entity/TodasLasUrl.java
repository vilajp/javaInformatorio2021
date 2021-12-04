package com.informatorio.apirestemprendimientos.entity;

import javax.persistence.*;

@Entity
public class TodasLasUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @ManyToOne(fetch = FetchType.LAZY)
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
}
