package com.informatorio.apirestemprendimientos.controller;

import com.informatorio.apirestemprendimientos.entity.Usuario;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @GetMapping( value="/usuario")
    public List<Usuario> obtenerTodos(){
        return new ArrayList<Usuario>();
    }
}
