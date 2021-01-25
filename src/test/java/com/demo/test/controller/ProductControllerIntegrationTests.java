package com.demo.test.controller;

import com.demo.test.ProductComparisonServiceAppApplication;
import com.demo.test.dto.ProductSellerDto;
import com.demo.test.repository.ProductRepository;
import com.demo.test.utilis.ApplicationConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;


import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ProductComparisonServiceAppApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
 class ProductControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    String rootUrl = "http://localhost:";

    @Autowired
    private ProductRepository productRepository;

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Test
     void testPushImportStrategyException() {
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity(rootUrl + port + "products/push",null,String.class);
        assertEquals(ApplicationConstants.PUSH_IMPORT_STRATEGY_ERROR , responseEntity.getBody());
    }

    @Test
     void testPullImportStrategyException() {
       ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity(rootUrl + port  + "products/pull",null,String.class);
        assertEquals(ApplicationConstants.PULL_IMPORT_STRATEGY_ERROR , responseEntity.getBody());
    }

    @Test
     void testInvalidStrategyException() {
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity(rootUrl + port  + "products/psaasds",null,String.class);
        assertEquals(ApplicationConstants.INVALID_STRATEGY , responseEntity.getBody());
    }

    @Test
     void testBatchImport() {
        final int beforeCount = countRowsInTable("product");
        assertEquals(0,beforeCount);
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity(rootUrl + port  + "products/batch",null,String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
        final int afterCount = countRowsInTable("product");
        assertEquals(4 ,afterCount);
    }

    @Test
     void testGetProducts() {
        ResponseEntity<ProductSellerDto[]> responseEntity = this.restTemplate.getForEntity(rootUrl + port  + "products/fashion/shirt", ProductSellerDto[].class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(3,responseEntity.getBody().length);

    }
    protected int countRowsInTable(String tableName) {
        return JdbcTestUtils.countRowsInTable(this.jdbcTemplate, tableName);
    }

}