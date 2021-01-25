package com.demo.test.controller;


import com.demo.test.dto.ProductSellerDto;
import com.demo.test.exception.StrategyNotImplementedException;
import com.demo.test.service.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;


/**
 * The Product controller .
 */
@RestController
public class ProductController {

    private ProductService productService;

    /**
     * Instantiates a new Product controller.
     *
     * @param ProductService the product service
     */
    @Autowired
    public ProductController(ProductService ProductService) {
        this.productService = ProductService;
    }

    /**
     * Save products response entity.
     *
     * @param dataImportStrategy the data import strategy
     * @return the response entity
     * @throws StrategyNotImplementedException the strategy not implemented exception
     */
    @ApiOperation(value = "import new products with push,pull,batch strategy", response = String.class)
    @PostMapping(value = "/products/{data-import-strategy}")
    public ResponseEntity<String> saveProducts(@PathVariable("data-import-strategy") String dataImportStrategy) throws StrategyNotImplementedException {
            productService.saveProducts(dataImportStrategy);
            return ResponseEntity.created(URI.create("/products/")).build();
    }

    /**
     * Gets products by category and name.
     *
     * @param category    the category
     * @param productName the product name
     * @return the products by category and name
     */
    @ApiOperation(value = "search products and compare the data ",response = String.class)
    @GetMapping(value = "/products/{category}/{productName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductSellerDto>> getProductsByCategoryAndName(@PathVariable String category, @PathVariable String productName) {
        return ResponseEntity.ok(productService.getProductsByCategoryAndName(category,productName));
    }

    /**
     * Gets products.
     *
     * @return the products
     */
    @ApiOperation(value = "api not implemented",response = String.class)
    @GetMapping(value = "/products")
    public ResponseEntity.BodyBuilder getProducts() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED);
    }


}


