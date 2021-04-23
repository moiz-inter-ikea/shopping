package com.orielly.shopping.controllers;

import com.orielly.shopping.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ProductControllerAdvice {

    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String productNotFoundHandler(ProductNotFoundException e){
        return e.getMessage();
    }

    //using response entity - but this doe not get response body
/*    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> productNotFoundHandler(ProductNotFoundException e){
        return ResponseEntity.notFound().build();
    }*/

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String constraintViolations(ConstraintViolationException e){
        return e.getConstraintViolations().toString();
    }
}
