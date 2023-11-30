package com.shop.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Таблица взаимосвязи Покупок со Списком
 */
@Entity
@Table(name = "product_basket", schema = "shopping")
@Data
@IdClass(ProductBasket.ProductBasketPK.class)
public class ProductBasket {
    /**
     * Идентификатор продукта
     */
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product productId;

    /**
     * Идентификатор списка
     */
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "basket_id")
    private Basket basketId;

    @EqualsAndHashCode
    public static class ProductBasketPK implements Serializable {
        private String productId;
        private String basketId;
    }
}