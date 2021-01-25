package com.demo.test.entity;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * The Product Entity .
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Long productId;
    private String productName;
    private String category;
    private String description;
    private Double price;
    private Double rating;
    @ManyToOne
    private Seller seller;

}
