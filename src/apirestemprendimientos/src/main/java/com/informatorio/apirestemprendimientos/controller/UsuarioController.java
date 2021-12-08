package com.informatorio.apirestemprendimientos.controller;

import com.informatorio.apirestemprendimientos.entity.Usuario;
import com.informatorio.apirestemprendimientos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping( value="/usuario")
public class UsuarioController {
    private UsuarioRepository usuarioRepository;


    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/consulta/{campo}/{valor}")
    public ResponseEntity<?> buscarUsuario(@PathVariable String campo, @PathVariable String valor) {
        List<Usuario> listaDeUsuarios = usuarioRepository.findAll();
        List<Usuario> listaFiltrada = listaDeUsuarios.stream()
                .filter(cadaUsuario -> buscaUsuarios(cadaUsuario, campo, valor))
                .collect(Collectors.toList());
        return new ResponseEntity(listaFiltrada, HttpStatus.OK);
    }

    @GetMapping(value="/consulta/{dia}/{mes}/{anio}")
    public ResponseEntity<?> buscaUsuarioFecha(@PathVariable String dia,
                                                @PathVariable String mes,
                                                @PathVariable String anio){
        List<Usuario> listaDeUsuarios = usuarioRepository.findAll();
        List<Usuario> listaFiltrada = listaDeUsuarios.stream()
                .filter(cadaUsuario -> buscoUsuarioFecha(cadaUsuario.getFechaDeCreacion(), dia, mes, anio))
                .collect(Collectors.toList());
        return new ResponseEntity(listaFiltrada, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Usuario usuario) {
        return new ResponseEntity(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public void borrarUsuario(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);
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
        LocalDateTime fechaBusqueda = LocalDateTime.parse(anio+"-"+mes+"-"+dia+"T00:00:00.000");
            return fechaCreacion.isAfter(fechaBusqueda)==Boolean.TRUE;
    }
}



