CREATE TABLE product_variants
(
    id                 BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    product_id         BIGINT                              NOT NULL,
    shopify_id         BIGINT                              NOT NULL,
    title              VARCHAR(255)                        NULL,
    sku                VARCHAR(64)                         NOT NULL,
    inventory_quantity INT                                 NOT NULL,
    weight             INT                                 NOT NULL,
    price              DOUBLE PRECISION                    NOT NULL,
    compare_at_price   DOUBLE PRECISION                    NULL,
    created_date_time  TIMESTAMPTZ                         NOT NULL,
    updated_date_time  TIMESTAMPTZ                         NOT NULL,

    CONSTRAINT product_variants_pk
        PRIMARY KEY (id),

    CONSTRAINT product_variants_products_id_fk
        FOREIGN KEY (product_id) REFERENCES products (id)
            ON DELETE CASCADE
);
