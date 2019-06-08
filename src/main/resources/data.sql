insert into Casopis (id, naziv, tip_casopisa, open_access, glavni_urednik_id) values (1, 'Casopis1', 0, false, 9);
insert into Casopis (id, naziv, tip_casopisa, open_access, glavni_urednik_id) values (2, 'Casopis2', 1, false, 9);
insert into Casopis (id, naziv, tip_casopisa, open_access, glavni_urednik_id) values (3, 'Casopis3', 1, false, 9);

--autori id-1,2,3,4
insert into Korisnik (ime, prezime, drzava, grad, email, username, password, tip_korisnika) values ("Autor1", "Autor1", "Srbija", "Novi Sad", "autor1@mailinator.com", "autor1", "pass", 0);
insert into Korisnik (ime, prezime, drzava, grad, email, username, password, tip_korisnika) values ("Autor2", "Autor2", "Srbija", "Novi Sad", "autor2@mailinator.com", "autor2", "pass", 0);
insert into Korisnik (ime, prezime, drzava, grad, email, username, password, tip_korisnika) values ("Autor3", "Autor3", "Srbija", "Novi Sad", "autor3@mailinator.com", "autor3", "pass", 0);
insert into Korisnik (ime, prezime, drzava, grad, email, username, password, tip_korisnika) values ("Autor4", "Autor4", "Srbija", "Novi Sad", "autor4@mailinator.com", "autor4", "pass", 0);

--recenzenti id-5,6,7,8
insert into Korisnik (ime, prezime, drzava, grad, email, username, password, tip_korisnika) values ("Recenzent1", "Recenzent1", "Srbija", "Novi Sad", "rencenzent1@mailinator.com", "recenzent1", "pass", 2);
insert into Korisnik (ime, prezime, drzava, grad, email, username, password, tip_korisnika) values ("Recenzent2", "Recenzent2", "Srbija", "Novi Sad", "rencenzent2@mailinator.com", "recenzent2", "pass", 2);
insert into Korisnik (ime, prezime, drzava, grad, email, username, password, tip_korisnika) values ("Recenzent3", "Recenzent3", "Srbija", "Novi Sad", "rencenzent3@mailinator.com", "recenzent3", "pass", 2);
insert into Korisnik (ime, prezime, drzava, grad, email, username, password, tip_korisnika) values ("Recenzent4", "Recenzent4", "Srbija", "Novi Sad", "rencenzent4@mailinator.com", "recenzent4", "pass", 2);

--urednici id-9,10,11
--prvi sledeci je glavni urednik u svim casopisima (cisto nako)
insert into Korisnik (ime, prezime, drzava, grad, email, username, password, tip_korisnika) values ("Urednik1", "Urednik1", "Srbija", "Novi Sad", "urendik1@mailinator.com", "urednik1", "pass", 1);
insert into Korisnik (ime, prezime, drzava, grad, email, username, password, tip_korisnika) values ("Urednik2", "Urednik2", "Srbija", "Novi Sad", "urendik2@mailinator.com", "urednik2", "pass", 1);
insert into Korisnik (ime, prezime, drzava, grad, email, username, password, tip_korisnika) values ("Urednik3", "Urednik3", "Srbija", "Novi Sad", "urendik3@mailinator.com", "urednik3", "pass", 1);

--dodeljivanje naucne oblasti urednicima, redom: Biologija(0), Hemija(1), Matematika(2), Fizika(3)
insert into Urednik (id, naucna_oblast) values(9, 0);
insert into Urednik (id, naucna_oblast) values(10, 1);
insert into Urednik (id, naucna_oblast) values(11, 2);

insert into Recenzent (id, casopis_id) values (5,1);
insert into Recenzent (id, casopis_id) values (6,1);
insert into Recenzent (id, casopis_id) values (7,1);
insert into Recenzent (id, casopis_id) values (8,1);

--dodeljivanje casopisu 1 dva urednika(10-Hemija i 11-Mata)
insert into casopis_urednici(casopis_id, urednici_id) values (1, 10);
insert into casopis_urednici(casopis_id, urednici_id) values (1, 11);

insert into recenzenti_casopisa(casopis_id, recenzent_id) values (1,5);
insert into recenzenti_casopisa(casopis_id, recenzent_id) values (1,6);
insert into recenzenti_casopisa(casopis_id, recenzent_id) values (1,7);
insert into recenzenti_casopisa(casopis_id, recenzent_id) values (1,8);

--insert into Rad(id, naziv, apstrakt, naucna_oblast, status_rada, putanja, casopis_id, autor_id) values(1, 'Rad1','Naucni rad', 0, 1, 'C:\\Users\\uvrnu\\Desktop\\rad1.pdf',1,1);
--insert into Rad(id, naziv, apstrakt, naucna_oblast, status_rada, putanja, casopis_id, autor_id) values(2, 'Rad1','Naucni rad', 0, 1, 'C:\\Users\\uvrnu\\Desktop\\rad1.pdf',1,1);
--insert into Rad(id, naziv, apstrakt, naucna_oblast, status_rada, putanja, casopis_id, autor_id) values(3, 'Rad1','Naucni rad', 0, 1, 'C:\\Users\\uvrnu\\Desktop\\rad1.pdf',1,1);