package com.example.IMC.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

        @ExceptionHandler(NoSuchElementException.class)
        public ResponseEntity<String> handleNotFound(NoSuchElementException e) {
            return new ResponseEntity<>("Recurso não encontrado: " + e.getMessage(), HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(Exception.class)
        public ResponseEntity<String> handleGeneric(Exception e) {
            return new ResponseEntity<>("Erro interno: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

