package com.shop.mapper;

import com.shop.model.dto.BasketDto;
import com.shop.model.dto.ProductDto;
import com.shop.model.entity.Basket;
import com.shop.model.entity.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public interface ProjectMapper {

    Product mapToEntity(ProductDto dto);

    ProductDto mapToDto(Product entity);

    Product updateProductByRequest(ProductDto newProduct, @MappingTarget Product foundProduct);

    BasketDto mapToEntity(Basket entity);
}