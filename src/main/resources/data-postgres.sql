insert into tip_pregleda (naziv) values ('Ocni pregled');
insert into tip_pregleda (naziv) values ('Dermatoloski pregled');
insert into tip_pregleda (naziv) values ('Stomatoloski pregled');

insert into administrator_klinickog_centra (ime, prezime, username, password, email, promenjena_lozinka, uloga ) values ('Pera', 'Peric', 'adminKC', 'adminKC', 'admin@gmail.com', 'true', 'AdministratorKlinickogCentra'); 

insert into lek (sifra, naziv) values ('05768', 'Lata');
insert into lek (sifra, naziv) values ('07492', 'Bromazepam');
insert into lek (sifra, naziv) values ('44769', 'Zylaxera');
insert into lek (sifra, naziv) values ('00668', 'Onzapin');

insert into klinika (naziv, adresa, opis, slobodni_termini_pregleda, broj_ocena, ukupna_ocena,prihod, latitude, longitude) values ('klinika1', 'Novi Sad, Ulica 1', 'opis','slobodni', 10, 50, 521000, 45.258013, 19.821040);
insert into klinika (naziv, adresa, opis, slobodni_termini_pregleda, broj_ocena, ukupna_ocena,prihod, latitude, longitude) values ('klinika2', 'Novi Sad, Ulica 1', 'opis','slobodni', 10, 47, 479000, 45.256205, 19.827348);
insert into klinika (naziv, adresa, opis, slobodni_termini_pregleda, broj_ocena, ukupna_ocena,prihod, latitude, longitude) values ('klinika3', 'Novi Sad, Ulica 1', 'opis','slobodni', 10, 45, 660000, 45.251833, 19.833976);

insert into medicinska_sestra (ime, prezime, username, password, email, promenjena_lozinka, uloga, klinika_id ) values ('Mira', 'Miric', 'MiraMiric', 'mira123', 'mira@gmail.com', 'true', 'MedicinskaSestra', 1);
insert into medicinska_sestra (ime, prezime, username, password, email, promenjena_lozinka, uloga, klinika_id ) values ('Mara', 'Maric', 'MaraMaric', 'mara123', 'mara@gmail.com', 'true', 'MedicinskaSestra', 2);
insert into medicinska_sestra (ime, prezime, username, password, email, promenjena_lozinka, uloga, klinika_id ) values ('Lepa', 'lukic', 'LepaLukic', 'lepa123', 'lepa@gmail.com', 'false', 'MedicinskaSestra', 3);

insert into sala (naziv, klinika_id) values ('sala1', 1);
insert into sala (naziv, klinika_id) values ('sala2', 1);
insert into sala (naziv, klinika_id) values ('sala3', 2);
insert into sala (naziv, klinika_id) values ('sala4', 2);
insert into sala (naziv, klinika_id) values ('sala5', 3);

insert into zauzetost_sala(pocetak,kraj,sala_id) values ('2020-05-20 12:00','2020-05-20 13:00',1);
insert into zauzetost_sala(pocetak,kraj,sala_id) values ('2020-05-20 15:00','2020-05-20 16:00',1);
insert into zauzetost_sala(pocetak,kraj,sala_id) values ('2020-05-25 11:00','2020-05-25 12:00',1);
insert into zauzetost_sala(pocetak,kraj,sala_id) values ('2020-05-25 11:00','2020-05-25 12:00',2);


insert into administrator_klinike (klinika_id, ime, prezime, username, password, email, promenjena_lozinka, uloga) values (1, 'ime1', 'prz1', 'us', 'asd', 'ak1@gmail.com', true, 'AdministratorKlinike' );
insert into administrator_klinike (klinika_id, ime, prezime, username, password, email, promenjena_lozinka, uloga) values (1, 'ime2', 'prz2', 'us2', 'asd', 'ak2@gmail.com', false, 'AdministratorKlinike' );

