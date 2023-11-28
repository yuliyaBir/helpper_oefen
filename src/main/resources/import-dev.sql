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
ALTER SEQUENCE IF EXISTS public.users_id_seq RESTART WITH 10;
ALTER SEQUENCE IF EXISTS public.prestaties_id_seq RESTART WITH 10;
ALTER SEQUENCE IF EXISTS public.goedkeuringen_seq RESTART WITH 10;
