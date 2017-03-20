-- Ada penambahan column pada table user

-- Drop Previous table
DROP TABLE IF EXISTS tb_user;
DROP TABLE IF EXISTS tb_soal;
DROP SEQUENCE IF EXISTS tb_tester_tester_id_seq;


-- New Table Design
CREATE TABLE tb_role
(
  id SERIAL PRIMARY KEY,
  name character varying(100) NOT NULL,
  description character varying(255),
  CONSTRAINT tb_role_pkey PRIMARY KEY (id)
);

CREATE TABLE tb_user
(
  id SERIAL PRIMARY KEY,
  role_id integer,
  username character varying(100) NOT NULL,
  enabled boolean NOT NULL DEFAULT true,
  password character varying(255) NOT NULL,
  name character varying(255),
  email character varying(255),
  CONSTRAINT tb_user_pkey PRIMARY KEY (id),
  CONSTRAINT tb_user_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES public.tb_role (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tb_user_username_key UNIQUE (username)
);

CREATE TABLE tb_question_category
(
  id SERIAL PRIMARY KEY,
  question_category character varying(100) NOT NULL,
  description character varying(255),
  CONSTRAINT tb_question_category_pkey PRIMARY KEY (id)
);

CREATE TABLE tb_question
(
  id SERIAL PRIMARY KEY,
  category_id integer,
  ordering integer NOT NULL,
  question character varying(500) NOT NULL,
  CONSTRAINT tb_question_pkey PRIMARY KEY (id),
  CONSTRAINT tb_question_category_id_fkey FOREIGN KEY (category_id)
      REFERENCES public.tb_question_category (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE tb_answer
(
  id SERIAL PRIMARY KEY,
  question_id integer,
  ordering integer NOT NULL,
  answer character varying(255) NOT NULL,
  correct_answer boolean NOT NULL,
  CONSTRAINT tb_answer_pkey PRIMARY KEY (id),
  CONSTRAINT tb_answer_question_id_fkey FOREIGN KEY (question_id)
      REFERENCES public.tb_question (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE public.tb_user_test
(
  id SERIAL PRIMARY KEY,
  user_id integer,
  category_id integer,
  started timestamp without time zone,
  ended timestamp without time zone,
  score numeric,
  CONSTRAINT tb_user_test_pkey PRIMARY KEY (id),
  CONSTRAINT tb_user_test_category_id_fkey FOREIGN KEY (category_id)
      REFERENCES public.tb_question_category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT tb_user_test_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES public.tb_user (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);

-- Insert Dummy Data
INSERT INTO TB_ROLE(name) VALUES
('ADMIN'),
('TEST'),
('USER');

-- Insert Dummy Data For Login as Admin Username:kikimf password:Kiki3446
INSERT INTO tb_user (username,password,name,role_id,email) VALUES ('kikimf','$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.','Risky Miftahul Fajri',1,'kikihootowl@gmail.com')

-- Insert Dummy Data For Login as Tester Username:kikimf2 password:Kiki3446
INSERT INTO tb_user (username,password,name,role_id,email) VALUES ('kikimf2','$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.','Risky Miftahul Fajri',2,'kikihootowl@gmail.com')

-- Insert Dummy Data For Login as User Username:kikimf3 password:Kiki3446
INSERT INTO tb_user (username,password,name,role_id,email) VALUES ('kikimf3','$2a$10$Qc8EukKJ0YNH6gZttbK1nOO3vBBNDceY2owV04KRSpdPTuYLJUtR.','Risky Miftahul Fajri',3,'kikihootowl@gmail.com')