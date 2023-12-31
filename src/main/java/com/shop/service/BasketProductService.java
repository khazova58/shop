package com.shop.service;

import com.shop.exception.BusinessError;
import com.shop.exception.ServiceException;
import com.shop.service.contract.BasketProductContract;
import com.shop.mapper.ProjectMapper;
import com.shop.model.dto.ProductDto;
import com.shop.model.entity.Basket;
import com.shop.model.entity.Product;
import com.shop.repository.BasketRepository;
import com.shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для работы с товарами в корзине
 */
@Service
@RequiredArgsConstructor
public class BasketProductService implements BasketProductContract {
    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final ProjectMapper mapper;

    /**
     * Положить товар в корзину
     *
     * @param basketId - id корзины
     * @param productId - id товара
     */
    @Override
    @Transactional
    public void putProduct(String basketId, String productId) {
        Basket basket = basketRepository.findByBasketId(basketId).orElseThrow(() -> new ServiceException(BusinessError.BASKET_NOT_FOUND, basketId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ServiceException(BusinessError.PRODUCT_NOT_FOUND, productId));
        List<Product> list = basket.getProducts();
        list.add(product);
        basketRepository.save(basket);
    }

    /**
     * Получить все товары из корзины
     *
     * @param id корзины
     * @return список товаров
     */
    @Override
    public List<ProductDto> allProducts(String id) {
        Basket foundedBasket = basketRepository.findById(id).orElseThrow(() -> new ServiceException(BusinessError.BASKET_NOT_FOUND, id));
        List<Product> products = foundedBasket.getProducts();
        return products.stream()
                .map(mapper::mapToDto)
                .toList();
    }

    /**
     * Удаление товара из корзины
     *
     * @param basketId - id корзины
     * @param productId - id товара
     */
    @Override
    @Transactional
    public void deleteProduct(String basketId, String productId) {
        Basket foundedBasket = basketRepository.findById(basketId).orElseThrow(() -> new ServiceException(BusinessError.BASKET_NOT_FOUND, basketId));
        Product product = productRepository.findById(productId).orElseThrow(() -> new ServiceException(BusinessError.PRODUCT_NOT_FOUND, productId));
        List<Product> products = foundedBasket.getProducts();
        products.remove(product);
        basketRepository.save(foundedBasket);
    }
}