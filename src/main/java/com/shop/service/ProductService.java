package com.shop.service;

import com.shop.exception.BusinessError;
import com.shop.exception.ServiceException;
import com.shop.mapper.ProjectMapper;
import com.shop.model.dto.ProductDto;
import com.shop.model.entity.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.contract.ProductContract;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
public class ProductService implements ProductContract {

    private final ProductRepository repository;
    private final ProjectMapper mapper;

    public ProductService(ProductRepository repository, ProjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public String newProduct(ProductDto dto) {
        log.debug("Creating a new product: {}", dto);
        Product entity = mapper.mapToEntity(dto);
        Product savedProduct = repository.save(entity);
        log.debug("New product created with ID: {}", savedProduct.getProductId());
        return savedProduct.getProductId();
    }

    @Override
    public ProductDto getProduct(String id) {
        log.debug("Fetching product with ID: {}", id);
        Product found = repository.findById(id)
                .orElseThrow(() -> new ServiceException(BusinessError.PRODUCT_NOT_FOUND, id));
        ProductDto productDto = mapper.mapToDto(found);
        log.debug("Found product: {}", productDto);
        return productDto;
    }

    @Override
    public List<ProductDto> allProducts() {
        log.debug("Fetching all products");
        List<Product> products = repository.findAll();
        List<ProductDto> dtoList = products.stream()
                .map(mapper::mapToDto)
                .toList();
        log.debug("Fetched {} products", dtoList.size());
        return dtoList;
    }

    @Override
    @Transactional
    public ProductDto updateProduct(String id, ProductDto newProduct) {
        log.debug("Updating product with ID {}: {}", id, newProduct);
        Product foundProduct = repository.findById(id).orElseThrow(() -> new ServiceException(BusinessError.PRODUCT_NOT_FOUND, id));
        Product updatedEntity = mapper.updateProductByRequest(newProduct, foundProduct);
        ProductDto updatedProductDto = mapper.mapToDto(updatedEntity);
        log.debug("Product updated: {}", updatedProductDto);
        return updatedProductDto;
    }

    @Override
    @Transactional
    public void deleteProduct(String id) {
        log.debug("Deleting product with ID: {}", id);
        Product found = repository.findById(id).orElseThrow(() -> new ServiceException(BusinessError.PRODUCT_NOT_FOUND, id));
        repository.delete(found);
        log.debug("Product with ID {} deleted", id);
    }
}