insert into lekar (klinika_id,tip_pregleda_id, ime, prezime,broj_ocena, ukupna_ocena, username, password, email, radni_kalendar, radno_vreme_od,radno_vreme_do, uloga, promenjena_lozinka) values (1, 1,'lekar1','prezime1',12, 55,'us3','pas12345','lekar1@gmail.com','aaa',8,16, 'Lekar',true);
insert into lekar (klinika_id,tip_pregleda_id, ime, prezime,broj_ocena, ukupna_ocena, username, password, email, radni_kalendar, radno_vreme_od,radno_vreme_do, uloga, promenjena_lozinka) values (1, 2,'lekar2','prezime2',12, 50,'lekar2','lekar2','lekar2@gmail.com','aaa',9,17, 'Lekar',true);
insert into lekar (klinika_id,tip_pregleda_id, ime, prezime,broj_ocena, ukupna_ocena, username, password, email, radni_kalendar, radno_vreme_od,radno_vreme_do, uloga, promenjena_lozinka) values (2, 3,'lekar3','prezime3',12, 60,'lekar3','lekar2','lekar3@gmail.com','aaa',13,18, 'Lekar',true);
insert into lekar (klinika_id,tip_pregleda_id, ime, prezime,broj_ocena, ukupna_ocena, username, password, email, radni_kalendar, radno_vreme_od,radno_vreme_do, uloga, promenjena_lozinka) values (3, 1,'lekar4','prezime4',12, 59,'lekar4','lekar2','lekar4@gmail.com','aaa',8,16, 'Lekar',true);
insert into lekar (klinika_id,tip_pregleda_id, ime, prezime,broj_ocena, ukupna_ocena, username, password, email, radni_kalendar, radno_vreme_od,radno_vreme_do, uloga, promenjena_lozinka) values (1, 1,'lekar5','prezime5',12, 55,'us5','pas12345','lekar5@gmail.com','aaa',8,16, 'Lekar',false);

insert into pacijent (ime, prezime, username, password, email, adresa, grad, drzava, broj_tel, lbo, aktiviran_nalog, uloga) values ('Nenad', 'Nenadovic', 'pacijent', 'pacijent', 'nenad@gmail.com', 'adresa 39', 'Novi Sad', 'Srbija', '0641111111', 1234567890123, true, 'Pacijent');
insert into pacijent (ime, prezime, username, password, email, adresa, grad, drzava, broj_tel, lbo, aktiviran_nalog, uloga) values ('Mika', 'Mikic', 'username1', 'pass1', 'mika@gmail.com', 'adr1', 'grad', 'drzava', '021', 123, true, 'Pacijent');
insert into pacijent (ime, prezime, username, password, email, adresa, grad, drzava, broj_tel, lbo, aktiviran_nalog, uloga) values ('Zika', 'Zikic', 'username2', 'pass2', 'zika@gmail.com', 'adr2', 'grad', 'drzava', '021', 234, true, 'Pacijent');
insert into pacijent (ime, prezime, username, password, email, adresa, grad, drzava, broj_tel, lbo, aktiviran_nalog, uloga) values ('Laza', 'Lazic', 'username3', 'pass3', 'laza@gmail.com', 'adr3', 'grad', 'drzava', '021', 345, false, 'Pacijent');
insert into pacijent (ime, prezime, username, password, email, adresa, grad, drzava, broj_tel, lbo, aktiviran_nalog, uloga) values ('Pera', 'Peric', 'username4', 'pass4', 'pera@gmail.com', 'adr4', 'grad', 'drzava', '021', 456, false, 'Pacijent');
insert into pacijent (ime, prezime, username, password, email, adresa, grad, drzava, broj_tel, lbo, aktiviran_nalog, uloga) values ('Mile', 'Milic', 'username5', 'pass5', 'mile@gmail.com', 'adr5', 'grad', 'drzava', '021', 567, false, 'Pacijent');

insert into zdravstveni_karton (pacijent_id, krvna_grupa, dioptrija, visina, tezina, alergije, lista_bolesti) values (1, 'A', '+2', 180, 80, 'polen, kucna prasina', 'dijabetes');
insert into zdravstveni_karton (pacijent_id, krvna_grupa, dioptrija, visina, tezina, alergije, lista_bolesti) values (2, 'B', '+3', 156, 80, 'nema', 'nema');
insert into zdravstveni_karton (pacijent_id, krvna_grupa, dioptrija, visina, tezina, alergije, lista_bolesti) values (3, 'O', '+1', 165, 80, 'polen', 'nema');
insert into zdravstveni_karton (pacijent_id, krvna_grupa, dioptrija, visina, tezina, alergije, lista_bolesti) values (4, 'AB', '+1', 192, 80, 'lek1', 'nema');
insert into zdravstveni_karton (pacijent_id, krvna_grupa, dioptrija, visina, tezina, alergije, lista_bolesti) values (5, 'A', '+2', 179, 80, 'nema', 'nema');
insert into zdravstveni_karton (pacijent_id, krvna_grupa, dioptrija, visina, tezina, alergije, lista_bolesti) values (6, 'A', '+2', 189, 87, 'nema', 'nema');

