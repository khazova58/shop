package com.shop.service;

import com.shop.exception.ServiceException;
import com.shop.mapper.ProjectMapper;
import com.shop.mapper.ProjectMapperImpl;
import com.shop.model.dto.ProductDto;
import com.shop.model.entity.Basket;
import com.shop.model.entity.Product;
import com.shop.repository.BasketRepository;
import com.shop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class BasketProductServiceTest {

    private BasketProductService sut;

    @Mock
    private BasketRepository basketRepository;

    @Mock
    private ProductRepository productRepository;

    private final ProjectMapper mapper = new ProjectMapperImpl();

    private final Basket basket = new Basket("testId", LocalDateTime.now(),
            new ArrayList<>(List.of(new Product("testId", "Potato", "red potato", 37.50))));

    private final Product product = new Product("testId", "Potato", "red potato", 37.50);


    @BeforeEach
    void setUp() {
        sut = new BasketProductService(productRepository, basketRepository, mapper);
    }


    @Test
    @DisplayName("Положить товар в корзину")
    void putProduct() {
        Mockito.when(basketRepository.findById("testId")).thenReturn(Optional.of(basket));
        Mockito.when(productRepository.findById("testId")).thenReturn(Optional.of(product));
        Mockito.when(basketRepository.save(basket)).thenReturn(basket);
        sut.putProduct("testId", "testId");
        Mockito.verify(basketRepository).save(basket);
    }

    @Test
    @DisplayName("Получить все товары из корзины")
    void allProducts() {
        Mockito.when(basketRepository.findById("testId")).thenReturn(Optional.of(basket));

        List<ProductDto> products = sut.allProducts("testId");

        assertEquals(products.size(), 1);
        assertEquals(products.get(0).getTitle(), "Potato");
    }

    @Test
    @DisplayName("Удалить товар из корзины")
    void deleteProductPositive() {
        Mockito.when(basketRepository.findById("testId")).thenReturn(Optional.of(basket));
        Mockito.when(productRepository.findById("testId")).thenReturn(Optional.of(product));

        sut.deleteProduct("testId","testId");

        Mockito.verify(basketRepository).save(basket);
    }

    @Test
    @DisplayName("Удалить несуществующий товар из корзины")
    void deleteProduct_expectedException() {
        Mockito.when(basketRepository.findById("testId")).thenReturn(Optional.of(basket));
        Mockito.when(productRepository.findById("testId")).thenReturn(Optional.empty());

        ServiceException exception = assertThrows(ServiceException.class, () -> sut.deleteProduct("testId", "testId"));

        assertEquals("00001", exception.getBusinessError().getErrorCode());
    }

    @Test
    @DisplayName("Удалить товар из несуществующей корзины")
    void deleteProduct_expectedBasketNotFoundException() {
        Mockito.when(basketRepository.findById("testId")).thenReturn(Optional.empty());

        ServiceException exception = assertThrows(ServiceException.class, () -> sut.deleteProduct("testId", "testId"));

        assertEquals("00000", exception.getBusinessError().getErrorCode());
    }
}