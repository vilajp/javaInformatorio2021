package com.informatorio.apirestemprendimientos.controller;

import com.informatorio.apirestemprendimientos.repository.EmprendimientoRepository;
import com.informatorio.apirestemprendimientos.repository.UsuarioRepository;
import com.informatorio.apirestemprendimientos.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventoController {

    private EmprendimientoRepository emprendimientoRepository;
    private UsuarioRepository usuarioRepository;
    private VotoRepository votoRepository;

    @Autowired
    public EventoController(EmprendimientoRepository emprendimientoRepository,
                          UsuarioRepository usuarioRepository,
                          VotoRepository votoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.votoRepository = votoRepository;
    }
}
