package com.shop.service;

import com.shop.exception.BusinessError;
import com.shop.exception.ServiceException;
import com.shop.mapper.ProjectMapper;
import com.shop.model.dto.ProductDto;
import com.shop.model.entity.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.contract.ProductContract;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Product entity = mapper.mapToEntity(dto);
        Product saveProduct = repository.save(entity);
        return saveProduct.getProductId();
    }

    @Override
    public ProductDto getProduct(String id) {
        Product found = repository.findById(id).orElseThrow(() -> new ServiceException(BusinessError.PRODUCT_NOT_FOUND, id));
        return mapper.mapToDto(found);
    }

    @Override
    public List<ProductDto> allProducts() {
        List<Product> products = repository.findAll();
        return products.stream()
                .map(mapper::mapToDto)
                .toList();
    }

    @Override
    @Transactional
    public ProductDto updateProduct(String id, ProductDto newProduct) {
        Product foundProduct = repository.findById(id).orElseThrow(() -> new ServiceException(BusinessError.PRODUCT_NOT_FOUND, id));
        Product updateEntity = mapper.updateProductByRequest(newProduct, foundProduct);
        return mapper.mapToDto(updateEntity);
    }

    @Override
    @Transactional
    public void deleteProduct(String id) {
        Product found = repository.findById(id).orElseThrow(() -> new ServiceException(BusinessError.PRODUCT_NOT_FOUND, id));
        repository.delete(found);
    }
}