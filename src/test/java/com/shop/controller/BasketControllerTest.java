package com.shop.controller;

import com.shop.model.dto.BasketDto;
import com.shop.service.BasketService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BasketController.class)
class BasketControllerTest {

    @MockBean
    private BasketService service;

    @Autowired
    private MockMvc mockMvc;

    private final BasketDto basketDto = new BasketDto("basketId", LocalDateTime.now());

    @Test
    @DisplayName("Создание корзины")
    void newBasket() throws Exception {
        Mockito.when(service.newBasket()).thenReturn(basketDto);

        mockMvc.perform(post("/api/v1/baskets"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.basketId").value(basketDto.getBasketId()));
    }

    @Test
    @DisplayName("Удаление корзины")
    void deleteBasket() throws Exception {
        Mockito.doNothing().when(service).deleteBasket("testId");

        mockMvc.perform(delete("/api/v1/baskets")
                        .param("id", "testId"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}