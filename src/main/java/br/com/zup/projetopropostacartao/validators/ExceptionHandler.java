package br.com.zup.projetopropostacartao.validators;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;
import javax.validation.ValidationException;

@RestControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalStateException.class)
    public String objectNotFoundHandler(IllegalStateException e) {
        return "Error 400: \n\nMessage: " + e.getMessage();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public String notValidHandler(MethodArgumentNotValidException e) {
        return "Error 400: \n\nMessage: Error on field " + e.getBindingResult().getFieldError().getField().toString() +
                "\n" + e.getBindingResult().getFieldError().getDefaultMessage();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MissingServletRequestParameterException.class)
    public String missingRequestParameterHandler(MissingServletRequestParameterException e) {
        return "Error 400: \n\nMessage: Parameter " + e.getParameterName().toString() + " must not be empty";
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(NoResultException.class)
    public String purchaseExceptionHandler(NoResultException e) {
        return "Error 400: \n\nMessage: " + e.getMessage().toString();
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
    public String duplicatedHandler(ValidationException e) {
        return "Error 400: \n\nMessage: " + e.getMessage().toString();
    }
}