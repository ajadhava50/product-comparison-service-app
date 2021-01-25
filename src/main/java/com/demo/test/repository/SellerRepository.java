package com.demo.test.repository;

import com.demo.test.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Seller repository.
 */
@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    /**
     * Find by seller name seller.
     *
     * @param sellerName the seller name
     * @return the seller
     */
    Seller findBySellerName(String sellerName);
}
