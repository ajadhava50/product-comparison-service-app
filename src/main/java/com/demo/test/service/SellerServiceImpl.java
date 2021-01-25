package com.demo.test.service;

import com.demo.test.dto.ProductSellerDto;
import com.demo.test.entity.Seller;
import com.demo.test.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Seller service.
 */
@Service
public class SellerServiceImpl implements SellerService {

    private SellerRepository sellerRepository;

    /**
     * Instantiates a new Seller service.
     *
     * @param sellerRepository the seller repository
     */
    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller getSeller(ProductSellerDto productSellerDto) {
        Seller seller = sellerRepository.findBySellerName(productSellerDto.getSellerName());
        return seller != null ? seller : saveSeller(productSellerDto);
    }

    @Override
    public Seller saveSeller(ProductSellerDto productSellerDto) {
        return sellerRepository.save(convertToSellerEntity(productSellerDto));
    }

    private Seller convertToSellerEntity(ProductSellerDto productSellerDto) {
       Seller seller = new Seller();
       seller.setSellerName(productSellerDto.getSellerName());
       seller.setSellerType(productSellerDto.getSellerType());
       seller.setSellerUrl(productSellerDto.getSellerUrl());
       seller.setAddress(productSellerDto.getAddress());
        return seller;
    }
}