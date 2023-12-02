package com.shop.controller;

import com.shop.model.dto.ProductDto;
import com.shop.service.BasketProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BasketProductController.class)
class BasketProductControllerTest {

    @MockBean
    private BasketProductService service;

    @Autowired
    private MockMvc mockMvc;

    private final ProductDto productDto = new ProductDto("Potato", "red potato", 35.50);

    @Test
    @DisplayName("Положить товар в корзину")
    void putProducts() throws Exception {
        Mockito.doNothing().when(service).putProduct("testBasketId", "testProductId");

        mockMvc.perform(post("/api/v1/baskets/{basketId}/products/{productId}", "testBasketId", "testProductId"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Показать все товары из корзины")
    void getProducts() throws Exception {
        String expected = "[{\"title\":\"Potato\",\"description\":\"red potato\",\"price\":35.5}]";
        Mockito.when(service.allProducts("testBasketId")).thenReturn(List.of(productDto));

        MvcResult result = mockMvc.perform(get("/api/v1/baskets/{basketId}/products", "testBasketId"))
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), expected);
    }

    @Test
    @DisplayName("Удалить товар из корзины")
    void deleteProduct() throws Exception {
        Mockito.doNothing().when(service).deleteProduct("testBasketId", "testProductId");

        mockMvc.perform(delete("/api/v1/baskets/{basketId}/products/{productId}", "testBasketId", "testProductId"))
                .andExpect(status().isNoContent());
    }
}