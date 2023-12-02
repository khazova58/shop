package com.shop.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Корзина покупок
 */
@Entity
@Table(name = "basket")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    /**
     * Продукты в составе корзины
     */
    @ManyToMany
    @JoinTable(name = "basket_product",
            joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();
}