--
-- PostgreSQL database dump
--

-- Dumped from database version 11.6 (Debian 11.6-1.pgdg90+1)
-- Dumped by pg_dump version 11.6 (Debian 11.6-1.pgdg90+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: enum_Attributes_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."enum_Attributes_type" AS ENUM (
    'CREATED',
    'ACCEPTED',
    'CANCELED'
);


ALTER TYPE public."enum_Attributes_type" OWNER TO postgres;

--
-- Name: enum_Attributes_type_attr; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."enum_Attributes_type_attr" AS ENUM (
    'AAA',
    'SSS',
    'DDD'
);


ALTER TYPE public."enum_Attributes_type_attr" OWNER TO postgres;

--
-- Name: enum_Notifications_type; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."enum_Notifications_type" AS ENUM (
    'ORDER_CREATED',
    'ORDER_ACCEPTED',
    'ORDER_CANCELED',
    'ORDER_DELETED'
);


ALTER TYPE public."enum_Notifications_type" OWNER TO postgres;

--
-- Name: enum_Orders_status; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."enum_Orders_status" AS ENUM (
    'CREATED',
    'ACCEPTED',
    'CANCELED'
);


ALTER TYPE public."enum_Orders_status" OWNER TO postgres;

--
-- Name: enum_TimeTables_slotSize; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."enum_TimeTables_slotSize" AS ENUM (
    'HOUR',
    'DAY',
    'WEEK'
);


ALTER TYPE public."enum_TimeTables_slotSize" OWNER TO postgres;

--
-- Name: enum_Users_role; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public."enum_Users_role" AS ENUM (
    'USER',
    'ADMIN'
);


ALTER TYPE public."enum_Users_role" OWNER TO postgres;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: attribute_values; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attribute_values (
    id integer NOT NULL,
    time_table_id integer NOT NULL,
    attribute_id integer NOT NULL,
    order_id integer NOT NULL,
    value character varying(255)
);


ALTER TABLE public.attribute_values OWNER TO postgres;

--
-- Name: AttributeValues_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."AttributeValues_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."AttributeValues_id_seq" OWNER TO postgres;

--
-- Name: AttributeValues_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."AttributeValues_id_seq" OWNED BY public.attribute_values.id;


--
-- Name: attributes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attributes (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    type_attr character varying(255) NOT NULL,
    is_required boolean NOT NULL,
    time_table_id integer NOT NULL
);


ALTER TABLE public.attributes OWNER TO postgres;

--
-- Name: Attributes_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Attributes_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Attributes_id_seq" OWNER TO postgres;

--
-- Name: Attributes_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Attributes_id_seq" OWNED BY public.attributes.id;


--
-- Name: notifications; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.notifications (
    id integer NOT NULL,
    order_id integer NOT NULL,
    user_id integer NOT NULL,
    type public."enum_Notifications_type" NOT NULL,
    is_read boolean NOT NULL
);


ALTER TABLE public.notifications OWNER TO postgres;

--
-- Name: Notifications_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Notifications_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Notifications_id_seq" OWNER TO postgres;

--
-- Name: Notifications_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Notifications_id_seq" OWNED BY public.notifications.id;


--
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    author_id integer NOT NULL,
    author_name character varying(255) NOT NULL,
    start_date timestamp with time zone NOT NULL,
    end_date timestamp with time zone NOT NULL,
    status public."enum_Orders_status" DEFAULT 'CREATED'::public."enum_Orders_status" NOT NULL,
    time_table_id integer NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Name: Orders_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Orders_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Orders_id_seq" OWNER TO postgres;

--
-- Name: Orders_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Orders_id_seq" OWNED BY public.orders.id;


--
-- Name: time_tables; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.time_tables (
    id integer NOT NULL,
    title character varying(255) NOT NULL,
    start_date timestamp with time zone NOT NULL,
    end_date timestamp with time zone NOT NULL,
    slot_size public."enum_TimeTables_slotSize" NOT NULL
);


ALTER TABLE public.time_tables OWNER TO postgres;

--
-- Name: TimeTables_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."TimeTables_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."TimeTables_id_seq" OWNER TO postgres;

--
-- Name: TimeTables_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."TimeTables_id_seq" OWNED BY public.time_tables.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role public."enum_Users_role" DEFAULT 'USER'::public."enum_Users_role" NOT NULL,
    image_path character varying(255)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: Users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public."Users_id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."Users_id_seq" OWNER TO postgres;

--
-- Name: Users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public."Users_id_seq" OWNED BY public.users.id;


--
-- Name: sessions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sessions (
    id uuid NOT NULL,
    sess jsonb NOT NULL
);


ALTER TABLE public.sessions OWNER TO postgres;

--
-- Name: attribute_values id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attribute_values ALTER COLUMN id SET DEFAULT nextval('public."AttributeValues_id_seq"'::regclass);


--
-- Name: attributes id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributes ALTER COLUMN id SET DEFAULT nextval('public."Attributes_id_seq"'::regclass);


--
-- Name: notifications id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications ALTER COLUMN id SET DEFAULT nextval('public."Notifications_id_seq"'::regclass);


--
-- Name: orders id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public."Orders_id_seq"'::regclass);


--
-- Name: time_tables id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.time_tables ALTER COLUMN id SET DEFAULT nextval('public."TimeTables_id_seq"'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public."Users_id_seq"'::regclass);


--
-- Data for Name: attribute_values; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attribute_values (id, time_table_id, attribute_id, order_id, value) FROM stdin;
1	1	1	2	Meeting
2	1	2	2	Count people 10
3	1	1	3	JAVA Conference
4	1	1	1	IT-Boroda
5	4	4	5	WCG
6	8	5	4	ESL
7	1	2	3	Count people 25
8	2	3	6	CodeWars
9	1	1	7	Golang-conferece
10	1	2	7	max count people 50
11	2	3	8	Day open doors
12	2	4	8	\N
13	2	3	9	Coronavirus Info
14	2	4	9	\N
15	8	7	10	English for developers
16	4	6	11	Halloween
17	4	6	12	Day of Great Macaroni Monster
18	3	5	13	Moscow meetup
19	4	6	14	Day of Remembrance Smalltalk
20	4	6	20	Day of Linux Admins
\.


--
-- Data for Name: attributes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attributes (id, title, type_attr, is_required, time_table_id) FROM stdin;
1	Name Event	STRING	t	1
2	Description	STRING	f	1
3	Event	STRING	t	2
4	Date	DATE	f	2
5	Event name	STRING	t	3
6	Measure	STRING	t	4
7	Arrangement	STRING	t	8
\.


--
-- Data for Name: notifications; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.notifications (id, order_id, user_id, type, is_read) FROM stdin;
1	2	2	ORDER_CREATED	f
2	2	2	ORDER_CANCELED	f
3	3	2	ORDER_ACCEPTED	f
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (id, author_id, author_name, start_date, end_date, status, time_table_id) FROM stdin;
1	1	Admin	2020-04-14 19:00:00+00	2020-04-14 20:00:00+00	ACCEPTED	1
2	2	John Doe	2020-04-09 19:00:00+00	2020-04-09 20:00:00+00	CREATED	1
3	2	John Doe	2020-04-12 19:00:00+00	2020-04-12 20:00:00+00	CREATED	1
4	3	Michail	2020-06-08 18:00:00+00	2020-06-08 19:00:00+00	CREATED	4
5	3	Michail	2020-09-03 01:00:00+00	2020-09-03 02:00:00+00	CREATED	8
6	2	John Doe	2020-02-22 00:00:00+00	2020-02-22 01:00:00+00	ACCEPTED	2
7	4	Ivan	2020-04-11 23:00:00+00	2020-04-12 00:00:00+00	CANCELED	1
8	4	Ivan	2020-02-20 21:00:00+00	2020-02-20 22:00:00+00	CREATED	2
9	4	Ivan	2020-02-22 19:00:00+00	2020-02-22 20:00:00+00	ACCEPTED	2
10	4	Ivan	2020-09-02 20:00:00+00	2020-09-02 21:00:00+00	CREATED	8
11	3	Michail	2020-06-17 18:00:00+00	2020-06-18 18:00:00+00	ACCEPTED	4
12	3	Michail	2020-06-22 18:00:00+00	2020-06-23 18:00:00+00	ACCEPTED	4
13	3	Michail	2020-04-17 21:00:00+00	2020-04-17 22:00:00+00	CREATED	3
14	2	John Doe	2020-06-08 18:00:00+00	2020-06-09 18:00:00+00	CREATED	4
15	2	John Doe	2020-05-12 18:00:00+00	2020-05-13 18:00:00+00	CANCELED	6
16	2	John Doe	2020-05-24 18:00:00+00	2020-05-25 18:00:00+00	CREATED	6
17	2	John Doe	2020-05-23 18:00:00+00	2020-05-24 18:00:00+00	CREATED	6
18	1	Admin	2020-05-30 18:00:00+00	2020-05-31 18:00:00+00	ACCEPTED	6
19	1	Admin	2020-05-29 18:00:00+00	2020-05-30 18:00:00+00	ACCEPTED	6
20	1	Admin	2020-06-14 18:00:00+00	2020-06-15 18:00:00+00	ACCEPTED	4
21	3	Michail	2020-07-21 18:00:00+00	2020-07-22 18:00:00+00	CREATED	5
22	4	Ivan	2020-07-21 18:00:00+00	2020-07-22 18:00:00+00	CREATED	5
23	2	John Doe	2020-07-21 18:00:00+00	2020-07-22 18:00:00+00	CREATED	5
24	1	Admin	2020-07-29 18:00:00+00	2020-07-30 18:00:00+00	ACCEPTED	5
25	4	Ivan	2020-07-24 18:00:00+00	2020-07-25 18:00:00+00	CREATED	5
26	4	Ivan	2020-07-31 18:00:00+00	2020-08-01 18:00:00+00	CANCELED	5
\.


--
-- Data for Name: sessions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sessions (id, sess) FROM stdin;
\.


--
-- Data for Name: time_tables; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.time_tables (id, title, start_date, end_date, slot_size) FROM stdin;
1	DevOps Battle 2.2	2020-04-09 18:00:00+00	2020-04-15 17:00:00+00	HOUR
2	Skolkovo Junior Challenge 2020	2020-02-19 18:00:00+00	2020-02-25 17:00:00+00	HOUR
3	Moscow JS Meetup	2020-04-16 18:00:00+00	2020-04-21 17:00:00+00	HOUR
4	ISDEF Spring 2020	2020-06-07 18:00:00+00	2020-06-28 17:00:00+00	DAY
5	HackTheRealty	2020-07-05 18:00:00+00	2020-08-16 17:00:00+00	DAY
6	MCOM Foodtech Anticrisis	2020-05-03 18:00:00+00	2020-05-31 17:00:00+00	DAY
7	Serverless Architecture Conference 2020	2020-06-30 18:00:00+00	2020-07-05 17:00:00+00	HOUR
8	HR API Online-marathon 2020	2020-09-02 18:00:00+00	2020-09-05 17:00:00+00	HOUR
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, name, email, password, role, image_path) FROM stdin;
1	Admin	admin@google.com	admin_passw	ADMIN	user_avatar_1.png
2	John Doe	joo@google.com	john_passw	USER	user_avatar_2.png
3	Michail	micha@mail.ru	mich_passw	USER	user_avatar_3.png
4	Ivan	iv@mail.ru	iv_passw	USER	user_avatar_4.png
5	Petr	Petr@mail.ru	Petr_passw	USER	user_avatar_5.png
6	Dima	Dima@mail.ru	Dima_passw	USER	user_avatar_6.png
7	Anton	Anton@mail.ru	Anton_passw	USER	user_avatar_7.png
8	Joseph	Joseph@mail.ru	Joseph_passw	USER	user_avatar_8.png
9	Micle	Micle@mail.ru	Micle_passw	USER	user_avatar_9.png
10	Luisa	Luisa@mail.ru	Luisa_passw	USER	user_avatar_10.png
11	Sebastian	Sebastian@mail.ru	Sebastian_passw	USER	user_avatar_11.png
12	Vika	Vika@mail.ru	Vika_passw	USER	user_avatar_12.png
13	Polina	Polina@mail.ru	Polina_passw	USER	user_avatar_13.png
14	Achmed	Achmed@mail.ru	Achmed_passw	USER	user_avatar_14.png
15	Kim_Chen_In	Kim_Chen_In@mail.ru	Kim_Chen_In_passw	USER	user_avatar_15.png
\.


--
-- Name: AttributeValues_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."AttributeValues_id_seq"', 20, true);


--
-- Name: Attributes_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Attributes_id_seq"', 7, true);


--
-- Name: Notifications_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Notifications_id_seq"', 3, true);


--
-- Name: Orders_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Orders_id_seq"', 26, true);


--
-- Name: TimeTables_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."TimeTables_id_seq"', 8, true);


--
-- Name: Users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."Users_id_seq"', 15, true);


--
-- Name: attribute_values AttributeValues_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attribute_values
    ADD CONSTRAINT "AttributeValues_pkey" PRIMARY KEY (id);


--
-- Name: attributes Attributes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributes
    ADD CONSTRAINT "Attributes_pkey" PRIMARY KEY (id);


--
-- Name: notifications Notifications_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT "Notifications_pkey" PRIMARY KEY (id);


--
-- Name: orders Orders_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT "Orders_pkey" PRIMARY KEY (id);


--
-- Name: sessions Sessions_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sessions
    ADD CONSTRAINT "Sessions_pkey" PRIMARY KEY (id);


--
-- Name: time_tables TimeTables_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.time_tables
    ADD CONSTRAINT "TimeTables_pkey" PRIMARY KEY (id);


--
-- Name: users Users_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT "Users_email_key" UNIQUE (email);


--
-- Name: users Users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT "Users_pkey" PRIMARY KEY (id);


--
-- Name: attribute_values AttributeValues_attributeId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attribute_values
    ADD CONSTRAINT "AttributeValues_attributeId_fkey" FOREIGN KEY (attribute_id) REFERENCES public.attributes(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: attribute_values AttributeValues_orderId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attribute_values
    ADD CONSTRAINT "AttributeValues_orderId_fkey" FOREIGN KEY (order_id) REFERENCES public.orders(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: attribute_values AttributeValues_timeTableId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attribute_values
    ADD CONSTRAINT "AttributeValues_timeTableId_fkey" FOREIGN KEY (time_table_id) REFERENCES public.time_tables(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: attributes Attributes_timeTableId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributes
    ADD CONSTRAINT "Attributes_timeTableId_fkey" FOREIGN KEY (time_table_id) REFERENCES public.time_tables(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: notifications Notifications_orderId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT "Notifications_orderId_fkey" FOREIGN KEY (order_id) REFERENCES public.orders(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: notifications Notifications_userId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.notifications
    ADD CONSTRAINT "Notifications_userId_fkey" FOREIGN KEY (user_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: orders Orders_authorId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT "Orders_authorId_fkey" FOREIGN KEY (author_id) REFERENCES public.users(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: orders Orders_timeTableId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT "Orders_timeTableId_fkey" FOREIGN KEY (time_table_id) REFERENCES public.time_tables(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

