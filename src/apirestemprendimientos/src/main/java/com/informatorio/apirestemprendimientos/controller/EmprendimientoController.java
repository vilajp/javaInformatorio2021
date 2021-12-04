package com.informatorio.apirestemprendimientos.controller;

import com.informatorio.apirestemprendimientos.entity.Emprendimiento;
import com.informatorio.apirestemprendimientos.entity.Usuario;
import com.informatorio.apirestemprendimientos.repository.EmprendimientoRepository;
import com.informatorio.apirestemprendimientos.repository.UsuarioRepository;
import net.bytebuddy.pool.TypePool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping( value="/emprendimiento")
public class EmprendimientoController {
    private EmprendimientoRepository emprendimientoRepository;

    @Autowired
    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository){
        this.emprendimientoRepository= emprendimientoRepository;
    }

    @GetMapping
    public ResponseEntity<?> obtenerTodosEmprendimientos(){
        return new ResponseEntity(emprendimientoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearEmprendimiento(@RequestBody Emprendimiento emprendimiento){
        return new ResponseEntity(emprendimientoRepository.save(emprendimiento), HttpStatus.CREATED);
    }
}
