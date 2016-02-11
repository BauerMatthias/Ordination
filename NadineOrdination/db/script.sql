create table SYMPTOM (s_id integer not null auto_increment, beschreibung varchar(300), primary key(s_id));
create table KRANKHEIT (k_id integer not null auto_increment, beschreibung varchar(300), primary key(k_id));
create table PATIENT (p_id integer not null auto_increment, vname varchar(100), nname varchar(100), adresse varchar(200), telnr varchar(100), primary key(p_id));
create table BEHANDLUNG (b_id integer not null auto_increment, p_id integer not null, datum datetime, primary key(b_id));

create table BEH_KRANK (b_id integer not null, k_id integer not null, primary key(b_id, k_id));
alter table BEH_KRANK add foreign key (b_id) references BEHANDLUNG(b_id);
alter table BEH_KRANK add foreign key (k_id) references KRANKHEIT(k_id);

create table KRANK_SYMPT (k_id integer not null, s_id integer not null, primary key(k_id, s_id));
alter table KRANK_SYMPT add foreign key (k_id) references KRANKHEIT(k_id);
alter table KRANK_SYMPT add foreign key (s_id) references SYMPTOM(s_id);

create table PAT_KRANK (p_id integer not null, k_id integer not null, primary key(p_id, k_id));
alter table PAT_KRANK add foreign key (k_id) references KRANKHEIT(k_id);
alter table PAT_KRANK add foreign key (p_id) references PATIENT(p_id);