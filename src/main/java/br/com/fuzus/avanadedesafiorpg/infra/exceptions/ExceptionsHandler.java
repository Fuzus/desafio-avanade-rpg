package br.com.fuzus.avanadedesafiorpg.infra.exceptions;

import br.com.fuzus.avanadedesafiorpg.domain.battle.exceptions.UnableToMoveException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> handle404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorsData::new).toList());
    }

    @ExceptionHandler(UnableToMoveException.class)
    public ResponseEntity<String> handleError423(UnableToMoveException e){
        return ResponseEntity.status(HttpStatus.LOCKED).body(e.getMessage());
    }

    private record ValidationErrorsData(String field, String message){
        public ValidationErrorsData(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
