insert into public.cst_state_type (cst_state_type, name) values (1, 'Aktiivne'), (2, 'Suletud');
insert into public.cst_type (cst_type, name) values (1, 'Edasimüüja'), (2, 'Partner'), (3, 'Klient'), (4, 'Suurklient');
insert into public.contract_type (contract_type, name) values (1, 'Tavaleping');
INSERT INTO public.contract_status_type (contract_status_type, name)
VALUES
  (1, 'Projekt'),
  (2, 'Kinnitatud'),
  (3, 'Kehtiv'),
  (4, 'Peatatatud'),
  (5, 'Lõpetatud'),
  (6, 'Lõpetatud uuega');

insert into public.customer (first_name, last_name, identity_code, note, created, updated, created_by, updated_by, birth_date, cst_type, cst_state_type)
values ('Vahur', 'Kaar', '123', 'Notes', now(), now(), 1, 1, to_date('1999-12-12', 'YYYY-MM-DD'), 1, 1);

insert into public.contract (customer, contract_status_type, contract_type, cnt_number, name, description, valid_from, valid_to, created, updated, created_by, updated_by, conditions, note, value_amount)
values (1, 3, 1, '222', 'Leping', 'Kirjeldus',to_date('2000-12-12', 'YYYY-MM-DD'), to_date('2013-12-12', 'YYYY-MM-DD'), to_date('1999-12-12', 'YYYY-MM-DD'), to_date('1999-12-12', 'YYYY-MM-DD'), 0, 0, 'Tingimused', 'Note', 222.02);