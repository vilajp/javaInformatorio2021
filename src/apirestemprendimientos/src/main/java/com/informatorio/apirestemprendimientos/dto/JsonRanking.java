package com.informatorio.apirestemprendimientos.dto;


public class JsonRanking {
    private Long Id;
    private String nombre;
    private Integer votos;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    @Override
    public String toString() {
        return "JsonRanking{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", votos=" + votos +
                '}';
    }
}
