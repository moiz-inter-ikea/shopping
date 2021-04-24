package com.orielly.shopping.controllers;

import com.orielly.shopping.entities.Product;
import com.orielly.shopping.exception.ProductNotFoundException;
import com.orielly.shopping.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping("/all")
    public List<Product> getAllProducts(){
        return service.findAll();
    }

    @GetMapping()
    public Product findProductById(@RequestParam Integer id){
        return service.findById(id).orElseThrow(()->new ProductNotFoundException(id));
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Product p = service.saveProduct(product);

        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(p.getId())
                .toUri();

        return ResponseEntity.created(location).body(p);

        //Way to just return 201 & Created
        //public ResponseEntity<Product> addProduct(@RequestBody Product product){
        //return new ResponseEntity<> ("CREATED",HttpStatus.CREATED); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id){
        Optional<Product> existingProduct = service.findById(id);
        if (existingProduct.isPresent()){
            service.deleteProduct(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllProducts(){
        //service.deleteAll();
        service.deleteAllInBatch();
        return ResponseEntity.noContent().build();
    }

}
