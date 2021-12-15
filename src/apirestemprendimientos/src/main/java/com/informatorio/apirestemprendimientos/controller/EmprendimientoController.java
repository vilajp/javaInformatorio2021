package com.informatorio.apirestemprendimientos.controller;

import com.informatorio.apirestemprendimientos.dto.MostrarEmprendimiento;
import com.informatorio.apirestemprendimientos.dto.ProcesoJsonEmprendimiento;
import com.informatorio.apirestemprendimientos.exception.EmprendimientoException;
import com.informatorio.apirestemprendimientos.entity.Emprendimiento;
import com.informatorio.apirestemprendimientos.entity.TodasLasTags;
import com.informatorio.apirestemprendimientos.entity.TodasLasUrl;
import com.informatorio.apirestemprendimientos.entity.Usuario;
import com.informatorio.apirestemprendimientos.repository.EmprendimientoRepository;
import com.informatorio.apirestemprendimientos.repository.TodasLasTagsRepository;
import com.informatorio.apirestemprendimientos.repository.TodasLasUrlRepository;
import com.informatorio.apirestemprendimientos.repository.UsuarioRepository;
import com.informatorio.apirestemprendimientos.servicios.ServicioEmpredimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmprendimientoController {
    private EmprendimientoRepository emprendimientoRepository;
    private UsuarioRepository usuarioRepository;
    private TodasLasTagsRepository todasLasTagsRepository;
    private TodasLasUrlRepository todasLasUrlRepository;
    private ProcesoJsonEmprendimiento procesoJsonEmprendimiento;
    private ServicioEmpredimiento se;
    private MostrarEmprendimiento muestroEmprendimientos;


    @Autowired
    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository,
                                    UsuarioRepository usuarioRepository,
                                    TodasLasTagsRepository todasLasTagsRepository,
                                    TodasLasUrlRepository todasLasUrlRepository,
                                    ServicioEmpredimiento se) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.todasLasTagsRepository = todasLasTagsRepository;
        this.todasLasUrlRepository = todasLasUrlRepository;
        this.se = se;
    }

    /*@GetMapping(value = "/emprendimiento")
    public ResponseEntity<?> obtenerTodosEmprendimientos() {
        return new ResponseEntity(emprendimientoRepository.findAll(), HttpStatus.OK);
    }*/
    @GetMapping(value = "/emprendimiento")
    public ResponseEntity<?> obtenerTodosEmprendimientos() {
        List<Emprendimiento> todosLosEmprendimientos = emprendimientoRepository.findAll();
        List<MostrarEmprendimiento> listaEmprendimientoFiltrada = todosLosEmprendimientos.stream()
                .map(cadaEmprendimiento -> armoDtoEmprendimiento(cadaEmprendimiento))
                .collect(Collectors.toList());
        return new ResponseEntity(listaEmprendimientoFiltrada, HttpStatus.OK);
    }

    public MostrarEmprendimiento armoDtoEmprendimiento(Emprendimiento cadaemprendimiento){
        MostrarEmprendimiento jsonMostrar = new MostrarEmprendimiento();
        jsonMostrar.setId(cadaemprendimiento.getId());
        jsonMostrar.setNombre(cadaemprendimiento.getNombre());
        jsonMostrar.setDescripcion(cadaemprendimiento.getDescripcion());
        jsonMostrar.setContenido(cadaemprendimiento.getContenido());
        jsonMostrar.setFechaDeCreacion(cadaemprendimiento.getFechaDeCreacion());
        jsonMostrar.setObjetivo(cadaemprendimiento.getObjetivo());
        jsonMostrar.setPublicado(cadaemprendimiento.getPublicado());
        List<String> listaTags = cadaemprendimiento.getTags().stream()
                .map(cadaTag -> cadaTag.getNombre())
                .collect(Collectors.toList());
        jsonMostrar.setTags(listaTags);
        List<String> listaUrls = cadaemprendimiento.getUrls().stream()
                .map(cadaUrl -> cadaUrl.getNombre())
                .collect(Collectors.toList());
        jsonMostrar.setUrls(listaUrls);
        jsonMostrar.setUsuario(cadaemprendimiento.getUsuario().getNombre()+
                " "+cadaemprendimiento.getUsuario().getApellido());
        return jsonMostrar;
    }

    @GetMapping(value = "/emprendimiento/no-publicados")
    public ResponseEntity<?> buscarEmprendimientoSinPublicar() {
        List<Emprendimiento> todosLosEmprendimientos = emprendimientoRepository.findAll();
        List<Emprendimiento> listaEmprendimientoFiltrada = todosLosEmprendimientos.stream()
                .filter(cadaEmprendimiento -> cadaEmprendimiento.getPublicado().equals(false))
                .collect(Collectors.toList());
        return new ResponseEntity(listaEmprendimientoFiltrada, HttpStatus.OK);
    }

    @GetMapping(value = "/emprendimiento/tags/{nombreTag}")
    public ResponseEntity<?> buscarEmprendimiento(@PathVariable String nombreTag) {
        List<TodasLasTags> listaDeTags = todasLasTagsRepository.findAll();
        List<TodasLasTags> listaTagsFiltrada = listaDeTags.stream()
                .filter(cadaTag -> se.buscaTags(cadaTag, nombreTag))
                .collect(Collectors.toList());

        List<Emprendimiento> listaEmprendimientoFiltrada = listaTagsFiltrada.stream()
                .map(cadaTag -> cadaTag.getEmprendimiento())
                .collect(Collectors.toList());

        List<MostrarEmprendimiento> listaEmprendimientos = listaEmprendimientoFiltrada.stream()
                .map(cadaEmprendimiento -> armoDtoEmprendimiento(cadaEmprendimiento))
                .collect(Collectors.toList());

        return new ResponseEntity(listaEmprendimientos, HttpStatus.OK);
    }

    @PostMapping(value = "/usuario/{idUser}/emprendimiento")
    public ResponseEntity<?> crearEmprendimiento(@Valid @PathVariable("idUser") Long idUser,
                                                 @RequestBody() ProcesoJsonEmprendimiento procesoJsonEmprendimiento)
                                                                    throws EmprendimientoException {

        Usuario usuario = usuarioRepository.findById(idUser)
            .orElseThrow(()-> new EmprendimientoException("no existe Usuario"));

        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.setUsuario(usuario);
        List<TodasLasTags> listaObjetosTag = procesoJsonEmprendimiento.getTags().stream()
                .map(cadaTag -> se.creoTags(cadaTag))
                .collect(Collectors.toList());

        listaObjetosTag.stream()
                .forEach(emprendimiento::agregarTags);

        List<TodasLasUrl> listaObjetosUrl = procesoJsonEmprendimiento.getUrls().stream()
                .map(cadaUrl -> se.creoUrls(cadaUrl))
                .collect(Collectors.toList());

        listaObjetosUrl.stream()
                .forEach(emprendimiento::agregarUrl);

        emprendimiento.setNombre(procesoJsonEmprendimiento.getNombre());
        emprendimiento.setObjetivo(procesoJsonEmprendimiento.getObjetivo());
        emprendimiento.setDescripcion(procesoJsonEmprendimiento.getDescripcion());
        emprendimiento.setContenido(procesoJsonEmprendimiento.getContenido());

        usuario.agregarEmprendimiento(emprendimiento);

        return new ResponseEntity(emprendimientoRepository.save(emprendimiento), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/emprendimiento/{id}")
    public void borrarEmprendimiento(@PathVariable("id") Long id) {
        emprendimientoRepository.deleteById(id);
    }

    @PutMapping(value = "/emprendimiento/{id}")
    public ResponseEntity<?> modificarEmprendimiento(@PathVariable("id") Long id,
                                                     @RequestBody Emprendimiento emprendimientoQueVino) {
        Emprendimiento emprendimientoAModificar = emprendimientoRepository.findById(id).get();
        Emprendimiento emprendimientoModificado = se.modificoEmprendimiento(emprendimientoAModificar,
                emprendimientoQueVino);
        return new ResponseEntity(emprendimientoRepository.save(emprendimientoModificado), HttpStatus.OK);
    }


}
