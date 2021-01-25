package com.demo.test.helper;

import com.demo.test.dto.ProductSellerDto;
import com.demo.test.exception.StrategyNotImplementedException;
import com.demo.test.utilis.ApplicationConstants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The  Batch data helper class is responsible for reading batch files
 * and sending list of product info as dto .
 */
@Component
@Slf4j
public class BatchDataHelper {

    @Value("${productData.folder}")
    private String productDatafolder;

    /**
     * Gets batch data.
     *
     * @return the batch data
     * @throws StrategyNotImplementedException the strategy not implemented exception
     */
    public List<ProductSellerDto> getBatchData() throws StrategyNotImplementedException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductSellerDto> productsList = new ArrayList<>();
        for (File productsFile : getFilesInFolder(productDatafolder)) {
            try {
                log.debug("processing file:" + productsFile);
                List<ProductSellerDto> productsDto = objectMapper.readValue(productsFile, new TypeReference<List<ProductSellerDto>>() {});
                productsList.addAll(productsDto);
            } catch (IOException e) {
                throw new StrategyNotImplementedException(ApplicationConstants.BATCH_IMPORT_STRATEGY_ERROR);
            }
        }
        return productsList;
    }

    private File[] getFilesInFolder(String path) {
        File productDataDirectory = new File(path);
        if (productDataDirectory.isDirectory()) {
            log.debug("Reading product data import file :" + path);
            return productDataDirectory.listFiles();
        }
        throw new StrategyNotImplementedException("empty folder or incorrect path, please check the folder path in config/application.properties");
    }
}
