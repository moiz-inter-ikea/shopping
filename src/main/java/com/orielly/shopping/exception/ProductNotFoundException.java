package com.orielly.shopping.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer id) {
        super("No Id "+id+" exists");
    }
}