insert into dijagnoza (sifra, naziv) values ('556','prvaDijagnoza');
insert into dijagnoza (sifra, naziv) values ('778','drugaDijagnoza');
insert into dijagnoza (sifra, naziv) values ('632','trecaDijagnoza');
insert into dijagnoza (sifra, naziv) values ('114','cetvrtaDijagnoza');


insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (1, 1, 1, 'info', '2019-04-30 13:40', '2019-04-30 14:10', 10, 1000,1,2, 1, true, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (2, 1, 1, 'info', '2019-05-30 13:40','2019-05-30 14:10', 10, 1000,1,2, 1, true, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (1, 1, 1, 'info', '2019-06-30 13:00','2019-06-30 13:30', 0, 1000,1,2, 1, true, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (1, 1, 1, '', '2019-08-30 13:00','2019-08-30 13:30', 0, 1000,1,null, 1, false, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (1, 1, 1, 'info 1', '2019-07-30 13:40','2019-07-30 14:10', 10, 1000,1,2, 1, true, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (2, 2, 2, 'info 2', '2019-05-29 13:00','2019-05-29 13:30', 20, 1000,2,2, 1, true, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (3, 3, 3, 'info 3', '2019-06-14 13:30','2019-06-14 14:00', 30, 1000,3,1, 1, true, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (4, 4, 4, 'info 4', '2019-07-12 12:00','2019-07-12 12:30', 10, 1000,1,1, 2, true, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (1, 5, 5, 'info 5', '2019-01-17 13:00','2019-01-17 13:30', 0, 1000,2,3, 2, true, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (2, 6, 6, 'info 6', '2019-05-29 10:00','2019-05-29 10:30', 10, 1000,3,4, 2, true, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (2, 2, 2, '', '2019-05-13 10:00','2019-05-13 10:30', 10, 1000,2,null, 2, false, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (2, 3, 3, '', '2019-05-22 10:00','2019-05-22 10:30', 10, 1000,2,null, 2, false, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (4, 1, 1, '', '2019-05-25 10:00','2019-05-25 10:30', 10, 1000,2,null, 2, false, true, false);

insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (2, null, null, '', '2020-05-13 10:00','2020-05-13 10:30', 10, 1000,3,null, 2, false, false, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (2, null, null, '', '2019-05-22 10:00','2019-05-22 10:30', 10, 1000,3,null, 2, false, false, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (4, null, null, '', '2019-05-25 10:00','2019-05-25 10:30', 10, 1000,3,null, 2, false, false, false);
--treba mi ovaj pregled za testove
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (2, 1, 1, '', '2020-05-13 11:00','2020-05-13 11:30', 10, 1000,3,null, 2, false, true, true);
--i ovaj
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (2, 1, 1, '', '2020-05-13 12:00','2020-05-13 12:30', 10, 1000,3,null, 2, false, false, false);
--zakazani pregledi, koje otkazujem
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (1, 1, 1, '', '2020-04-30 13:00', '2020-04-30 14:00', 10, 1000,1,null, 1, false, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (1, 1, 1, '', '2020-04-30 14:00', '2020-04-30 15:00', 10, 1000,1,null, 1, false, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (1, 1, 1, '', '2020-02-07 12:00', '2020-02-07 13:00', 10, 1000,1,null, 1, false, true, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (1, 1, 1, '', '2020-05-25 11:00','2020-05-25 11:30', 10, 1000,1,null, null, false,false,false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda_od,datum_pregleda_do, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen, prihvacen, obrisan) values (1, 1, 1, '', '2021-05-25 11:00','2021-05-25 12:00', 10, 1000,1,null, null, false,false,false);

insert into operacija (zdravstveni_karton_id, pacijent_id, informacije, datum_operacije_od,datum_operacije_do, cena, obavljen) values (1, 1, 'info', '2020-01-30 12:00','2020-01-30 14:00', 5000, true);

insert into lekar_operacija (operacija_id, lekar_id) values (1, 1);
insert into lekar_operacija (operacija_id, lekar_id) values (1, 2);

insert into recept (pregled_id, sifra_leka, naziv_leka, lbo, ime_pacijenta, prezime_pacijenta, overen, medicinska_sestra_id) values (1, 'sif1', 'naz1', 123, 'imeP', 'prezP', false, null);
insert into recept (pregled_id, sifra_leka, naziv_leka, lbo, ime_pacijenta, prezime_pacijenta, overen, medicinska_sestra_id) values (1, 'sif2', 'naz2', 123, 'imeP', 'prezP', false, null);

insert into obavljen_pregled (klinika_id,pacijent_id) values (1,1);
insert into obavljen_pregled (klinika_id,pacijent_id) values (2,2);
insert into obavljen_pregled (klinika_id,pacijent_id) values (3,3);
insert into obavljen_pregled (klinika_id,pacijent_id) values (2,1);
insert into obavljen_pregled (klinika_id,pacijent_id) values (3,1);
insert into obavljen_pregled (klinika_id,pacijent_id) values (3,2);

insert into poruka (pacijent_id, naslov, telo, email_posiljaoca, email_primaoca, odgovoreno) values (1, 'Zahtev Nenad Nenadovic', 'Zahtev id 1 - Nenad Nenadovic', 'nenad@gmail.com', '-', true);
insert into poruka (pacijent_id, naslov, telo, email_posiljaoca, email_primaoca, odgovoreno) values (2, 'Zahtev Mika Mikic', 'Zahtev id 2 - Mika Mikic', 'mika@gmail.com', '-', true);
insert into poruka (pacijent_id, naslov, telo, email_posiljaoca, email_primaoca, odgovoreno) values (3, 'Zahtev Zika Zikic', 'Zahtev id 3 - Zika Zikic', 'zika@gmail.com', '-', true);
insert into poruka (pacijent_id, naslov, telo, email_posiljaoca, email_primaoca, odgovoreno) values (4, 'Zahtev Laza Lazic', 'Zahtev id 4 - Laza Lazic', 'laza@gmail.com', '-', false);
insert into poruka (pacijent_id, naslov, telo, email_posiljaoca, email_primaoca, odgovoreno) values (5, 'Zahtev Pera Peric', 'Zahtev id 5 - Pera Peric', 'pera@gmail.com', '-', false);
insert into poruka (pacijent_id, naslov, telo, email_posiljaoca, email_primaoca, odgovoreno) values (6, 'Zahtev Mile Milic', 'Zahtev id 6 - Mile Milic', 'mile@gmail.com', '-', false);

insert into lekar_odsustvo (pocetak,kraj,odobreno,tip,lekar_id) values ('2019-05-13','2019-05-20',null,1,1);
insert into lekar_odsustvo (pocetak,kraj,odobreno,tip,lekar_id) values ('2019-05-28','2019-05-30',null,1,2);
insert into lekar_odsustvo (pocetak,kraj,odobreno,tip,lekar_id) values ('2019-03-13','2019-04-20',null,1,3);

insert into odsustvo (pocetak,kraj,odobreno,tip,med_sestra_id) values ('2019-03-13','2019-04-20',null,1,1);
insert into odsustvo (pocetak,kraj,odobreno,tip,med_sestra_id) values ('2019-04-13','2019-05-20',null,1,2);

insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2019-05-20 12:00','2019-05-20 13:00',1);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2019-08-23 12:00','2019-08-23 13:00',3);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2019-05-20 09:00','2019-05-20 10:00',4);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2020-01-30 12:00','2020-01-30 13:00',1);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2020-01-30 13:00','2020-01-30 14:00',1);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2020-02-28 13:00','2020-02-28 14:00',1);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2020-02-28 14:00','2020-02-28 15:00',1);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2020-02-28 11:00','2020-02-28 12:20',1);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2020-05-25 10:00','2020-05-25 12:00',1);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2021-05-25 11:00','2021-05-25 12:00',1);

--kada otkazem pregled ovo treba izbrisati
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2020-04-30 13:00', '2020-04-30 14:00',1);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2020-04-30 14:00', '2020-04-30 15:00',1);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2020-02-07 12:00', '2020-02-07 13:00',1);

insert into lekar_odsustvo (pocetak,kraj,odobreno,tip,lekar_id) values ('2019-12-13','2019-12-31',true,1,1);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2019-12-30 12:00','2019-08-23 13:00',4);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2019-12-30 13:00','2019-08-23 14:00',4);

insert into cenovnik(tip_pregleda_id,cena,klinika_id) values (1,1000,1);
insert into cenovnik(tip_pregleda_id,cena,klinika_id) values (2,2000,1);
insert into cenovnik(tip_pregleda_id,cena,klinika_id) values (3,3000,1);

insert into cenovnik(tip_pregleda_id,cena,klinika_id) values (1,1000,2);
insert into cenovnik(tip_pregleda_id,cena,klinika_id) values (2,2000,2);
insert into cenovnik(tip_pregleda_id,cena,klinika_id) values (3,3000,2);

insert into cenovnik(tip_pregleda_id,cena,klinika_id) values (1,1000,3);
insert into cenovnik(tip_pregleda_id,cena,klinika_id) values (2,2000,3);
insert into cenovnik(tip_pregleda_id,cena,klinika_id) values (3,3000,3);
