package com.informatorio.apirestemprendimientos.controller;

import com.informatorio.apirestemprendimientos.entity.Emprendimiento;
import com.informatorio.apirestemprendimientos.entity.Evento;
import com.informatorio.apirestemprendimientos.entity.Usuario;
import com.informatorio.apirestemprendimientos.exception.EmprendimientoException;
import com.informatorio.apirestemprendimientos.repository.EmprendimientoRepository;
import com.informatorio.apirestemprendimientos.repository.EventoRepository;
import com.informatorio.apirestemprendimientos.repository.UsuarioRepository;
import com.informatorio.apirestemprendimientos.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.informatorio.apirestemprendimientos.dto.EstadoEvento.ABIERTO;

@RestController
public class EventoController {

    private EmprendimientoRepository emprendimientoRepository;
    private UsuarioRepository usuarioRepository;
    private VotoRepository votoRepository;
    private EventoRepository eventoRepository;

    @Autowired
    public EventoController(EmprendimientoRepository emprendimientoRepository,
                          UsuarioRepository usuarioRepository,
                          VotoRepository votoRepository,
                            EventoRepository eventoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.votoRepository = votoRepository;
        this.eventoRepository = eventoRepository;
    }

    @PostMapping(value = "/evento")
    public ResponseEntity<?> crear(@Valid @RequestBody Evento evento) throws EmprendimientoException {
        evento.setEstadoEvento(ABIERTO);
        return new ResponseEntity(eventoRepository.save(evento), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/evento/{id}")
    public void borrarEvento(@PathVariable("id") Long id) {
        eventoRepository.deleteById(id);
    }

    @PutMapping(value = "/evento/{id}")
    public ResponseEntity<?> modificarEvento(@PathVariable("id") Long id,
                                                     @RequestBody Evento eventoQueVino)
                                            throws EmprendimientoException {
        Evento eventoAModificar = eventoRepository.findById(id).get();
        Evento eventoModificado = modificoEvento(eventoAModificar,
                eventoQueVino);
        return new ResponseEntity(eventoRepository.save(eventoModificado), HttpStatus.OK);
    }

    public Evento modificoEvento(Evento eventoAModificar,
                                                 Evento eventoQueVino) {
        eventoAModificar.setDetallesEvento(eventoQueVino.getDetallesEvento());
        eventoAModificar.setFechaDeCierre(eventoQueVino.getFechaDeCierre());
        eventoAModificar.setPremio(eventoQueVino.getPremio());
        return eventoAModificar;
    }

    @GetMapping(value="/evento/{eventoId}/emprendimiento/{emprendimientoId}")
    public ResponseEntity<?> inscribirEnEvento(@PathVariable("eventoId") Long eventoId,
                                               @PathVariable("emprendimientoId") Long emprendimientoId)
            throws EmprendimientoException {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new EmprendimientoException("no existe Evento"));
        Emprendimiento emprendimiento = emprendimientoRepository.findById(emprendimientoId)
                .orElseThrow(() -> new EmprendimientoException("no existe emprendimiento"));
        if (evento.getEstadoEvento().equals(ABIERTO)&&emprendimiento.getEvento().equals(null)) {
            evento.setEmprendimientosEvento(emprendimiento);
            emprendimiento.setEvento(evento);
        }
        return new ResponseEntity(eventoRepository.save(evento), HttpStatus.OK);
    }
}
