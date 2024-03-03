package com.rasmoo.cliente.escola.gradecurricular.handler;

import com.rasmoo.cliente.escola.gradecurricular.exceptions.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.model.ErrorMapResponse;
import com.rasmoo.cliente.escola.gradecurricular.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ResourceHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMapResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException m){

        Map<String, String> erros = new HashMap<>();
        m.getBindingResult()
                .getAllErrors()
                .forEach(erro->{
                    String campo = ((FieldError)erro).getField();
                    String mensagem = erro.getDefaultMessage();
                    erros.put(campo, mensagem);
                });

        ErrorMapResponse.ErrorMapResponseBuilder errorMap = ErrorMapResponse.builder();

        errorMap.erros(erros)
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .timeStamp(System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap.build());
    }
    @ExceptionHandler(MateriaException.class)
    public ResponseEntity<ErrorResponse> handlerMateriaException(MateriaException m){
        ErrorResponse.ErrorResponseBuilder erro = ErrorResponse.builder();
        erro.httpStatus(m.getHttpStatus().value());
        erro.mensagem(m.getMessage());
        erro.timestamp(System.currentTimeMillis());

        return ResponseEntity.status(m.getHttpStatus()).body(erro.build());
    }


}
