--liquibase formatted sql

--changeset bnechita:1
create table doctors
(
    id                  bigserial primary key,
    first_name          varchar(50) not null,
    last_name           varchar(50) not null,
    specialization_id   bigserial   not null,
    contact_information varchar(50) not null,
    bio                 text,
    average_rating      numeric(4, 2),
    total_reviews       integer,
    photo_url           text,
    clinic_id           bigserial   not null
);

create table specializations
(
    id          bigserial primary key,
    name        varchar(50) not null,
    description text
);

create table clinics
(
    id                  bigserial primary key,
    name                varchar(50) not null,
    address             text,
    city_id             bigserial   not null,
    contact_information varchar(50) not null
);

create table cities
(
    id   bigserial primary key,
    name varchar(50) not null
);