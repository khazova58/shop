package com.shop.service;

import com.shop.exception.ServiceException;
import com.shop.mapper.ProjectMapper;
import com.shop.mapper.ProjectMapperImpl;
import com.shop.model.dto.ProductDto;
import com.shop.model.entity.Product;
import com.shop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    private ProductService sut;

    @Mock
    private ProductRepository repository;

    private final ProjectMapper mapper = new ProjectMapperImpl();

    private final Product product = new Product("testId", "Potato", "red potato", 37.50);
    private final ProductDto dto = new ProductDto("Potato", "red potato", 37.50);
    private final ProductDto updateDto = new ProductDto("Potato", "red potato", 40.00);

    @BeforeEach
    void setUp() {
        sut = new ProductService(repository, mapper);
    }

    @Test
    @DisplayName("Создание товара")
    void newProduct() {
        Mockito.when(repository.save(any())).thenReturn(product);

        String id = sut.newProduct(dto);

        assertFalse(id.isEmpty());
        Mockito.verify(repository).save(any());
    }

    @Test
    @DisplayName("Найти товар по id")
    void getProduct() {
        Mockito.when(repository.findById("testId")).thenReturn(Optional.of(product));

        ProductDto founded = sut.getProduct("testId");

        assertEquals(founded.getTitle(), product.getTitle());
    }

    @Test
    @DisplayName("Найти несуществующий товар")
    void getProduct_expectedException() {
        Mockito.when(repository.findById("testId")).thenReturn(Optional.empty());

        ServiceException exception = assertThrows(ServiceException.class, () -> sut.getProduct("testId"));

        assertEquals("00001", exception.getBusinessError().getErrorCode());
    }

    @Test
    @DisplayName("Список всех товаров")
    void allProducts() {
        List<Product> list = new ArrayList<>();
        list.add(product);
        Mockito.when(repository.findAll()).thenReturn(list);

        List<ProductDto> dtoList = sut.allProducts();

        assertEquals(list.size(), dtoList.size());
        assertEquals(list.get(0).getTitle(), dtoList.get(0).getTitle());
    }

    @Test
    @DisplayName("Обновление карточки товара")
    void updateProduct() {
        Mockito.when(repository.findById("testId")).thenReturn(Optional.of(product));

        ProductDto updated = sut.updateProduct("testId", updateDto);

        assertEquals(updated.getPrice(), 40.00);
    }

    @Test
    @DisplayName("Удалить карточку товара")
    void deleteProduct() {
        Mockito.when(repository.findById("testId")).thenReturn(Optional.of(product));
        Mockito.doNothing().when(repository).delete(product);

        sut.deleteProduct("testId");

        Mockito.verify(repository).delete(product);
    }
}