package com.informatorio.apirestemprendimientos.dto;

import java.time.LocalDateTime;

public class VotoPorUsuario {
    private Long Id;
    private String generadoDesde;
    private LocalDateTime fechaCreacion;
    private String EmprendimientoVotado;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getGeneradoDesde() {
        return generadoDesde;
    }

    public void setGeneradoDesde(String generadoDesde) {
        this.generadoDesde = generadoDesde;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEmprendimientoVotado() {
        return EmprendimientoVotado;
    }

    public void setEmprendimientoVotado(String emprendimientoVotado) {
        EmprendimientoVotado = emprendimientoVotado;
    }
}
