create table KRANKHEIT (k_id integer not null auto_increment, beschreibung varchar(300), primary key(k_id));
create table PATIENT (p_id integer not null auto_increment, vname varchar(100), nname varchar(100), adresse varchar(200), gebDatum datetime, telnr varchar(100), tarif double, primary key(p_id));
create table BEHANDLUNG (b_id integer not null auto_increment, p_id integer not null, datum datetime, beh_beschreibung varchar(300), einnahme double, bemerkung varchar(200), primary key(b_id));

create table BEH_KRANK (b_id integer not null, k_id integer not null, primary key(b_id, k_id));
alter table BEH_KRANK add foreign key (b_id) references BEHANDLUNG(b_id);
alter table BEH_KRANK add foreign key (k_id) references KRANKHEIT(k_id);

create table PAT_KRANK (p_id integer not null, k_id integer not null, primary key(p_id, k_id));
alter table PAT_KRANK add foreign key (k_id) references KRANKHEIT(k_id);
alter table PAT_KRANK add foreign key (p_id) references PATIENT(p_id);