package com.orielly.shopping.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = false) // its more of validation for table column
    @NotBlank(message = "product must have a non-blank name")
    private String name;

    @Min(message = "price must be greater than or equal to Zero",value = 0)
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
