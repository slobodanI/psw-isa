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

insert into administrator_klinickog_centra (ime, prezime, username, password, email, promenjena_lozinka, uloga ) values ('Pera', 'Peric', 'adminKC', 'adminKC', 'admin@gmail.com', 'true', 'AdministratorKlinickogCentra'); 
insert into medicinska_sestra (ime, prezime, username, password, email, promenjena_lozinka, uloga ) values ('lepa', 'lukic', 'lepa123', 'lepsi', 'blabla', 'true', 'MedicinskaSestra');

insert into lek (sifra, naziv) values ('sif1', 'naz1');
insert into lek (sifra, naziv) values ('sif2', 'naz2');

insert into klinika (naziv, adresa, opis, slobodni_termini_pregleda, broj_ocena, ukupna_ocena,prihod) values ('naz1', 'adr1', 'opis','slobodni', 5, 50,521000);

insert into sala (naziv, zauzetost, klinika_id) values ('sala1', 'zauzeto', 1);
insert into sala (naziv, zauzetost, klinika_id) values ('sala2', 'zauzeto', 1);

insert into administrator_klinike (klinika_id, ime, prezime, username, password, email, promenjena_lozinka, uloga) values (1, 'ime1', 'prz1', 'us', 'asd', 'blabla', true, 'AdministratorKlinike' );
insert into administrator_klinike (klinika_id, ime, prezime, username, password, email, promenjena_lozinka, uloga) values (1, 'ime2', 'prz2', 'us2', 'asd', 'blabla', true, 'AdministratorKlinike' );

insert into lekar (klinika_id, ime, prezime, ukupna_ocena, username, password, radni_kalendar, broj_ocena, uloga) values (1,'ime3','prezime3',60,'us3','pas3','aaa', 5, 'Lekar');

insert into pacijent (ime, prezime, username, password, email, adresa, grad, drzava, broj_tel, lbo, aktiviran_nalog, uloga) values ('Nenad', 'Nenadovic', 'pacijent', 'pacijent', 'email@email.com', 'adresa 39', 'Novi Sad', 'Srbija', '0641111111', 1234567890123, true, 'Pacijent');

insert into zdravstveni_karton (pacijent_id, krvna_grupa, dioptrija, visina, tezina, alergije, lista_bolesti) values (1, 'A', '+2', 180, 80, 'polen, kucna prasina', 'dijabetes');

insert into dijagnoza (sifra, naziv) values ('1','prvaDijagnoza');
insert into dijagnoza (sifra, naziv) values ('2','drugaDijagnoza');
insert into dijagnoza (sifra, naziv) values ('3','trecaDijagnoza');
insert into dijagnoza (sifra, naziv) values ('4','cetvrtaDijagnoza');

insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda, dijagnoza_id) values (1, 1, 1, 'info', '30-04-2019','13:40-14:10', 10, 1000, 'obican',2);
insert into pregled (lekar_id, pacijent_id, zdravstveni_karton_id, informacije, datum_pregleda, satnica, popust, cena, tip_pregleda, dijagnoza_id) values (1, 1, 1, 'info', '30-05-2019','13:00-13:30', 0, 1000, 'obican',null);

insert into recept (pregled_id, sifra_leka, naziv_leka, lbo, ime_pacijenta, prezime_pacijenta, overen, ime_medicinske_sestre) values (1, 'sif1', 'naz1', 123, 'imeP', 'prezP', false, 'medSes');
insert into recept (pregled_id, sifra_leka, naziv_leka, lbo, ime_pacijenta, prezime_pacijenta, overen, ime_medicinske_sestre) values (1, 'sif2', 'naz2', 123, 'imeP', 'prezP', false, 'medSes');

insert into obavljen_pregled (klinika_id,pacijent_id) values (1,1);

insert into poruka (id, naslov, telo, email_posiljaoca, email_primaoca, odgovoreno) values (1, 'Zahtev Mika Mikic', 'Zahtev id 1 - Mika Mikic', 'mika@gmail.com', '-', true);
insert into poruka (id, naslov, telo, email_posiljaoca, email_primaoca, odgovoreno) values (2, 'Zahtev Zika Zikic', 'Zahtev id 2 - Zika Zikic', 'zika@gmail.com', '-', true);
insert into poruka (id, naslov, telo, email_posiljaoca, email_primaoca, odgovoreno) values (3, 'Zahtev Laza Lazic', 'Zahtev id 3 - Laza Lazic', 'laza@gmail.com', '-', false);
insert into poruka (id, naslov, telo, email_posiljaoca, email_primaoca, odgovoreno) values (4, 'Zahtev Pera Peric', 'Zahtev id 4 - Pera Peric', 'pera@gmail.com', '-', false);
insert into poruka (id, naslov, telo, email_posiljaoca, email_primaoca, odgovoreno) values (5, 'Zahtev Mile Milic', 'Zahtev id 5 - Mile Milic', 'mile@gmail.com', '-', false);

