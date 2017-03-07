#ADMIN,USER,DAN TESTER DIJADIKAN 1 TABLE

CREATE TABLE public.tb_user
(
  user_id bigint NOT NULL DEFAULT nextval('tb_tester_tester_id_seq'::regclass),
  username character varying(255) NOT NULL,
  password character varying(255) NOT NULL,
  name character varying(255) NOT NULL,
  roles character varying(255) NOT NULL,
  CONSTRAINT tb_user_pkey PRIMARY KEY (user_id),
  CONSTRAINT unique_username UNIQUE (username)
)

#TABLE SOAL

CREATE TABLE public.tb_soal
(
  id_soal integer NOT NULL,
  soal character varying(255) NOT NULL,
  pilihan_1 character(50),
  pilihan_2 character(50),
  CONSTRAINT tb_soal_pkey PRIMARY KEY (id_soal)
)

#TABLE JAWABAN MASIH BELUM DIBIKIN

