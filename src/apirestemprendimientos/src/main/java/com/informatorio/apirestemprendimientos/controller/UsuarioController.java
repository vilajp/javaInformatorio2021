package com.informatorio.apirestemprendimientos.controller;

import com.informatorio.apirestemprendimientos.entity.Usuario;
import com.informatorio.apirestemprendimientos.exception.EmprendimientoException;
import com.informatorio.apirestemprendimientos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
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
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario) throws EmprendimientoException {
        return new ResponseEntity(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public void borrarUsuario(@PathVariable("id") Long id) throws EmprendimientoException {
        usuarioRepository.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable("id") Long id,
                                            @RequestBody Usuario usuarioQueVino) throws EmprendimientoException {
        Usuario usuarioAModificar = usuarioRepository.findById(id).get();
        Usuario usuarioModificado = modificoUsuario(usuarioAModificar, usuarioQueVino);
        return new ResponseEntity(usuarioRepository.save(usuarioModificado), HttpStatus.OK);
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

    public Usuario modificoUsuario(Usuario usuarioAModificar, Usuario usuarioQueVino){
        usuarioAModificar.setNombre(usuarioQueVino.getNombre());
        usuarioAModificar.setApellido(usuarioQueVino.getApellido());
        usuarioAModificar.setCiudad(usuarioQueVino.getCiudad());
        usuarioAModificar.setProvincia(usuarioQueVino.getProvincia());
        usuarioAModificar.setPais(usuarioQueVino.getPais());
        usuarioAModificar.setEmail(usuarioQueVino.getEmail());
        return usuarioAModificar;

    }

    public Boolean buscoUsuarioFecha(LocalDateTime fechaCreacion, String dia, String mes, String anio){
        LocalDateTime fechaBusqueda = LocalDateTime.parse(anio+"-"+mes+"-"+dia+"T23:59:59.000");
            return fechaCreacion.isAfter(fechaBusqueda)==Boolean.TRUE;
    }
}



