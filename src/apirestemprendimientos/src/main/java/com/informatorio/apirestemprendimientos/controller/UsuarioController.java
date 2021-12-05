package com.informatorio.apirestemprendimientos.controller;

import com.informatorio.apirestemprendimientos.entity.Usuario;
import com.informatorio.apirestemprendimientos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping( value="/usuario")
public class UsuarioController {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<?>  obtenerTodos(){
        return new ResponseEntity(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Usuario usuario){
        return new ResponseEntity(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public void borrarUsuario(@PathVariable("id") Long id){
        usuarioRepository.deleteById(id);
    }
}
