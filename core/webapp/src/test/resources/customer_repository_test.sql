insert into public.cst_state_type (cst_state_type, name) values (1, 'Aktiivne'), (2, 'Suletud');
insert into public.cst_type (cst_type, name) values (1, 'Edasimüüja'), (2, 'Partner'), (3, 'Klient'), (4, 'Suurklient');
insert into public.country (country, name) values (1, 'Eesti'), (2, 'Läti'), (3, 'Leedu'), (4, 'Soome');

insert into public.customer (first_name, last_name, identity_code, note, created, updated, created_by, updated_by, birth_date, cst_type, cst_state_type)
values ('Vahur', 'Kaar', '123', 'Notes', now(), now(), null, null, now(), 1, 1);

insert into public.cst_address (customer, zip, house, address, county, parish, town_county, address_type, phone, sms, mobile, email, note, country, created, updated, created_by, updated_by)
values (1, 'zip', 'house', 'address', 'county', 'parish', 'town_county', null, 'phone', 'sms', 'mobile', 'email', 'note', 2, now(), now(), null, null);
insert into public.cst_address (customer, zip, house, address, county, parish, town_county, address_type, phone, sms, mobile, email, note, country, created, updated, created_by, updated_by)
values (1, 'zip 2', 'house 2', 'address 2', 'county 2', 'parish 2', 'town_county 2', null, 'phone 2', 'sms 2', 'mobile 2', 'email 2', 'note 2', 1, now(), now(), null, null);