package br.com.zup.projetopropostacartao.config;

import br.com.zup.projetopropostacartao.validators.PropostaDuplicadaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Collection;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadronizado> argumentNotValidHandler(MethodArgumentNotValidException exception) {
        Collection<String> mensagens = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(error -> {
            mensagens.add(String.format("Campo %s %s", error.getField(), error.getDefaultMessage()));
        });

        ErroPadronizado erroPadronizado = new ErroPadronizado(mensagens);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadronizado);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(PropostaDuplicadaException.class)
    public ResponseEntity<ErroPadronizado> propostaDuplicadaHandler(PropostaDuplicadaException exception) {
        Collection<String> mensagens = new ArrayList<>();

        mensagens.add(exception.getMessage());

        ErroPadronizado erroPadronizado = new ErroPadronizado(mensagens);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erroPadronizado);
    }
}