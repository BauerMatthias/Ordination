insert into patient (vname, nname, adresse, gebDatum, telnr, tarif) values ('Bettina', 'Bauer', 'Heinreichs 47, 3902 Vitis', '1967-07-15', '0680/1234567', 15.0);
insert into patient (vname, nname, adresse, gebDatum, telnr, tarif) values ('Inge', 'Prager', 'Schönfeld irgendwas...', '1963-08-14', '0664/1265567', 20.0);
insert into patient (vname, nname, adresse, gebDatum, telnr, tarif) values ('Thomas', 'Prager', 'Schönfeld irgendwas...', '1991-03-01', '0664/1265567', 10.0);
insert into patient (vname, nname, adresse, gebDatum, telnr, tarif) values ('Manuela', 'Gumpinger', 'Allentsteig irgendwas...', '1978-01-01', '0664/1265567', 15.0);

insert into krankheit (beschreibung) values ('Rückenschmerzen');
insert into krankheit (beschreibung) values ('Schulterschmerzen');
insert into krankheit (beschreibung) values ('Fußschmerzen');
insert into krankheit (beschreibung) values ('Kopfschmerzen');

insert into behandlung (p_id, datum, beh_beschreibung, einnahme, bemerkung) values(1, '2016-02-10', 'FuZo', 10.0, 'weiterhin Schmerzen vorhanden');
insert into behandlung (p_id, datum, beh_beschreibung, einnahme, bemerkung) values(2, '2016-01-08', 'Klassisch', 15.0, ' ');
insert into behandlung (p_id, datum, beh_beschreibung, einnahme, bemerkung) values(3, '2014-01-01', 'Bindegewebsmassage', 10.0, 'beschwerden vorhanden');

insert into beh_krank values(1, 3);
insert into beh_krank values(2, 1);
insert into beh_krank values(2, 4);

insert into pat_krank values(1, 1);
insert into pat_krank values(1, 2);
insert into pat_krank values(1, 4);
insert into pat_krank values(2, 2);
insert into pat_krank values(2, 3);
insert into pat_krank values(3, 1);
insert into pat_krank values(4, 1);
insert into pat_krank values(4, 3);
insert into pat_krank values(4, 4);