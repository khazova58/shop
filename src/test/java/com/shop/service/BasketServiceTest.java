package com.shop.service;

import com.shop.exception.ServiceException;
import com.shop.mapper.ProjectMapper;
import com.shop.mapper.ProjectMapperImpl;
import com.shop.model.dto.BasketDto;
import com.shop.model.entity.Basket;
import com.shop.model.entity.Product;
import com.shop.repository.BasketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    private BasketService sut;

    private final Basket basket = new Basket("testId", LocalDateTime.now(),
            List.of(new Product("testId", "Potato", "red potato", 37.50)));

    @Mock
    private BasketRepository repository;

    private final ProjectMapper mapper = new ProjectMapperImpl();

    @BeforeEach
    void setUp() {
        sut = new BasketService(repository, mapper);
    }

    @Test
    @DisplayName("Создание корзины")
    void newBasket() {
        Mockito.when(repository.save(any(Basket.class))).thenReturn(basket);

        BasketDto basketDto = sut.newBasket();

        assertEquals(basketDto.getDateCreation(), basket.getDateCreation());
    }

    @Test
    @DisplayName("Удаление корзины")
    void deleteBasket() {
        Mockito.when(repository.findById("testId")).thenReturn(Optional.of(basket));

        sut.deleteBasket("testId");

        Mockito.verify(repository).findById("testId");
        Mockito.verify(repository).delete(basket);
    }

    @Test
    @DisplayName("Удаление несуществующей корзины")
    void deleteBasket_expectedException() {
        Mockito.when(repository.findById("testId")).thenReturn(Optional.empty());

        ServiceException exception = assertThrows(ServiceException.class, () -> sut.deleteBasket("testId"));

        assertEquals("00000", exception.getBusinessError().getErrorCode());
    }
}