package com.shop.service;

import com.shop.model.dto.ProductDto;

import java.util.List;

public interface ProductService {

    String newProduct(ProductDto dto);

    ProductDto getProduct(String id);

    List<ProductDto> allProducts();

    ProductDto updateProduct(String id, ProductDto dto);

    void deleteProduct(String id);
}