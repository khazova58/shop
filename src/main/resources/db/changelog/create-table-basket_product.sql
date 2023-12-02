-- liquibase formatted sql

-- changeset yakhazova:create-table-basket_product
CREATE TABLE IF NOT EXISTS shopping.basket_product
(
    basket_id  CHARACTER VARYING(36) NOT NULL,
    product_id CHARACTER VARYING(36) NOT NULL,
    CONSTRAINT pk_basket_product PRIMARY KEY (basket_id, product_id),
    CONSTRAINT fk_basket_product_basket FOREIGN KEY (basket_id) REFERENCES shopping.basket (basket_id) ON DELETE CASCADE,
    CONSTRAINT fk_basket_product_product FOREIGN KEY (product_id) REFERENCES shopping.product (product_id) ON DELETE CASCADE
);
