package com.rasmoo.cliente.escola.gradecurricular.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MateriaException extends RuntimeException{
    private static final long serialVersionUID = 1;

    private final HttpStatus httpStatus;

    public MateriaException(final String mensagem,
                            final HttpStatus httpStatus){
        super(mensagem);
        this.httpStatus = httpStatus;
    }
}
