--liquibase formatted sql

--changeset bnechita:1
CREATE TABLE specializations
(
    id             BIGSERIAL,
    specialization VARCHAR NOT NULL,
    description    VARCHAR,
    PRIMARY KEY (id)
);

--changeset bnechita:2
CREATE TABLE cities
(
    id        BIGSERIAL,
    city      VARCHAR NOT NULL,
    area_code VARCHAR,
    PRIMARY KEY (id)
);

--changeset bnechita:3
CREATE TABLE clinics
(
    id                  BIGSERIAL,
    clinic              VARCHAR   NOT NULL,
    address             VARCHAR   NOT NULL,
    city_id             BIGSERIAL NOT NULL,
    contact_information VARCHAR   NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_city
        FOREIGN KEY (city_id)
            REFERENCES cities (id)
            ON DELETE CASCADE
);

--changeset bnechita:4
CREATE TABLE doctors
(
    id                  BIGSERIAL,
    first_name          VARCHAR       NOT NULL,
    last_name           VARCHAR       NOT NULL,
    specialization_id   BIGSERIAL     NOT NULL,
    contact_information VARCHAR       NOT NULL,
    bio                 VARCHAR,
    average_rating      NUMERIC(4, 2) NOT NULL,
    total_reviews       INTEGER       NOT NULL,
    photo_url           VARCHAR,
    clinic_id           BIGSERIAL     NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_specialization
        FOREIGN KEY (specialization_id)
            REFERENCES specializations (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_clinic
        FOREIGN KEY (clinic_id)
            REFERENCES clinics (id)
            ON DELETE CASCADE
);

--changeset bnechita:5
CREATE TABLE investigations
(
    id                BIGSERIAL,
    investigation     VARCHAR   NOT NULL,
    specialization_id BIGSERIAL NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_specialization
        FOREIGN KEY (specialization_id)
            REFERENCES specializations (id)
            ON DELETE CASCADE
);

--changeset bnechita:6
CREATE TABLE doctor_investigations_expense
(
    id               BIGSERIAL,
    investigation_id BIGSERIAL     NOT NULL,
    doctor_id        BIGSERIAL     NOT NULL,
    price            NUMERIC(7, 2) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_investigation
        FOREIGN KEY (investigation_id)
            REFERENCES investigations (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_doctor
        FOREIGN KEY (doctor_id)
            REFERENCES doctors (id)
            ON DELETE CASCADE
);