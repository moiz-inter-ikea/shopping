package com.orielly.shopping.services;

import com.orielly.shopping.doa.ProductRepository;
import com.orielly.shopping.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void initializeDatabase(){
        if (repository.count() == 0) {
            repository.saveAll(Arrays.asList(
                    new Product("baseball", 5.0),
                    new Product("football", 6.0),
                    new Product("basketball", 7.0)
            ));
        }
    }

    public List<Product> findAll(){
        return repository.findAll();
    }

    public Optional<Product> findById(Integer id)
    {
        return repository.findById(id);
    }

}
