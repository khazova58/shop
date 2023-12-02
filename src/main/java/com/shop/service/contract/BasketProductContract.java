package com.shop.service.contract;

import com.shop.model.dto.ProductDto;

import java.util.List;

public interface BasketProductContract {

    void putProduct(String basketId, String productId);

    List<ProductDto> allProducts(String id);

    void deleteProduct(String basketId, String productId);
}