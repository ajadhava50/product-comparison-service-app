package com.demo.test.entity;

import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


/**
 * The Seller Entity.
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Include
    private Long sellerId;
    private String sellerName;
    private String sellerUrl;
    private String sellerType;
    private String address;
    @OneToMany(mappedBy ="seller")
    private List<Product> product;

}
