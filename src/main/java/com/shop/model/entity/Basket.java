package com.shop.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Корзина покупок
 */
@Entity
@Table(name = "basket", schema = "shopping")
@Data
public class Basket {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "basket_id")
    private String basketId;

    /**
     * Дата создания
     */
    @Column(name = "date_create")
    @CreationTimestamp
    private LocalDateTime dateCreation;
}