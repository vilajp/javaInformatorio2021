package com.informatorio.apirestemprendimientos.controller;

import com.informatorio.apirestemprendimientos.entity.Emprendimiento;
import com.informatorio.apirestemprendimientos.entity.Evento;
import com.informatorio.apirestemprendimientos.entity.Usuario;
import com.informatorio.apirestemprendimientos.entity.Voto;
import com.informatorio.apirestemprendimientos.exception.EmprendimientoException;
import com.informatorio.apirestemprendimientos.repository.EmprendimientoRepository;
import com.informatorio.apirestemprendimientos.repository.EventoRepository;
import com.informatorio.apirestemprendimientos.repository.VotoRepository;
import com.informatorio.apirestemprendimientos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.informatorio.apirestemprendimientos.dto.EstadoEvento.CERRADO;

@RestController
public class VotoController {
    private EmprendimientoRepository emprendimientoRepository;
    private UsuarioRepository usuarioRepository;
    private VotoRepository votoRepository;
    private EventoRepository eventoRepository;

    @Autowired
    public VotoController(EmprendimientoRepository emprendimientoRepository,
                          UsuarioRepository usuarioRepository,
                          VotoRepository votoRepository,
                          EventoRepository eventoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.votoRepository = votoRepository;
        this.eventoRepository = eventoRepository;
    }

    @PostMapping(value = "/usuario/{usuarioId}/emprendimiento/{emprendimientoId}/voto")
    public ResponseEntity<?> votar(@RequestBody Voto voto,
                                   @PathVariable Long usuarioId,
                                   @PathVariable Long emprendimientoId) throws EmprendimientoException{
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() ->new EmprendimientoException("No existe ese Usuario"));
        Emprendimiento emprendimiento = emprendimientoRepository.findById(emprendimientoId)
                .orElseThrow(() ->new EmprendimientoException("No existe ese Emprendimiento"));
        List<Voto> todosLosVotos = votoRepository.findAll();
        List<Voto> buscoVoto = todosLosVotos.stream()
                .filter(cadaVoto -> cadaVoto.getEmprendimiento().getId().equals(emprendimientoId)&&
                        cadaVoto.getUsuario().getId().equals(usuarioId))
                .collect(Collectors.toList());

        int cantidadEventos = emprendimiento.getListaEventos().size();

        Voto nuevoVoto = new Voto();
        if (buscoVoto.size() == 0 && cantidadEventos==0){
            nuevoVoto.setUsuario(usuario);
            nuevoVoto.setGeneradoDesde(voto.getGeneradoDesde());
            nuevoVoto.setEmprendimiento(emprendimiento);
        }else if (buscoVoto.size()==1 && cantidadEventos>0){
            throw new EmprendimientoException("Emprendimiento en Evento, para votar use Url: /usuario/"+usuarioId+
                    "/evento/{eventoId}/emprendimiento/"+emprendimientoId);
        }else{
            throw new EmprendimientoException("Ud ya voto por ese emprendimiento");
        }
        return new ResponseEntity(votoRepository.save(nuevoVoto), HttpStatus.CREATED);
    }

    @PostMapping(value = "/usuario/{usuarioId}/evento/eventoId/emprendimiento/{emprendimientoId}/voto")
    public ResponseEntity<?> votarEnEvento(@RequestBody Voto voto,
                                   @PathVariable Long usuarioId,
                                   @PathVariable Long emprendimientoId,
                                   @PathVariable Long eventoId) throws EmprendimientoException{
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() ->new EmprendimientoException("No existe ese Usuario"));
        Emprendimiento emprendimiento = emprendimientoRepository.findById(emprendimientoId)
                .orElseThrow(() ->new EmprendimientoException("No existe ese Emprendimiento"));
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() ->new EmprendimientoException("No existe ese Evento"));
        List<Voto> todosLosVotos = votoRepository.findAll();
        List<Voto> buscoVoto = todosLosVotos.stream()
                .filter(cadaVoto -> cadaVoto.getEmprendimiento().getId().equals(emprendimientoId)&&
                        cadaVoto.getUsuario().getId().equals(usuarioId)&&cadaVoto.getEvento().equals(eventoId))
                .collect(Collectors.toList());

        int cantidadEventos = emprendimiento.getListaEventos().size();

        if(cantidadEventos == 0){
            throw new EmprendimientoException("Este emprendimiento no esta inscripto en ningun evento");
        }
        List<Emprendimiento> listaEmprendimientos = evento.getEmprendimientosEvento().stream()
                .filter(cadaEmprendimiento->cadaEmprendimiento.getId().equals(emprendimientoId))
                .collect(Collectors.toList());
        if(listaEmprendimientos.size()==0){
            throw new EmprendimientoException("Ese emprendimiento no corresponde a Evento");
        }
        if (evento.getEstadoEvento().equals(CERRADO)){
            throw new EmprendimientoException("Ese evento se encuentra cerrado, no puede votar");
        }

        Voto nuevoVoto = new Voto();
        if (buscoVoto.size() == 0){
            nuevoVoto.setUsuario(usuario);
            nuevoVoto.setGeneradoDesde(voto.getGeneradoDesde());
            nuevoVoto.setEmprendimiento(emprendimiento);
            nuevoVoto.setEvento(evento);
        }else{
            throw new EmprendimientoException("Ud ya voto por ese emprendimiento");
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


