insert into public.cst_state_type (cst_state_type, name) values (1, 'Aktiivne'), (2, 'Suletud');
insert into public.cst_type (cst_type, name) values (1, 'Edasimüüja'), (2, 'Partner'), (3, 'Klient'), (4, 'Suurklient');
insert into public.contract_type (contract_type, name) values (1, 'Tavaleping');
insert into public.country (country, name) values (1, 'Eesti'), (2, 'Läti'), (3, 'Leedu'), (4, 'Soome');
INSERT INTO public.contract_status_type (contract_status_type, name)
VALUES
  (1, 'Projekt'),
  (2, 'Kinnitatud'),
  (3, 'Kehtiv'),
  (4, 'Peatatatud'),
  (5, 'Lõpetatud'),
  (6, 'Lõpetatud uuega'),
  (7, 'Kustutatud');