CREATE TABLE product_tags
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    product_id BIGINT                              NOT NULL,
    text       VARCHAR(255)                        NOT NULL,

    CONSTRAINT tags_pk
        PRIMARY KEY (id),

    CONSTRAINT product_tags_products_id_fk
        FOREIGN KEY (product_id) REFERENCES products (id)
            ON DELETE CASCADE
);
