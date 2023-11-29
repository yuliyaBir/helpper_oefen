insert into users (id, voornaam, familienaam, email, wachtwoord, rollen)
values (1, 'Budgethouder', 'Budgethouder', 'budgethouder@test.com', '$2a$09$N7SLPG4T3LzY5lf5vxjjd.vxpJv.1jRs7gD20TacgOZYC19XS.rJ2', 'budgethouder'),
       (2, 'Assistent', 'Assistent', 'assistent@test.com', '$2a$09$V257c5z5NJU48LdaNAeilOuwBSuPm/4zwGhw3huBNh6AaBVWBN0f2', 'assistent') ON CONFLICT DO NOTHING;
-- wachtwoord voor budgethouder = 'budgethouder', voor assistent = 'assistent'
insert into goedkeuringen (id, goedgekeurd, commentaar, uren)
values (1, '2020-12-12', 'test1', 4)
ON CONFLICT DO NOTHING;
insert into prestaties (id, naam, omschrijving, assistent_id, budgethouder_id, goedkeuring_id)
values (1, 'test1', 'test1', 2, 1, 1),
       (2, 'test2', 'test2', 2, 1, null)
ON CONFLICT DO NOTHING;
ALTER SEQUENCE IF EXISTS public.users_id_seq RESTART WITH 10;
ALTER SEQUENCE IF EXISTS public.prestaties_id_seq RESTART WITH 10;
ALTER SEQUENCE IF EXISTS public.goedkeuringen_seq RESTART WITH 10;