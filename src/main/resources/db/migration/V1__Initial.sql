CREATE SEQUENCE hibernate_sequence START 1;

CREATE TABLE orders
(
    id                   BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    number               INT                                 NOT NULL,
    name                 VARCHAR(23)                         NOT NULL,
    cancel_reason        VARCHAR(32)                         NOT NULL,
    financial_status     VARCHAR(32)                         NOT NULL,
    fulfillment_status   VARCHAR(32)                         NOT NULL,
    subtotal_price       DOUBLE PRECISION                    NOT NULL,
    total_discount       DOUBLE PRECISION                    NOT NULL,
    total_tax            DOUBLE PRECISION                    NOT NULL,
    total_shipping_price DOUBLE PRECISION                    NOT NULL,
    total_price          DOUBLE PRECISION                    NOT NULL,
    created_date_time    TIMESTAMPTZ                         NOT NULL,
    valid_from           TIMESTAMPTZ                         NOT NULL,
    valid_to             TIMESTAMPTZ                         NULL,

    CONSTRAINT orders_pk
        PRIMARY KEY (id),

    CONSTRAINT orders_number_valid_key
        UNIQUE (number, valid_from, valid_to)
);

CREATE TABLE order_lines
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    order_id      BIGINT                              NOT NULL,
    sku           VARCHAR(64)                         NOT NULL,
    title         VARCHAR(255)                        NOT NULL,
    variant_title VARCHAR(255)                        NULL,
    quantity      INT                                 NOT NULL,
    price         DOUBLE PRECISION                    NOT NULL,

    CONSTRAINT order_lines_pk
        PRIMARY KEY (id),

    CONSTRAINT order_lines_orders_id_fk
        FOREIGN KEY (order_id) REFERENCES orders (id)
            ON DELETE CASCADE
);
