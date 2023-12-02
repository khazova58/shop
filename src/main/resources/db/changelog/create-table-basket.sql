-- liquibase formatted sql

-- changeset yakhazova:create-table-basket
CREATE TABLE IF NOT EXISTS shopping.basket
(
    basket_id   CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
    date_create TIMESTAMP(6)
);