package com.demo.test.helper;

import com.demo.test.dto.ProductSellerDto;
import com.demo.test.exception.StrategyNotImplementedException;
import com.demo.test.utilis.ApplicationConstants;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The type Product data pull import strategy.
 */
@Component
public class ProductDataPullImportStrategy implements ProductDataImportStrategy {

    @Override
    public List<ProductSellerDto> applyDataImportStrategy() throws StrategyNotImplementedException {
        throw new StrategyNotImplementedException(ApplicationConstants.PULL_IMPORT_STRATEGY_ERROR);
    }
}
