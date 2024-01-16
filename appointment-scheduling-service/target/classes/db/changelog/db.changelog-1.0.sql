--liquibase formatted sql

--changeset bnechita:1
CREATE TABLE patients
(
    id                 BIGSERIAL,
    first_name         VARCHAR   NOT NULL,
    last_name          VARCHAR   NOT NULL,
    cnp                VARCHAR   NOT NULL,
    date_of_birth      DATE,
    gender             VARCHAR,
    phone_number       VARCHAR,
    email_address      VARCHAR,
    user_id            BIGSERIAL NOT NULL,
    created_date_time  TIMESTAMP,
    modified_date_time TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE (cnp)
);

--changeset bnechita:2
CREATE TABLE appointments
(
    id                       BIGSERIAL,
    patient_id               BIGSERIAL NOT NULL,
    doctor_id                BIGSERIAL NOT NULL,
    medical_investigation_id BIGSERIAL NOT NULL,
    date                     DATE      NOT NULL,
    start_time               TIME      NOT NULL,
    end_time                 TIME      NOT NULL,
    diagnostic               VARCHAR,
    created_date_time        TIMESTAMP,
    modified_date_time       TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_patient
        FOREIGN KEY (patient_id)
            REFERENCES patients (id)
);

--changeset bnechita:3
CREATE TABLE appointment_requests
(
    id                 BIGSERIAL,
    doctor_id          BIGSERIAL NOT NULL,
    investigation_id   BIGSERIAL NOT NULL,
    first_name         VARCHAR   NOT NULL,
    last_name          VARCHAR   NOT NULL,
    phone_number       VARCHAR   NOT NULL,
    email              VARCHAR   NOT NULL,
    created_date_time  TIMESTAMP,
    modified_date_time TIMESTAMP,
    PRIMARY KEY (id)
);

