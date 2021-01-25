package com.demo.test.service;

import com.demo.test.dto.ProductSellerDto;
import com.demo.test.exception.StrategyNotImplementedException;

import java.util.List;

/**
 * The interface Product service.
 */
public interface ProductService {

    /**
     * Gets products by category and name.
     *
     * @param category    the category
     * @param productName the product name
     * @return the products by category and name
     */
    List<ProductSellerDto> getProductsByCategoryAndName(String category, String productName);

    /**
     * Save products.
     *
     * @param dataImportStrategy the data import strategy
     * @throws StrategyNotImplementedException the strategy not implemented exception
     */
    void saveProducts(String dataImportStrategy) throws StrategyNotImplementedException;
}
