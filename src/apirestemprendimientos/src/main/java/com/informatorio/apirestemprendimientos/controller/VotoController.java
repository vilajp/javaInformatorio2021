package com.informatorio.apirestemprendimientos.controller;

import com.informatorio.apirestemprendimientos.entity.Emprendimiento;
import com.informatorio.apirestemprendimientos.entity.Usuario;
import com.informatorio.apirestemprendimientos.entity.Voto;
import com.informatorio.apirestemprendimientos.repository.EmprendimientoRepository;
import com.informatorio.apirestemprendimientos.repository.VotoRepository;
import com.informatorio.apirestemprendimientos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class VotoController {
    private EmprendimientoRepository emprendimientoRepository;
    private UsuarioRepository usuarioRepository;
    private VotoRepository votoRepository;

    @Autowired
    public VotoController(EmprendimientoRepository emprendimientoRepository,
                          UsuarioRepository usuarioRepository,
                          VotoRepository votoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.votoRepository = votoRepository;
    }

    @PostMapping(value = "/usuario/{usuarioId}/emprendimiento/{emprendimientoId}/voto")
    public ResponseEntity<?> votar(@RequestBody Voto voto,
                                   @PathVariable Long usuarioId,
                                   @PathVariable Long emprendimientoId) {
        Usuario usuario = usuarioRepository.findById(usuarioId).get();
        Emprendimiento emprendimiento = emprendimientoRepository.findById(emprendimientoId).get();
        List<Voto> todosLosVotos = votoRepository.findAll();
        List<Voto> buscoVoto = todosLosVotos.stream()
                .filter(cadaVoto -> cadaVoto.getEmprendimiento().getId().equals(emprendimientoId)&&
                        cadaVoto.getUsuario().getId().equals(usuarioId))
                .collect(Collectors.toList());
        Voto nuevoVoto = new Voto();
        if (buscoVoto.size() == 0||(buscoVoto.size()==1&&buscoVoto.get(0).getEnEvento().equals(Boolean.TRUE))){
            nuevoVoto.setUsuario(usuario);
            nuevoVoto.setGeneradoDesde(voto.getGeneradoDesde());
            nuevoVoto.setEmprendimiento(emprendimiento);
            if (emprendimiento.getEvento() == null) {
                nuevoVoto.setEnEvento(false);
            } else {
                nuevoVoto.setEnEvento(true);
            }
        }else{
            return new ResponseEntity(buscoVoto, HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(votoRepository.save(nuevoVoto), HttpStatus.CREATED);
    }

    @GetMapping("/usuario/{usuarioId}/votos")
    public ResponseEntity<?> votar(@PathVariable Long usuarioId) {
        List<Voto> todosLosVotos =  votoRepository.findAll();
        List<Voto> votosUsuario = todosLosVotos.stream()
                .filter(cadaVoto -> cadaVoto.getUsuario().getId().equals(usuarioId))
                .collect(Collectors.toList());
        return new ResponseEntity(votosUsuario, HttpStatus.OK);
    }
}


