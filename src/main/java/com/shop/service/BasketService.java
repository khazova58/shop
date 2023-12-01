package com.shop.service;

import com.shop.exception.BusinessError;
import com.shop.exception.ServiceException;
import com.shop.service.contract.BasketContract;
import com.shop.mapper.ProjectMapper;
import com.shop.model.dto.BasketDto;
import com.shop.model.entity.Basket;
import com.shop.repository.BasketRepository;
import org.springframework.stereotype.Service;

/**
 * Сервис создания/удаления корзины для пользователя
 */
@Service
public class BasketService implements BasketContract {

    private final BasketRepository repository;
    private final ProjectMapper mapper;

    public BasketService(BasketRepository repository, ProjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Создание корзины
     * @return Dto созданного объекта с полями - id корзины, дата создания
     */
    @Override
    public BasketDto newBasket() {
        Basket newBasket = repository.save(new Basket());
        return mapper.mapToEntity(newBasket);
    }

    /**
     * Удаление корзины
     * @param id корзины
     */
    @Override
    public void deleteBasket(String id) {
        Basket found = repository.findById(id).orElseThrow(() -> new ServiceException(BusinessError.BASKET_NOT_FOUND, id));
        repository.delete(found);
    }
}