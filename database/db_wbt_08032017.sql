-- Drop Previous table
DROP TABLE IF EXISTS tb_user;
DROP TABLE IF EXISTS tb_soal;
DROP SEQUENCE IF EXISTS tb_tester_tester_id_seq;

-- New Table Design
CREATE TABLE TB_ROLE(
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE TB_USER(
    id SERIAL PRIMARY KEY,
    role_id INTEGER REFERENCES TB_ROLE,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE TB_QUESTION_CATEGORY(
    id SERIAL PRIMARY KEY,
    question_category VARCHAR(100) NOT NULL,
    description VARCHAR(255)
);

CREATE TABLE TB_QUESTION(
    id SERIAL PRIMARY KEY,
    category_id INTEGER REFERENCES TB_QUESTION_CATEGORY,
    ordering INTEGER NOT NULL,
    question VARCHAR(500) NOT NULL
);

CREATE TABLE TB_ANSWER(
    id SERIAL PRIMARY KEY,
    question_id INTEGER REFERENCES TB_QUESTION,
    ordering INTEGER NOT NULL,
    answer VARCHAR(255) NOT NULL,
    correct_answer BOOLEAN NOT NULL
);

CREATE TABLE TB_USER_TEST(
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES TB_USER,
    category_id INTEGER REFERENCES TB_QUESTION_CATEGORY,
    started TIMESTAMP,
    ended TIMESTAMP,
    score DECIMAL
);

-- Insert Dummy Data
INSERT INTO TB_ROLE(name) VALUES
('ADMIN'),
('TEST'),
('USER');