package com.orielly.shopping.entities;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ProductTest {

    @Autowired
    private Validator validator;

    @Test
    void negativePriceOnProductIsInvalid(){
        Product product = new Product("name",-1.0);
        Set<ConstraintViolation<Product>> violations =validator.validate(product);
        System.out.println(violations);
        assertEquals(1,violations.size());

    }

    @Test
    void autoWiringWorked(){
        assertNotNull(validator);
        assertEquals(LocalValidatorFactoryBean.class, validator.getClass());
    }

    @Test
    void nameCannotBeBlank(){
        Product product = new Product("",10.0);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(1,violations.size());

        //Extract violations using set.iterator(); alternative, using Streams in price test
        ConstraintViolation<Product> violation = violations.iterator().next();
        assertEquals("product must have a non-blank name",violation.getMessage());
    }

    @Test
    void priceMustBeGEZero(){
        Product product = new Product("name", -1);
        Set<ConstraintViolation<Product>> violations = validator.validate(product);
        assertEquals(1,violations.size());

        Optional<ConstraintViolation<Product>> optionalViolation = violations.stream().findFirst();
        assertTrue(optionalViolation.isPresent());
        assertEquals("price must be greater than or equal to Zero", optionalViolation.get().getMessage());
    }

}