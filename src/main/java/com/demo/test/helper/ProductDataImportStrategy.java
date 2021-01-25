package com.demo.test.helper;

import com.demo.test.dto.ProductSellerDto;
import com.demo.test.exception.StrategyNotImplementedException;

import java.util.List;

/**
 * The interface Product data import strategy.
 */
public interface ProductDataImportStrategy {

    /**
     * Apply list.
     *
     * @return the list
     * @throws StrategyNotImplementedException the strategy not implemented exception
     */
    public List<ProductSellerDto> applyDataImportStrategy() throws StrategyNotImplementedException;
}
