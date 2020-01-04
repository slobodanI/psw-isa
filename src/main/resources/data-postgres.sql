insert into student (index_number, first_name, last_name) values ('ra1-2014', 'Marko', 'Marković');
insert into student (index_number, first_name, last_name) values ('ra2-2014', 'Milan', 'Milanović');
insert into student (index_number, first_name, last_name) values ('ra3-2014', 'Ivana', 'Ivanović');
insert into student (index_number, first_name, last_name) values ('ra4-2014', 'Bojan', 'Bojanović');
insert into student (index_number, first_name, last_name) values ('ra5-2014', 'Pera', 'Perić');
insert into student (index_number, first_name, last_name) values ('ra6-2014', 'Zoran', 'Zoranović');
insert into student (index_number, first_name, last_name) values ('ra7-2014', 'Bojana', 'Bojanović');
insert into student (index_number, first_name, last_name) values ('ra8-2014', 'Milana', 'Milanović');
insert into student (index_number, first_name, last_name) values ('ra9-2014', 'Jovana', 'Jovanić');

insert into course (name) values ('Matematika');
insert into course (name) values ('Osnove programiranja');
insert into course (name) values ('Objektno programiranje');

insert into teacher (first_name, last_name) values ('Strahinja', 'Simić');
insert into teacher (first_name, last_name) values ('Marina', 'Antić');
insert into teacher (first_name, last_name) values ('Siniša', 'Branković');

insert into teaching (course_id, teacher_id) values (1, 1);
insert into teaching (course_id, teacher_id) values (1, 2);
insert into teaching (course_id, teacher_id) values (2, 2);
insert into teaching (course_id, teacher_id) values (3, 3);

insert into exam (student_id, course_id, date, grade) values (1, 1, '2016-02-01', 9);
insert into exam (student_id, course_id, date, grade) values (1, 2, '2016-04-19', 8);
insert into exam (student_id, course_id, date, grade) values (2, 1, '2016-02-01', 10);
insert into exam (student_id, course_id, date, grade) values (2, 2, '2016-04-19', 10);



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
insert into medicinska_sestra (ime, prezime, username, password, email, promenjena_lozinka, uloga, klinika_id ) values ('Lepa', 'lukic', 'LepaLukic', 'lepa123', 'lepa@gmail.com', 'true', 'MedicinskaSestra', 3);

insert into sala (naziv, zauzetost, klinika_id) values ('sala1', 'zauzeto', 1);
insert into sala (naziv, zauzetost, klinika_id) values ('sala2', 'zauzeto', 1);

insert into administrator_klinike (klinika_id, ime, prezime, username, password, email, promenjena_lozinka, uloga) values (1, 'ime1', 'prz1', 'us', 'asd', 'blabla', true, 'AdministratorKlinike' );
insert into administrator_klinike (klinika_id, ime, prezime, username, password, email, promenjena_lozinka, uloga) values (1, 'ime2', 'prz2', 'us2', 'asd', 'blabla', true, 'AdministratorKlinike' );

insert into lekar (klinika_id,tip_pregleda_id, ime, prezime,broj_ocena, ukupna_ocena, username, password, radni_kalendar, radno_vreme_od,radno_vreme_do, uloga) values (1, 1,'lekar1','prezime1',12, 55,'us3','pas12345','aaa',8,16, 'Lekar');
insert into lekar (klinika_id,tip_pregleda_id, ime, prezime,broj_ocena, ukupna_ocena, username, password, radni_kalendar, radno_vreme_od,radno_vreme_do, uloga) values (1, 2,'lekar2','prezime2',12, 50,'lekar2','lekar2','aaa',9,17, 'Lekar');
insert into lekar (klinika_id,tip_pregleda_id, ime, prezime,broj_ocena, ukupna_ocena, username, password, radni_kalendar, radno_vreme_od,radno_vreme_do, uloga) values (2, 3,'lekar3','prezime3',12, 60,'lekar3','lekar2','aaa',13,18, 'Lekar');
insert into lekar (klinika_id,tip_pregleda_id, ime, prezime,broj_ocena, ukupna_ocena, username, password, radni_kalendar, radno_vreme_od,radno_vreme_do, uloga) values (3, 1,'lekar4','prezime4',12, 59,'lekar4','lekar2','aaa',8,16, 'Lekar');
insert into lekar (klinika_id,tip_pregleda_id, ime, prezime,broj_ocena, ukupna_ocena, username, password, radni_kalendar, radno_vreme_od,radno_vreme_do, uloga) values (1, 1,'lekar5','prezime5',12, 55,'us5','pas12345','aaa',8,16, 'Lekar');

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

insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (1, 1, 1, 'info', '30-04-2019','13:40-14:10', 10, 1000,1,2, 1, true);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (2, 1, 1, 'info', '09-01-2020','13:40-14:10', 10, 1000,1,2, 1, true);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (1, 1, 1, 'info', '30-05-2019','13:00-13:30', 0, 1000,1,2, 1, true);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (1, 1, 1, '', '30-06-2019','13:00-13:30', 0, 1000,1,null, 1, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (1, 1, 1, 'info 1', '30-04-2019','13:40-14:10', 10, 1000,1,2, 1, true);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (2, 2, 2, 'info 2', '29-05-2019','13:00-13:30', 20, 1000,2,2, 1, true);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (3, 3, 3, 'info 3', '14-06-2019','13:30-14:00', 30, 1000,3,1, 1, true);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (4, 4, 4, 'info 4', '12-07-2019','12:00-12:30', 10, 1000,1,1, 2, true);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (1, 5, 5, 'info 5', '17-01-2019','13:00-13:30', 0, 1000,2,3, 2, true);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (2, 6, 6, 'info 6', '29-05-2019','10:00-10:30', 10, 1000,3,4, 2, true);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (2, 2, 2, '', '10-01-2020','10:00-11:00', 10, 1000,3,null, 2, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (2, 3, 3, '', '22-05-2019','10:00-10:30', 10, 1000,3,null, 2, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (4, 1, 1, '', '25-05-2019','10:00-10:30', 10, 1000,3,null, 2, false);

insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (2, null, null, '', '13-05-2019','10:00-10:30', 10, 1000,3,null, 2, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (2, null, null, '', '22-05-2019','10:00-10:30', 10, 1000,3,null, 2, false);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda_id, dijagnoza_id, sala_id, obavljen) values (4, null, null, '', '25-05-2019','10:00-10:30', 10, 1000,3,null, 2, false);

insert into operacija (zdravstveni_karton_id, pacijent_id, informacije, datum_operacije, satnica, cena, obavljen) values (1, 1, 'info', '30-01-2020', '12:00-14:00', 5000, false);

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

insert into lekar_odsustvo (pocetak,kraj,odobreno,tip,lekar_id) values ('2019-05-13','2019-05-20',true,1,1);
insert into lekar_odsustvo (pocetak,kraj,odobreno,tip,lekar_id) values ('2019-05-28','2019-05-30',true,1,2);
insert into lekar_odsustvo (pocetak,kraj,odobreno,tip,lekar_id) values ('2019-03-13','2019-04-20',true,1,3);

insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2019-05-20 12:00','2019-05-20 13:00',1);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2019-08-23 12:00','2019-08-23 13:00',3);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2019-05-20 09:00','2019-05-20 10:00',4);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2020-01-30 12:00','2019-05-20 13:00',1);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2020-01-30 13:00','2019-05-20 14:00',1);

insert into lekar_odsustvo (pocetak,kraj,odobreno,tip,lekar_id) values ('2019-12-13','2019-12-31',true,1,1);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2019-12-30 12:00','2019-08-23 13:00',4);
insert into zauzetost_lekara(pocetak,kraj,lekar_id) values ('2019-12-30 13:00','2019-08-23 14:00',4);

