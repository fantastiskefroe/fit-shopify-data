CREATE TABLE products
(
    id                  BIGINT GENERATED ALWAYS AS IDENTITY NOT NULL,
    shopify_id          BIGINT                              NOT NULL,
    title               VARCHAR(255)                        NOT NULL,
    handle              VARCHAR(255)                        NOT NULL,
    status              VARCHAR(32)                         NOT NULL,
    main_image_url      VARCHAR(255)                        NOT NULL,
    created_date_time   TIMESTAMPTZ                         NOT NULL,
    updated_date_time   TIMESTAMPTZ                         NOT NULL,
    published_date_time TIMESTAMPTZ                         NULL,

    CONSTRAINT products_pk
        PRIMARY KEY (id)
);
