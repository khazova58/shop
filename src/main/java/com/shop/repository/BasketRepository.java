package com.shop.repository;

import com.shop.model.entity.Basket;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepository extends JpaRepository<Basket, String> {

    @EntityGraph(attributePaths = "products")
    Optional<Basket> findByBasketId(String id);
}