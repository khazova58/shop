package com.shop.service.contract;

import com.shop.model.dto.ProductDto;
import com.shop.model.dto.ProductIdDto;

import java.util.List;

public interface ProductContract {

    ProductIdDto newProduct(ProductDto dto);

    ProductDto getProduct(String id);

    List<ProductDto> allProducts();

    ProductDto updateProduct(String id, ProductDto dto);

    void deleteProduct(String id);
}