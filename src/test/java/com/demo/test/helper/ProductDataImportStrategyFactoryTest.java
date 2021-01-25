package com.demo.test.helper;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(Lifecycle.PER_CLASS)
class ProductDataImportStrategyFactoryTest {

    @InjectMocks
    private ProductDataImportStrategyFactory productDataImportStrategyFactory;

    @Mock
    private ProductDataBatchImportStrategy productDataBatchImportStrategy;
    @Mock
    private ProductDataPullImportStrategy productDataPullImportStrategy;
    @Mock
    private ProductDataPushImportStrategy productDataPushImportStrategy;

    @BeforeAll
    void init(){
        MockitoAnnotations.openMocks(this);
        }

    @Test
    void getStrategy() {

        List<String> productDataImportStrategy = Arrays.asList("batch", "batCH", "push", "PUsh", "PUll", "pUll");
        for (String strategy : productDataImportStrategy) {
            assertTrue(productDataImportStrategyFactory.getStrategy(strategy).isPresent());
        }
    }
}