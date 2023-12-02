package com.shop.service.contract;

import com.shop.model.dto.BasketDto;

public interface BasketContract {

    BasketDto newBasket();

    void deleteBasket(String id);
}