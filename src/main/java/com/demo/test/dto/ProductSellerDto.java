package com.demo.test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductSellerDto {

    private String productName;
    private String category;
    private String description;
    private Double price;
    private Double rating;
    private String sellerName;
    private String sellerUrl;
    private String sellerType;
    private String address;

}
