insert into public.contract_type (contract_type, name) values (1, 'Tavaleping');
INSERT INTO public.contract_status_type (contract_status_type, name)
VALUES
  (1, 'Projekt'),
  (2, 'Kinnitatud'),
  (3, 'Kehtiv'),
  (4, 'Peatatatud'),
  (5, 'Lõpetatud'),
  (6, 'Lõpetatud uuega'),
  (7, 'Kustutatud');

insert into public.customer (first_name, last_name, identity_code, note, created, updated, created_by, updated_by, birth_date, cst_type, cst_state_type)
values ('Vahur', 'Kaar', '123', 'Notes', now(), now(), null, null, now(), null, null);

insert into public.contract (customer, contract_status_type, contract_type, cnt_number, name, description, valid_from, valid_to, created, updated, created_by, updated_by, conditions, note, value_amount)
values (1, 3, 1, '222', 'Leping', 'Kirjeldus', now(), now(), now(), now(), 0, 0, 'Tingimused', 'Note', 222.02);