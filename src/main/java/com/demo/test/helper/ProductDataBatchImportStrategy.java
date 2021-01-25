package com.demo.test.helper;

import com.demo.test.dto.ProductSellerDto;
import com.demo.test.exception.StrategyNotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The  Product data batch import strategy.
 */
@Component
public class ProductDataBatchImportStrategy implements ProductDataImportStrategy {

    private BatchDataHelper batchDataHelper;

    /**
     * Instantiates a new Product data batch import strategy.
     *
     * @param batchDataHelper the batch data helper
     */
    @Autowired
    public ProductDataBatchImportStrategy(BatchDataHelper batchDataHelper) {
        this.batchDataHelper = batchDataHelper;
    }

    @Override
    public List<ProductSellerDto> applyDataImportStrategy() throws StrategyNotImplementedException {
       return batchDataHelper.getBatchData();
    }
}
