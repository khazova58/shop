package com.shop.service;

import com.shop.mapper.ProjectMapper;
import com.shop.model.dto.BasketDto;
import com.shop.model.entity.Basket;
import com.shop.repository.BasketRepository;
import org.springframework.stereotype.Service;

@Service
public class BasketServiceImpl implements BasketService {

    private final BasketRepository repository;
    private final ProjectMapper mapper;

    public BasketServiceImpl(BasketRepository repository, ProjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BasketDto newBasket() {
        Basket newBasket = repository.save(new Basket());
        return mapper.mapToEntity(newBasket);
    }

    @Override
    public void deleteBasket(String id) {
        Basket found = repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(found);
    }
}