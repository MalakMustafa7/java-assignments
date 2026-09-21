CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE authorities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    role_id BIGINT NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE role_authorities (
    role_id BIGINT NOT NULL,
    authority_id BIGINT NOT NULL,

    PRIMARY KEY (role_id, authority_id),
    FOREIGN KEY (role_id) REFERENCES roles(id),
    FOREIGN KEY (authority_id) REFERENCES authorities(id)
);

ALTER TABLE customer
    ADD COLUMN user_id BIGINT;

ALTER TABLE customer
    ADD CONSTRAINT fk_customer_user
        FOREIGN KEY (user_id)
        REFERENCES users(id);

ALTER TABLE customer
    ADD CONSTRAINT uk_customer_user
        UNIQUE (user_id);