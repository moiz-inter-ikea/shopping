package com.orielly.shopping.config;

import com.orielly.shopping.doa.ProductRepository;
import com.orielly.shopping.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AppInit implements CommandLineRunner {

    private final ProductRepository repository;

    @Autowired
    public AppInit(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (repository.count() == 0) {
            repository.saveAll(Arrays.asList(
                    new Product("baseball", 5.0),
                    new Product("football", 6.0),
                    new Product("basketball", 7.0)
            ));
        }
    }
}
