package com.demo.test.service;

import com.demo.test.dto.ProductSellerDto;
import com.demo.test.entity.Product;
import com.demo.test.entity.Seller;
import com.demo.test.exception.StrategyNotImplementedException;
import com.demo.test.helper.ProductDataImportStrategy;
import com.demo.test.helper.ProductDataImportStrategyFactory;
import com.demo.test.repository.ProductRepository;
import com.demo.test.utilis.ApplicationConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The Product service for handling business rules/logic for product comparision service   .
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {


    private ProductRepository productRepository;
    private SellerService sellerService;
    private ProductDataImportStrategyFactory strategyFactory;

    /**
     * Instantiates a new Product service.
     *
     * @param productRepository the product repository
     * @param sellerService     the seller service
     * @param strategyFactory   the strategy factory
     */
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, SellerService sellerService, ProductDataImportStrategyFactory strategyFactory) {
        this.productRepository = productRepository;
        this.sellerService = sellerService;
        this.strategyFactory = strategyFactory;
    }

    @Override
    public List<ProductSellerDto> getProductsByCategoryAndName(String category,String productName) {

        return  (List<ProductSellerDto>) productRepository.findByCategoryAndProductNameOrderByRating(category,productName)
                        .stream().map(this::convertToProductDto)
                        .collect(Collectors.toList());
    }

    @Override
    public void saveProducts(String dataImportStrategy) throws StrategyNotImplementedException {
        List<Product> products = getProductsData(dataImportStrategy);
        int productsCreatedCount = productRepository.saveAll(products).size() ;
        log.debug("Product(s) Created"+productsCreatedCount);
    }

    private List<Product> getProductsData(String dataImportStrategy) throws StrategyNotImplementedException {
        Optional<ProductDataImportStrategy> strategy = strategyFactory.getStrategy(dataImportStrategy);
        List<ProductSellerDto> productsDto = new ArrayList<>();
        if (strategy.isPresent()) {
            productsDto.addAll(strategy.get().applyDataImportStrategy());
            return productsDto.stream()
                    .map(productDto -> convertToProductEntity(productDto))
                    .collect(Collectors.toList());
        }
        throw new StrategyNotImplementedException(ApplicationConstants.INVALID_STRATEGY);
    }

    private ProductSellerDto convertToProductDto(Product product) {
        ProductSellerDto productSellerDto = new ProductSellerDto();
        productSellerDto.setDescription(product.getDescription());
        productSellerDto.setPrice(product.getPrice());
        productSellerDto.setRating(product.getRating());
        productSellerDto.setSellerName(product.getSeller().getSellerName());
        productSellerDto.setSellerUrl(product.getSeller().getSellerUrl());
        productSellerDto.setSellerType(product.getSeller().getSellerType());
        productSellerDto.setAddress(product.getSeller().getAddress());
        return productSellerDto;
    }

    private Product convertToProductEntity(ProductSellerDto productSellerDto) {

        Seller seller = sellerService.getSeller(productSellerDto);
        Product product = new Product();
        product.setProductName(productSellerDto.getProductName());
        product.setCategory(productSellerDto.getCategory());
        product.setDescription(productSellerDto.getDescription());
        product.setPrice(productSellerDto.getPrice());
        product.setRating(productSellerDto.getRating());
        product.setSeller(seller);
        return product;
    }


}