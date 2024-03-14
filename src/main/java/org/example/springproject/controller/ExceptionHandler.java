package org.example.springproject.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.example.springproject.exception.EntityAlreadyExistsException;
import org.example.springproject.exception.NoSuchEntityException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.util.stream.Collectors.joining;
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NoSuchEntityException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public String resourceNotFoundHandler(RuntimeException exception) {
        return exception.getMessage();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ConstraintViolationException.class, EntityAlreadyExistsException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String badRequestHandler(RuntimeException exception) {
        if (exception instanceof ConstraintViolationException ex)
            return ex.getConstraintViolations()
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(joining("\n"));
        else return exception.getMessage();
    }
}