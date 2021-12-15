package com.informatorio.apirestemprendimientos.controller;

import com.informatorio.apirestemprendimientos.entity.Usuario;
import com.informatorio.apirestemprendimientos.exception.EmprendimientoException;
import com.informatorio.apirestemprendimientos.repository.UsuarioRepository;
import com.informatorio.apirestemprendimientos.servicios.ServicioUsuario;
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
    private ServicioUsuario su;


    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository, ServicioUsuario su) {

        this.usuarioRepository = usuarioRepository;
        this.su = su;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodos() {
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/consulta/{campo}/{valor}")
    public ResponseEntity<?> buscarUsuario(@PathVariable String campo, @PathVariable String valor) {
        List<Usuario> listaDeUsuarios = usuarioRepository.findAll();
        List<Usuario> listaFiltrada = listaDeUsuarios.stream()
                .filter(cadaUsuario -> su.buscaUsuarios(cadaUsuario, campo, valor))
                .collect(Collectors.toList());
        return new ResponseEntity(listaFiltrada, HttpStatus.OK);
    }

    @GetMapping(value="/consulta/{dia}/{mes}/{anio}")
    public ResponseEntity<?> buscaUsuarioFecha(@PathVariable String dia,
                                                @PathVariable String mes,
                                                @PathVariable String anio){
        List<Usuario> listaDeUsuarios = usuarioRepository.findAll();
        List<Usuario> listaFiltrada = listaDeUsuarios.stream()
                .filter(cadaUsuario -> su.buscoUsuarioFecha(cadaUsuario.getFechaDeCreacion(), dia, mes, anio))
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
        Usuario usuarioAModificar = usuarioRepository.findById(id)
                .orElseThrow(() -> new EmprendimientoException("no existe Usuario"));
        Usuario usuarioModificado = null;
        if (usuarioQueVino.getId().equals(id)) {
            usuarioModificado = su.modificoUsuario(usuarioAModificar, usuarioQueVino);
        }
        return new ResponseEntity(usuarioRepository.save(usuarioModificado), HttpStatus.OK);
    }



}



