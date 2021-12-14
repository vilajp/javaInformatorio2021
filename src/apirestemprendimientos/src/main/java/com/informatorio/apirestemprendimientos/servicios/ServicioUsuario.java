package com.informatorio.apirestemprendimientos.servicios;

import com.informatorio.apirestemprendimientos.entity.Usuario;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ServicioUsuario {

    public Usuario modificoUsuario(Usuario usuarioAModificar, Usuario usuarioQueVino){
        usuarioAModificar.setNombre(usuarioQueVino.getNombre());
        usuarioAModificar.setApellido(usuarioQueVino.getApellido());
        usuarioAModificar.setCiudad(usuarioQueVino.getCiudad());
        usuarioAModificar.setProvincia(usuarioQueVino.getProvincia());
        usuarioAModificar.setPais(usuarioQueVino.getPais());
        usuarioAModificar.setEmail(usuarioQueVino.getEmail());
        usuarioAModificar.setTipo(usuarioQueVino.getTipo());
        return usuarioAModificar;
    }

    public Boolean buscaUsuarios(Usuario cadaUsuario, String campo, String valor) {
        switch (campo) {
            case "nombre":
                return cadaUsuario.getNombre().toLowerCase().equals(valor.toLowerCase());
            case "apellido":
                return cadaUsuario.getApellido().toLowerCase().equals(valor.toLowerCase());
            case "ciudad":
                return cadaUsuario.getCiudad().toLowerCase().equals(valor.toLowerCase());
            case "pais":
                return cadaUsuario.getPais().toLowerCase().equals(valor.toLowerCase());
            case "provincia":
                return cadaUsuario.getProvincia().toLowerCase().equals(valor.toLowerCase());
            case "email":
                return cadaUsuario.getEmail().toLowerCase().equals(valor.toLowerCase());

        }
        return null;
    }

    public Boolean buscoUsuarioFecha(LocalDateTime fechaCreacion, String dia, String mes, String anio){
        LocalDateTime fechaBusqueda = LocalDateTime.parse(anio+"-"+mes+"-"+dia+"T23:59:59.000");
        return fechaCreacion.isAfter(fechaBusqueda)==Boolean.TRUE;
    }
}
