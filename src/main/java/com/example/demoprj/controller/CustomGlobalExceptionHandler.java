package com.example.demoprj.controller;

import exception.ApiError;
import exception.SongNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SongNotFoundException.class)
    public ResponseEntity<Object> handleExceptions( SongNotFoundException ex, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), details);
        ResponseEntity<Object> entity = new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleExceptions( Exception ex, WebRequest webRequest) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), details);
        ResponseEntity<Object> entity = new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

}
