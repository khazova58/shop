-- liquibase formatted sql

-- changeset yakhazova:create-table-product
    CREATE TABLE IF NOT EXISTS shopping.product
    (
        product_id  CHARACTER VARYING(36) NOT NULL PRIMARY KEY,
        title       CHARACTER VARYING(255) UNIQUE,
        description CHARACTER VARYING(255),
        price       FLOAT(53)
    );