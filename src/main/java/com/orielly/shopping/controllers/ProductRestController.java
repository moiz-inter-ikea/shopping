package com.orielly.shopping.controllers;

import com.orielly.shopping.entities.Product;
import com.orielly.shopping.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    private final ProductService service;

    @Autowired
    public ProductRestController(ProductService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return service.findAll();
    }

    @GetMapping("{id}")
    public Optional<Product> findProductById(Integer id){
        return service.findById(id);
    }
}
