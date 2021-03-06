CREATE SEQUENCE public.tb_answer_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 71
  CACHE 1;
ALTER TABLE public.tb_answer_id_seq
  OWNER TO postgres;

CREATE SEQUENCE public.tb_question_category_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 17
  CACHE 1;
ALTER TABLE public.tb_question_category_id_seq
  OWNER TO postgres;

CREATE SEQUENCE public.tb_question_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 19
  CACHE 1;
ALTER TABLE public.tb_question_id_seq
  OWNER TO postgres;

CREATE SEQUENCE public.tb_role_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 3
  CACHE 1;
ALTER TABLE public.tb_role_id_seq
  OWNER TO postgres;

CREATE SEQUENCE public.tb_user_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 7
  CACHE 1;
ALTER TABLE public.tb_user_id_seq
  OWNER TO postgres;

CREATE SEQUENCE public.tb_user_test_id_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1
  CACHE 1;
ALTER TABLE public.tb_user_test_id_seq
  OWNER TO postgres;

CREATE SEQUENCE public.tb_user_test_id_seq1
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 75
  CACHE 1;
ALTER TABLE public.tb_user_test_id_seq1
  OWNER TO postgres;
  
  
CREATE TABLE public.tb_role
(
  id integer NOT NULL DEFAULT nextval('tb_role_id_seq'::regclass),
  name character varying(100) NOT NULL,
  description character varying(255),
  CONSTRAINT tb_role_pkey PRIMARY KEY (id)
)

CREATE TABLE public.tb_user
(
  id integer NOT NULL DEFAULT nextval('tb_user_id_seq'::regclass),
  role_id integer,
  username character varying(100) NOT NULL,
  enabled boolean NOT NULL DEFAULT true,
  password character varying(255) NOT NULL,
  name character varying(255),
  email character varying(255),
  CONSTRAINT tb_user_pkey PRIMARY KEY (id),
  CONSTRAINT tb_user_role_id_fkey FOREIGN KEY (role_id)
      REFERENCES public.tb_role (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT tb_user_username_key UNIQUE (username)
)

CREATE TABLE public.tb_question_category
(
  id integer NOT NULL DEFAULT nextval('tb_question_category_id_seq1'::regclass),
  question_category character varying(100) NOT NULL,
  description character varying(255),
  question_type character varying(30),
  time_limit numeric(32,0),
  CONSTRAINT tb_question_category_pkey PRIMARY KEY (id)
)

CREATE TABLE public.tb_question
(
  id integer NOT NULL DEFAULT nextval('tb_question_id_seq'::regclass),
  category_id integer,
  ordering integer NOT NULL,
  question character varying(500) NOT NULL,
  CONSTRAINT tb_question_pkey PRIMARY KEY (id),
  CONSTRAINT tb_question_category_id_fkey FOREIGN KEY (category_id)
      REFERENCES public.tb_question_category (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)

CREATE TABLE tb_answer
(
  id integer NOT NULL DEFAULT nextval('tb_answer_id_seq'::regclass),
  question_id integer,
  ordering integer NOT NULL,
  answer character varying(255) NOT NULL,
  correct_answer boolean NOT NULL,
  CONSTRAINT tb_answer_pkey PRIMARY KEY (id),
  CONSTRAINT tb_answer_question_id_fkey FOREIGN KEY (question_id)
      REFERENCES public.tb_question (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)

CREATE TABLE public.tb_user_test
(
  id integer NOT NULL DEFAULT nextval('tb_user_test_id_seq1'::regclass),
  user_id integer,
  category_id integer,
  started timestamp without time zone,
  ended timestamp without time zone,
  score numeric DEFAULT 0,
  status character varying(10) NOT NULL DEFAULT 'Not yet'::character varying,
  CONSTRAINT tb_user_test_pkey PRIMARY KEY (id),
  CONSTRAINT tb_user_test_category_id_fkey FOREIGN KEY (category_id)
      REFERENCES public.tb_question_category (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT tb_user_test_user_id_fkey FOREIGN KEY (user_id)
      REFERENCES public.tb_user (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
)