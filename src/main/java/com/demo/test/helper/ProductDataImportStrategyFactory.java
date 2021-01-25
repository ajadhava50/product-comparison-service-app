package com.demo.test.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The type Product data import strategy factory is used to decide ProductDataImportStrategy.
 */
@Component
public class ProductDataImportStrategyFactory {

    private Map <String, ProductDataImportStrategy> strategyFactory = new HashMap<String, ProductDataImportStrategy>();

    /**
     * Instantiates a new Product data import strategy factory.
     *
     * @param productDataBatchImportStrategy the product data batch import strategy
     * @param productDataPullImportStrategy  the product data pull import strategy
     * @param productDataPushImportStrategy  the product data push import strategy
     */
    @Autowired
    public ProductDataImportStrategyFactory(ProductDataBatchImportStrategy productDataBatchImportStrategy,
                                            ProductDataPullImportStrategy productDataPullImportStrategy,
                                            ProductDataPushImportStrategy productDataPushImportStrategy) {
        strategyFactory.put("PUSH", productDataPushImportStrategy );
        strategyFactory.put("PULL", productDataPullImportStrategy );
        strategyFactory.put("BATCH", productDataBatchImportStrategy );
    }

    /**
     * Gets strategy.
     *
     * @param importType the import type
     * @return the strategy
     */
    public Optional<ProductDataImportStrategy> getStrategy(String importType) {
        return Optional.ofNullable(strategyFactory.get(importType.toUpperCase()));
    }
}
