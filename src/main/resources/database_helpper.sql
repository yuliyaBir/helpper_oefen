--
-- PostgreSQL database dump
--

-- Dumped from database version 16.0
-- Dumped by pg_dump version 16.0

-- Started on 2023-11-24 09:09:34

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
-- TOC entry 220 (class 1259 OID 24851)
-- Name: goedkeuringen; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.goedkeuringen (
    goedgekeurd date NOT NULL,
    commentaar character varying(500),
    uren integer NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.goedkeuringen OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 24691)
-- Name: goedkeuringen_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.goedkeuringen_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.goedkeuringen_seq OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 24829)
-- Name: prestaties; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.prestaties (
    id integer NOT NULL,
    naam character varying(100) NOT NULL,
    omschrijving character varying,
    assistent_id integer NOT NULL,
    budgethouder_id integer NOT NULL,
    goedkeuring_id integer
);


ALTER TABLE public.prestaties OWNER TO postgres;

--
-- TOC entry 218 (class 1259 OID 24828)
-- Name: prestaties_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.prestaties ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.prestaties_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 216 (class 1259 OID 16417)
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    id integer NOT NULL,
    voornaam character varying(50) NOT NULL,
    familienaam character varying(50) NOT NULL,
    email character varying(100) NOT NULL,
    wachtwoord character varying(500),
    rollen character varying
);


ALTER TABLE public.users OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16416)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.users ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 4859 (class 0 OID 24851)
-- Dependencies: 220
-- Data for Name: goedkeuringen; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.goedkeuringen (goedgekeurd, commentaar, uren, id) FROM stdin;
2020-12-12	vlot verlopen	4	15
2023-11-21	goed	6	16
2023-11-21	goed	7	17
2023-11-21	w	3	18
2023-11-21	superleuk	24	19
2023-11-21	goed	4	20
2023-11-21	asd	4	21
2023-11-21	goed verlopen	4	22
2023-11-21	dsfg	5	23
2023-11-21	df	4	24
2023-11-21	c	2	25
2023-11-21	d	1	26
2023-11-21	e	0	27
2023-11-21	x	0	28
2023-11-21	x	0	29
2023-11-21	ok	1	30
2023-11-21	x	1	34
2023-11-22	fuck	3	31
2023-11-22	d	1	32
2023-11-22	cf	1	33
2023-11-22	s	2	36
2023-11-22	 vb	3	37
\.


--
-- TOC entry 4858 (class 0 OID 24829)
-- Dependencies: 219
-- Data for Name: prestaties; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.prestaties (id, naam, omschrijving, assistent_id, budgethouder_id, goedkeuring_id) FROM stdin;
2	samenkoken	familiefeest	21	18	15
3	opstap naar Brussel	met een auto	21	18	16
4	dfjkas	sdngml	21	18	18
10	naar Wit-Rusland	met het vligtuig	21	18	19
11	naar Sint-Niklaas	met de auto	21	18	20
5	dasf	asdf	21	18	21
9	opstap naar Limburg	met de bus	21	18	22
6	opstap naar Mechelen	met de trein	21	18	23
7	opstap naar Gent	met de auto	21	18	24
8	opstap naar Luik	met de auto	21	18	25
14	sd	dg	21	18	26
13	Samen naar het theater	Naar de tentoonstelling Francis Alÿs in WIELS	21	18	27
12	Tuineren	in het weekend	21	18	28
17	Colruyt	3 koopjes	21	21	\N
15	Colruyt	24 koopjes	21	18	29
22	Colruyt	Melk	21	18	\N
23	Colruyt	Melk	21	18	\N
24	Colruyt	Melk	21	18	\N
25	Colruyt	Melk	21	18	\N
26	Colruyt	Melk	21	18	\N
27	Colruyt	Melk	21	18	\N
28	Colruyt	Melk	21	18	\N
30	Colruyt	Brood	21	18	30
29	Colruyt	Melk	21	18	34
16	Colruyt	12 koopjes	21	18	31
18	Colruyt	1 koopje	21	18	32
19	Colruyt	Melk	21	18	33
20	Colruyt	Melk	21	18	36
21	Colruyt	Melk	21	18	37
31	sdfs	df	21	21	\N
32	dfh	dfh	21	21	\N
33	drfh	dfh	21	21	\N
34	fdgj	fgj	21	21	\N
35	sga	sf	21	18	\N
36	sdfg	sdg	21	18	\N
\.


--
-- TOC entry 4855 (class 0 OID 16417)
-- Dependencies: 216
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (id, voornaam, familienaam, email, wachtwoord, rollen) FROM stdin;
18	hryry	Mikao	mido@gh.com	$2a$10$rZgyD7nZ2cU3up7Gu3lYPOpp45UJxWUkoFRsBfnO8LTjzpMqrcKdm	budgethouder
21	Twister	Twister	twister@twister.com	$2a$10$5s9WpIDmQDPBLAGe0mI4He.Hmw6PrP.0F4JuWFhZn41dec/apz.QC	assistent
27	sd	sd	sd@sd.com	$2a$10$SdA6edTuXMhUp3t.q5esIeAOrwKfizM8QHforDpo8YsRtLAOwyCCS	assistent
28	Budgethouder	Budgethouder	budgethouder@budgethouder.com	$2a$10$SmztARrI9j723YuOT6G7WePQHRnw.uwcRNwDEHfJ8LsCdJVSTARe.	budgethouder
\.


--
-- TOC entry 4865 (class 0 OID 0)
-- Dependencies: 217
-- Name: goedkeuringen_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.goedkeuringen_seq', 23, true);


--
-- TOC entry 4866 (class 0 OID 0)
-- Dependencies: 218
-- Name: prestaties_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.prestaties_id_seq', 36, true);


--
-- TOC entry 4867 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 30, true);


--
-- TOC entry 4708 (class 2606 OID 24857)
-- Name: goedkeuringen goedkeuringen_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.goedkeuringen
    ADD CONSTRAINT goedkeuringen_pkey PRIMARY KEY (id);


--
-- TOC entry 4706 (class 2606 OID 24835)
-- Name: prestaties prestatie_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prestaties
    ADD CONSTRAINT prestatie_pkey PRIMARY KEY (id);


--
-- TOC entry 4702 (class 2606 OID 24701)
-- Name: users uq_users_email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uq_users_email UNIQUE (email);


--
-- TOC entry 4704 (class 2606 OID 16421)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 4709 (class 2606 OID 24836)
-- Name: prestaties fk_prestaties_assistent_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prestaties
    ADD CONSTRAINT fk_prestaties_assistent_users FOREIGN KEY (assistent_id) REFERENCES public.users(id);


--
-- TOC entry 4710 (class 2606 OID 24841)
-- Name: prestaties fk_prestaties_budgethouder_users; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.prestaties
    ADD CONSTRAINT fk_prestaties_budgethouder_users FOREIGN KEY (budgethouder_id) REFERENCES public.users(id);


-- Completed on 2023-11-24 09:09:34

--
-- PostgreSQL database dump complete
--

