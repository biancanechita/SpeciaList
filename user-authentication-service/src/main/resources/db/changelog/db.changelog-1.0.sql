--liquibase formatted sql

--changeset bnechita:1
CREATE TABLE users
(
    id                 BIGSERIAL,
    email_address      VARCHAR NOT NULL,
    password           VARCHAR NOT NULL,
    role               VARCHAR NOT NULL,
    created_date_time  TIMESTAMP,
    modified_date_time TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE (email_address)
);