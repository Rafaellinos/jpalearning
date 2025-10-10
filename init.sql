CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE person_qualification (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

INSERT INTO person_qualification (name) VALUES ('student'), ('teacher');

CREATE TABLE person (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    documento VARCHAR(14) NOT NULL,
    qualification_id BIGINT NOT NULL REFERENCES person_qualification(id)
);

CREATE TABLE email (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    email VARCHAR(100) NOT NULL,
    main_email BOOLEAN,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    person_id UUID
);

ALTER TABLE email
    ADD CONSTRAINT fk_email_person
    FOREIGN KEY (person_id) REFERENCES person(id)
    ON DELETE CASCADE;

CREATE INDEX idx_email_person_id ON email(person_id);


CREATE TABLE endereco (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    logradouro_name VARCHAR(100) NOT NULL,
    main_logradouro BOOLEAN,
    logradouro_number VARCHAR(50),
    cep VARCHAR(20),
    cidade VARCHAR(100),
    uf VARCHAR(10),
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    person_id UUID
);

ALTER TABLE endereco
    ADD CONSTRAINT fk_endereco_person
    FOREIGN KEY (person_id) REFERENCES person(id)
    ON DELETE CASCADE;

CREATE INDEX idx_endereco_person_id ON endereco(person_id);

CREATE TABLE telefone (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    telefone VARCHAR(20),
    ddd VARCHAR(5),
    main_telefone BOOLEAN,
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    person_id UUID
);

ALTER TABLE telefone
    ADD CONSTRAINT fk_telefone_person
    FOREIGN KEY (person_id) REFERENCES person(id)
    ON DELETE CASCADE;

CREATE INDEX idx_telefone_person_id ON telefone(person_id);
