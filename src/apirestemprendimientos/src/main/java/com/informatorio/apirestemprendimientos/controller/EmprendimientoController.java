package com.informatorio.apirestemprendimientos.controller;

import com.informatorio.apirestemprendimientos.entity.Emprendimiento;
import com.informatorio.apirestemprendimientos.entity.TodasLasTags;
import com.informatorio.apirestemprendimientos.entity.TodasLasUrl;
import com.informatorio.apirestemprendimientos.entity.Usuario;
import com.informatorio.apirestemprendimientos.repository.EmprendimientoRepository;
import com.informatorio.apirestemprendimientos.repository.TodasLasTagsRepository;
import com.informatorio.apirestemprendimientos.repository.TodasLasUrlRepository;
import com.informatorio.apirestemprendimientos.repository.UsuarioRepository;
import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class EmprendimientoController {
    private EmprendimientoRepository emprendimientoRepository;
    private UsuarioRepository usuarioRepository;
    private TodasLasTagsRepository todasLasTagsRepository;
    private TodasLasUrlRepository todasLasUrlRepository;

    @Autowired
    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository,
                                    UsuarioRepository usuarioRepository,
                                    TodasLasTagsRepository todasLasTagsRepository,
                                    TodasLasUrlRepository todasLasUrlRepository){
        this.emprendimientoRepository= emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.todasLasTagsRepository = todasLasTagsRepository;
        this.todasLasUrlRepository = todasLasUrlRepository;
    }

    @GetMapping( value="/emprendimiento")
    public ResponseEntity<?> obtenerTodosEmprendimientos(){
        return new ResponseEntity(emprendimientoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/usuario/{idUser}/emprendimiento")
    public ResponseEntity<?> crearEmprendimiento(@PathVariable ("idUser") Long idUser,
                                                 @RequestBody() Map<?, ?> todoElJson)
                                                 {
        Usuario usuario = usuarioRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.setUsuario(usuario);
        Map<?, ?> jsonSinUrlNiTags = todoElJson.entrySet().stream()
                .filter(cadaClave-> cadaClave.getKey()!="tags"||cadaClave.getKey()!="urls")
                .collect(Collectors.toMap(cadaClave -> cadaClave.getKey(), cadaValor-> cadaValor.getValue()));

        List<String> listaTags = (List<String>) todoElJson.get("tags");
        List<String> listaUrls = (List<String>) todoElJson.get("urls");

        List<TodasLasTags> listaDeObjetosTag = listaTags.stream()
                .map(cadaTag -> creoTags(cadaTag))
                .collect(Collectors.toList());

        listaDeObjetosTag.stream()
                .forEach(emprendimiento::agregarTags);

         List<TodasLasUrl> listaDeObjetosUrl = listaUrls.stream()
                 .map(cadaUrl -> creoUrls(cadaUrl))
                 .collect(Collectors.toList());

         listaDeObjetosUrl.stream()
                 .forEach(emprendimiento::agregarUrl);

         emprendimiento.setNombre((String) jsonSinUrlNiTags.get("nombre"));
         emprendimiento.setContenido((String) jsonSinUrlNiTags.get("contenido"));
         emprendimiento.setDescripcion((String) jsonSinUrlNiTags.get("descripcion"));
         emprendimiento.setObjetivo((Number) jsonSinUrlNiTags.get("objetivo"));

        return new ResponseEntity(emprendimientoRepository.save(emprendimiento), HttpStatus.CREATED);
    }

    public TodasLasTags creoTags(String cadaTag){
        TodasLasTags nuevaTag = new TodasLasTags();
        nuevaTag.setNombre(cadaTag);
        return nuevaTag;
    }

    public TodasLasUrl creoUrls(String cadaUrl){
        TodasLasUrl nuevaUrl = new TodasLasUrl();
        nuevaUrl.setNombre(cadaUrl);
        return nuevaUrl;
    }
}
