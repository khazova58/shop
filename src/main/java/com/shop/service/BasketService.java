package com.shop.service;

import com.shop.model.dto.BasketDto;

public interface BasketService {

    BasketDto newBasket();

    void deleteBasket(String id);
}