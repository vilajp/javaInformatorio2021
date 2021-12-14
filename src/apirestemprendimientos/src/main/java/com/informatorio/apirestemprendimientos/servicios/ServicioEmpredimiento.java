package com.informatorio.apirestemprendimientos.servicios;

import com.informatorio.apirestemprendimientos.entity.Emprendimiento;
import com.informatorio.apirestemprendimientos.entity.TodasLasTags;
import com.informatorio.apirestemprendimientos.entity.TodasLasUrl;
import org.springframework.stereotype.Service;

@Service
public class ServicioEmpredimiento {

    public boolean buscaTags(TodasLasTags cadaTag, String nombreTag){
        String cadaNombreTag = cadaTag.getNombre();
        return cadaNombreTag.contains(nombreTag);
    }

    public TodasLasTags creoTags(String cadaTag) {
        TodasLasTags nuevaTag = new TodasLasTags();
        nuevaTag.setNombre(cadaTag);
        return nuevaTag;
    }

    public TodasLasUrl creoUrls(String cadaUrl) {
        TodasLasUrl nuevaUrl = new TodasLasUrl();
        nuevaUrl.setNombre(cadaUrl);
        return nuevaUrl;
    }

    public Emprendimiento modificoEmprendimiento(Emprendimiento emprendimientoAModificar,
                                                 Emprendimiento emprendimientoQueVino) {
        emprendimientoAModificar.setNombre(emprendimientoQueVino.getNombre());
        emprendimientoAModificar.setDescripcion(emprendimientoQueVino.getDescripcion());
        emprendimientoAModificar.setContenido(emprendimientoQueVino.getContenido());
        emprendimientoAModificar.setObjetivo(emprendimientoQueVino.getObjetivo());
        emprendimientoAModificar.setPublicado(emprendimientoQueVino.getPublicado());
        return emprendimientoAModificar;
    }
}
