package com.shop.controller;

import com.shop.model.dto.ProductDto;
import com.shop.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @MockBean
    private ProductService service;

    @Autowired
    private MockMvc mockMvc;

    private final ProductDto dto = new ProductDto("Potato", "red potato", 35.50);
    String bodyString = "[{\"title\":\"Potato\",\"description\":\"red potato\",\"price\":35.5}]";

    @Test
    @DisplayName("Создание товара")
    void newProduct() throws Exception {
        Mockito.when(service.newProduct(dto)).thenReturn("testId");

        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content("""
                                {
                                	"title": "Potato",
                                	"description": "red potato",
                                	"price": 35.50
                                }
                                """))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Найти товар по id")
    void getProduct() throws Exception {
        Mockito.when(service.getProduct("testId")).thenReturn(dto);

        mockMvc.perform(get("/api/v1/products")
                        .param("id", "testId"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Potato"));
    }

    @Test
    @DisplayName("Список всех товаров")
    void allProducts() throws Exception {
        ArrayList<ProductDto> list = new ArrayList<>();
        list.add(dto);
        Mockito.when(service.allProducts()).thenReturn(list);

        MvcResult actual = mockMvc.perform(
                        get("/api/v1/products/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals(bodyString, actual.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("Обновить карточку товара")
    void updateProduct() throws Exception {
        Mockito.when(service.updateProduct("testId", dto)).thenReturn(dto);

        mockMvc.perform(
                        put("/api/v1/products")
                                .param("id", "testId")
                                .content("""
                                        {
                                        	"title": "Potato",
                                        	"description": "red potato",
                                        	"price": 35.50
                                        }
                                        """)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35.50));
    }

    @Test
    @DisplayName("Удаление карточки товара")
    void deleteProduct() throws Exception {
        Mockito.doNothing().when(service).deleteProduct("testId");
        mockMvc.perform(
                        delete("/api/v1/products")
                                .param("id", "testId"))
                .andExpect(status().isNoContent());
    }
}