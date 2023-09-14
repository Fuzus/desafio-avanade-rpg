package br.com.fuzus.avanadedesafiorpg.infra.exceptions;

import org.springframework.validation.FieldError;

public record ValidationErrorsData(String field, String message){
    public ValidationErrorsData(FieldError error){
        this(error.getField(), error.getDefaultMessage());
    }
}
