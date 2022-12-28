CREATE SEQUENCE hibernate_sequence START 1;

CREATE TABLE orders
(
    id                   BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    name                 VARCHAR(16)      NOT NULL,
    number               INT              NOT NULL,
    cancel_reason        VARCHAR(10)      NULL,
    financial_status     VARCHAR(20)      NOT NULL,
    fulfillment_status   VARCHAR(10)      NOT NULL,
    total_discount       DOUBLE PRECISION NULL,
    subtotal_price       DOUBLE PRECISION NULL,
    total_tax            DOUBLE PRECISION NULL,
    total_price          DOUBLE PRECISION NULL,
    total_shipping_price DOUBLE PRECISION NULL,
    created_date_time    TIMESTAMPTZ      NOT NULL,
    valid_from           TIMESTAMPTZ      NOT NULL DEFAULT now(),
    valid_to             TIMESTAMPTZ      NULL,

    CONSTRAINT orders_pk
        PRIMARY KEY (id),

    CONSTRAINT orders_name_key
        UNIQUE (name, valid_from, valid_to)
);

CREATE UNIQUE INDEX orders_id_uindex
    ON orders (id);

CREATE TABLE order_line
(
    id            BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    order_id      BIGINT           NOT NULL,
    sku           VARCHAR(64)      NOT NULL,
    title         VARCHAR(255)     NOT NULL,
    variant_title VARCHAR(255),
    quantity      INT              NOT NULL,
    price         DOUBLE PRECISION NULL,

    CONSTRAINT order_line_pk
        PRIMARY KEY (id),

    CONSTRAINT order_line_order_id_fk
        FOREIGN KEY (order_id) REFERENCES orders (id)
            ON DELETE CASCADE
);

CREATE UNIQUE INDEX order_line_id_uindex
    ON order_line (id);
