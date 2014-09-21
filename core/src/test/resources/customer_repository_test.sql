insert into public.cst_state_type (cst_state_type, name) values (1, 'Aktiivne'), (2, 'Suletud');
insert into public.cst_type (cst_type, name) values (1, 'Edasimüüja'), (2, 'Partner'), (3, 'Klient'), (4, 'Suurklient');

insert into public.customer (first_name, last_name, identity_code, note, created, updated, created_by, updated_by, birth_date, cst_type, cst_state_type)
values ('Vahur', 'Kaar', '123', 'Notes', now(), now(), null, null, now(), 1, 1);