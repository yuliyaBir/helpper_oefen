-- -- This file allow to write SQL commands that will be emitted in test and dev.
-- -- The commands are commented as their support depends of the database
-- -- insert into myentity (id, field) values(1, 'field-1');
-- -- insert into myentity (id, field) values(2, 'field-2');
-- -- insert into myentity (id, field) values(3, 'field-3');
-- -- alter sequence myentity_seq restart with 4;
--
-- CREATE TABLE public.goedkeuringen (
--                                       goedgekeurd date NOT NULL,
--                                       commentaar character varying(500),
--                                       uren integer NOT NULL,
--                                       id integer NOT NULL
-- );
--
--
-- ALTER TABLE public.goedkeuringen OWNER TO postgres;
--
-- --
-- -- TOC entry 217 (class 1259 OID 24691)
-- -- Name: goedkeuringen_seq; Type: SEQUENCE; Schema: public; Owner: postgres
-- --
--
-- CREATE SEQUENCE public.goedkeuringen_seq
--     START WITH 1
--     INCREMENT BY 1
--     NO MINVALUE
--     NO MAXVALUE
--     CACHE 1;
--
--
-- ALTER SEQUENCE public.goedkeuringen_seq OWNER TO postgres;
--
-- --
-- -- TOC entry 219 (class 1259 OID 24829)
-- -- Name: prestaties; Type: TABLE; Schema: public; Owner: postgres
-- --
--
-- CREATE TABLE public.prestaties (
--                                    id integer NOT NULL,
--                                    naam character varying(100) NOT NULL,
--                                    omschrijving character varying,
--                                    assistent_id integer NOT NULL,
--                                    budgethouder_id integer NOT NULL,
--                                    goedkeuring_id integer
-- );
--
--
-- ALTER TABLE public.prestaties OWNER TO postgres;
--
-- --
-- -- TOC entry 218 (class 1259 OID 24828)
-- -- Name: prestaties_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
-- --
--
-- ALTER TABLE public.prestaties ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
--     SEQUENCE NAME public.prestaties_id_seq
--     START WITH 1
--     INCREMENT BY 1
--     NO MINVALUE
--     NO MAXVALUE
--     CACHE 1
--     );
--
--
-- --
-- -- TOC entry 216 (class 1259 OID 16417)
-- -- Name: users; Type: TABLE; Schema: public; Owner: postgres
-- --
--
-- CREATE TABLE public.users (
--                               id integer NOT NULL,
--                               voornaam character varying(50) NOT NULL,
--                               familienaam character varying(50) NOT NULL,
--                               email character varying(100) NOT NULL,
--                               wachtwoord character varying(500),
--                               rollen character varying
-- );
--
--
-- ALTER TABLE public.users OWNER TO postgres;
--
-- --
-- -- TOC entry 215 (class 1259 OID 16416)
-- -- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
-- --
--
-- ALTER TABLE public.users ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
--     SEQUENCE NAME public.users_id_seq
--     START WITH 1
--     INCREMENT BY 1
--     NO MINVALUE
--     NO MAXVALUE
--     CACHE 1
--     );
insert into users (voornaam, familienaam, email, wachtwoord, rollen) values(('budgethouder', 'budgethouder', 'budgethouder@test.com', 'budgethouder', 'budgethouder'),
                                                                            ('assistent', 'assistent', 'assistent@test.com', 'assistent', 'assistent'))    ON CONFLICT DO NOTHING;
insert into goedkeuringen (goedgekeurd, commentaar, uren) values ((2020-12-12, 'test1', 4), (2023-10-10, 'test2', 3))    ON CONFLICT DO NOTHING;
insert into prestaties (naam, omschrijving, assistent_id, budgethouder_id, goedkeuring_id) values (
                                                                                                   ('test1', 'test1', (select id from users where email = 'assistent@test.com'),
                                                                                                    (select id from users where email = 'budgethouder@test.com'),
                                                                                                    (select id from goedkeuringen where commentaar = 'test1'))),
                                                                                               ('test2', 'test1',
                                                                                                (select id from users where email = 'assistent@test.com'),
                                                                                                (select id from users where email = 'budgethouder@test.com'),
                                                                                                (select id from goedkeuringen where commentaar = 'test1'))    ON CONFLICT DO NOTHING;
ALTER SEQUENCE IF EXISTS public.users_id_seq RESTART WITH 100;
ALTER SEQUENCE IF EXISTS public.prestaties_id_seq RESTART WITH 100;
ALTER SEQUENCE IF EXISTS public.goedkeuringen_seq RESTART WITH 100;
