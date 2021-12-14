package com.informatorio.apirestemprendimientos.controller;

import com.informatorio.apirestemprendimientos.entity.Emprendimiento;
import com.informatorio.apirestemprendimientos.entity.Evento;
import com.informatorio.apirestemprendimientos.entity.Usuario;
import com.informatorio.apirestemprendimientos.entity.Voto;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping(value = "/evento/{eventoId}/emprendimiento/{emprendimientoId}")
    public ResponseEntity<?> inscribirEnEvento(@PathVariable("eventoId") Long eventoId,
                                               @PathVariable("emprendimientoId") Long emprendimientoId)
            throws EmprendimientoException {
        Evento evento = eventoRepository.findById(eventoId)
                .orElseThrow(() -> new EmprendimientoException("no existe Evento"));
        Emprendimiento emprendimiento = emprendimientoRepository.findById(emprendimientoId)
                .orElseThrow(() -> new EmprendimientoException("no existe emprendimiento"));
        List<Evento> listaEventos = emprendimiento.getListaEventos().stream()
                .filter(cadaEvento -> cadaEvento.getId().equals(eventoId))
                .collect(Collectors.toList());
        if (evento.getEstadoEvento().equals(ABIERTO) && listaEventos.size() == 0) {
            evento.setEmprendimientosEvento(emprendimiento);

        } else {
            throw new EmprendimientoException("El Evento esta cerrado o ese emprendimiento ya esta inscripto");
        }
        return new ResponseEntity(eventoRepository.save(evento), HttpStatus.OK);
    }

    @GetMapping(value = "/evento/{id}/ranking")
    public ResponseEntity<?> rankingEmprendimientos(@PathVariable("id") Long id)
            throws EmprendimientoException {

        List<Voto> listaVotos = votoRepository.findAll().stream()
                .filter(cadaVoto -> cadaVoto.getEvento().getId().equals(id))
                .collect(Collectors.toList());

        Map<Long, Integer> resultados = new HashMap<>();

        for (Voto listaVoto : listaVotos) {
            Integer contador = resultados.getOrDefault(listaVoto.getId(), 0);
            resultados.put(listaVoto.getEmprendimiento().getId(), contador++);
        }
        List<Long> idsOrdenados = new ArrayList<>();
        List<Integer> numeros = new ArrayList<>();
        while (resultados.size() != 0) {
            Integer mayor = 0;
            Long idMayor = Long.valueOf(0);
            for (Map.Entry<Long, Integer> entry : resultados.entrySet()) {
                if (entry.getKey() > mayor) {
                    mayor = entry.getValue();
                    idMayor = entry.getKey();
                }
            }
            idsOrdenados.add(idMayor);
            numeros.add(mayor);
            resultados.remove(idMayor);
        }
        List<Emprendimiento> ranking = null;
        for (Long cadaId : idsOrdenados) {
            ranking.add(emprendimientoRepository.findById(cadaId).get());
        }
        return new ResponseEntity(ranking, HttpStatus.OK);

    }
}
