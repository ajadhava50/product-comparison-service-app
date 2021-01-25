package com.demo.test.service;

import com.demo.test.dto.ProductSellerDto;
import com.demo.test.entity.Seller;

/**
 * The interface Seller service.
 */
public interface SellerService {

    /**
     * Gets seller.
     *
     * @param productSellerDto the product seller dto
     * @return the seller
     */
    Seller getSeller(ProductSellerDto productSellerDto);

    /**
     * Save seller seller.
     *
     * @param productSellerDto the product seller dto
     * @return the seller
     */
    Seller saveSeller(ProductSellerDto productSellerDto);

}
