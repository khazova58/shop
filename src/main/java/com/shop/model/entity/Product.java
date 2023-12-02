package com.shop.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Товар
 */
@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Product {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "product_id")
    private String productId;

    /**
     * Название товара
     */
    @Column(name = "title", unique = true)
    private String title;

    /**
     * Описание товара
     */
    @Column(name = "description")
    private String description;

    /**
     * Цена за единицу
     */
    @Column(name = "price")
    private double price;

    @Override
    public String toString() {
        return "Название='" + title + '\'' +
                ", Описание='" + description + '\'' +
                ", Цена за ед.=" + price;
    }
}