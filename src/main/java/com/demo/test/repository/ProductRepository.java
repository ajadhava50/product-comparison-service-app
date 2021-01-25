package com.demo.test.repository;

import com.demo.test.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Product repository.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

   /**
    * Find by category and product name order by rating list.
    *
    * @param ProductName the product name
    * @param category    the category
    * @return the list
    */
   List<Product> findByCategoryAndProductNameOrderByRating(String ProductName, String category);

}
