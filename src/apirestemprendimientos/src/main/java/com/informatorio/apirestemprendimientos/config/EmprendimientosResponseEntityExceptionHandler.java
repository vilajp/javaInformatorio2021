package com.informatorio.apirestemprendimientos.config;

import com.informatorio.apirestemprendimientos.dto.ApiError;
import com.informatorio.apirestemprendimientos.exception.EmprendimientoException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class EmprendimientosResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status, WebRequest request) {
        ApiError error = new ApiError();
        error.setStatus(status);
        List<String> mensaje = List.of(ex.getMessage().split(";"));
        List<String> listaErrores = mensaje.stream()
                .filter(cadaError -> cadaError.contains("default message"))
                .collect(Collectors.toList());
        String mensajeError = listaErrores.toString();
        error.setMensaje(mensajeError);
        error.setCantidadDeErrores(ex.getErrorCount());
        return new ResponseEntity<Object>(error, HttpStatus.I_AM_A_TEAPOT);
    }

    @ExceptionHandler(EmprendimientoException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(Exception ex,
                                                                WebRequest request) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMensaje(ex.getMessage());
        /*error.setCantidadDeErrores(ex.getErrorCount());*/
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(Exception ex,
                                                                WebRequest request) {
        ApiError error = new ApiError();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMensaje(ex.getMessage());

        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }
}