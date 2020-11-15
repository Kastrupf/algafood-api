create table autorisation (id bigint not null auto_increment, description varchar(255) not null, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table cuisine (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table groupe (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table groupe_autorisation (groupe_id bigint not null, autorisation_id bigint not null) engine=InnoDB
create table moyen_de_payment (id bigint not null auto_increment, description varchar(255) not null, primary key (id)) engine=InnoDB
create table produit (id bigint not null auto_increment, actif bit not null, description varchar(255) not null, nom varchar(255) not null, prix decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table region (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table restaurant (id bigint not null auto_increment, adresse_code_postal varchar(255), adresse_complement varchar(255), adresse_numero varchar(255), adresse_voie varchar(255), date_mise_a_jour datetime(6) not null, date_registre datetime(6) not null, frais_transport decimal(19,2) not null, nom varchar(255) not null, adresse_ville_id bigint, cuisine_id bigint not null, primary key (id)) engine=InnoDB
create table restaurant_moyen_de_payment (restaurant_id bigint not null, moyen_de_payment_id bigint not null) engine=InnoDB
create table utilisateur (id bigint not null auto_increment, date_creation datetime(6) not null, email varchar(255) not null, mot_de_passe varchar(255) not null, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table utilisateur_groupe (utilisateur_id bigint not null, groupe_id bigint not null) engine=InnoDB
create table ville (id bigint not null auto_increment, nom varchar(255) not null, region_id bigint not null, primary key (id)) engine=InnoDB
alter table groupe_autorisation add constraint FKj22hcdqb5g6et1dtpfv7ged7a foreign key (autorisation_id) references autorisation (id)
alter table groupe_autorisation add constraint FKixks4boccfspkwethjpvufsvq foreign key (groupe_id) references groupe (id)
alter table produit add constraint FKcw6nx1j083cgy5f50xuq1qyy8 foreign key (restaurant_id) references restaurant (id)
alter table restaurant add constraint FKlsseceu7am3v7uktdu5oks792 foreign key (adresse_ville_id) references ville (id)
alter table restaurant add constraint FKa1mopooywwfnvq23on35n6xdm foreign key (cuisine_id) references cuisine (id)
alter table restaurant_moyen_de_payment add constraint FKt0by5lix7px4q4fh3av3psr1f foreign key (moyen_de_payment_id) references moyen_de_payment (id)
alter table restaurant_moyen_de_payment add constraint FKi7bscp75y2cnek7r2bt0fgyjy foreign key (restaurant_id) references restaurant (id)
alter table utilisateur_groupe add constraint FKl296pow140muh5t3xuvyg5glr foreign key (groupe_id) references groupe (id)
alter table utilisateur_groupe add constraint FKhqoflviid3rxk7nji5pj5578x foreign key (utilisateur_id) references utilisateur (id)
alter table ville add constraint FKrxxss6tb7mtrux2io14r3wygf foreign key (region_id) references region (id)
insert into cuisine (nom) values ('Italienne')
insert into cuisine (nom) values ('Indienne')
insert into cuisine (nom) values ('Française')
insert into cuisine (nom) values ('Brésiliene')
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('La Bicha', 3.50, 3, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('PraiadoPepê', 2.66, 4, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('Shakira', 3.56, 2, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('Woko', 0.00, 1, utc_timestamp, utc_timestamp)
insert into moyen_de_payment (description) values ('carte')
insert into moyen_de_payment (description) values ('virement')
insert into moyen_de_payment (description) values ('ticket prépayé')
insert into restaurant_moyen_de_payment (restaurant_id, moyen_de_payment_id) values (1, 1), (1, 2), (1, 3), (2, 1), (3, 2), (3, 3)
insert into region (nom) values ('Nouvelle Aquitaine')
insert into region (nom) values ('Bourgogne')
insert into ville (nom, region_id) values ('Bordeaux', 1)
insert into ville (nom, region_id) values ('Dijon', 2)
insert into ville (nom, region_id) values ('Beaune', 2)
insert into groupe (nom) values ('user')
insert into groupe (nom) values ('admin')
insert into autorisation (nom, description) values ('lire','permission pour lecture' )
insert into autorisation (nom, description) values ('editer', 'permission pour edition')
insert into groupe_autorisation (groupe_id, autorisation_id) values (1, 1), (2, 1), (2, 2)
insert into utilisateur (nom, email, mot_de_passe, date_creation) values ('Fernanda', 'fernanda@email.com', 'Test1234', utc_timestamp)
insert into utilisateur (nom, email, mot_de_passe, date_creation) values ('Lucca', 'lucca@email.com', 'Test1234', utc_timestamp)
insert into utilisateur_groupe (utilisateur_id, groupe_id) values (1, 1), (1, 2), (2, 2)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Porc', 'Roti de porc fumé', 78.90, 1, 3)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Soupe', 'Soupe de légumes', 6.55, 1, 2)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Viande', 'Picanha ', 20.50, 1, 4)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Pizza', 'Reine ', 5.50, 1, 1)
create table autorisation (id bigint not null auto_increment, description varchar(255) not null, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table cuisine (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table groupe (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table groupe_autorisation (groupe_id bigint not null, autorisation_id bigint not null) engine=InnoDB
create table moyen_de_payment (id bigint not null auto_increment, description varchar(255) not null, primary key (id)) engine=InnoDB
create table produit (id bigint not null auto_increment, actif bit not null, description varchar(255) not null, nom varchar(255) not null, prix decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table region (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table restaurant (id bigint not null auto_increment, adresse_code_postal varchar(255), adresse_complement varchar(255), adresse_numero varchar(255), adresse_voie varchar(255), date_mise_a_jour datetime(6) not null, date_registre datetime(6) not null, frais_transport decimal(19,2) not null, nom varchar(255) not null, adresse_ville_id bigint, cuisine_id bigint not null, primary key (id)) engine=InnoDB
create table restaurant_moyen_de_payment (restaurant_id bigint not null, moyen_de_payment_id bigint not null) engine=InnoDB
create table utilisateur (id bigint not null auto_increment, date_creation datetime(6) not null, email varchar(255) not null, mot_de_passe varchar(255) not null, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table utilisateur_groupe (utilisateur_id bigint not null, groupe_id bigint not null) engine=InnoDB
create table ville (id bigint not null auto_increment, nom varchar(255) not null, region_id bigint not null, primary key (id)) engine=InnoDB
alter table groupe_autorisation add constraint FKj22hcdqb5g6et1dtpfv7ged7a foreign key (autorisation_id) references autorisation (id)
alter table groupe_autorisation add constraint FKixks4boccfspkwethjpvufsvq foreign key (groupe_id) references groupe (id)
alter table produit add constraint FKcw6nx1j083cgy5f50xuq1qyy8 foreign key (restaurant_id) references restaurant (id)
alter table restaurant add constraint FKlsseceu7am3v7uktdu5oks792 foreign key (adresse_ville_id) references ville (id)
alter table restaurant add constraint FKa1mopooywwfnvq23on35n6xdm foreign key (cuisine_id) references cuisine (id)
alter table restaurant_moyen_de_payment add constraint FKt0by5lix7px4q4fh3av3psr1f foreign key (moyen_de_payment_id) references moyen_de_payment (id)
alter table restaurant_moyen_de_payment add constraint FKi7bscp75y2cnek7r2bt0fgyjy foreign key (restaurant_id) references restaurant (id)
alter table utilisateur_groupe add constraint FKl296pow140muh5t3xuvyg5glr foreign key (groupe_id) references groupe (id)
alter table utilisateur_groupe add constraint FKhqoflviid3rxk7nji5pj5578x foreign key (utilisateur_id) references utilisateur (id)
alter table ville add constraint FKrxxss6tb7mtrux2io14r3wygf foreign key (region_id) references region (id)
insert into cuisine (nom) values ('Italienne')
insert into cuisine (nom) values ('Indienne')
insert into cuisine (nom) values ('Française')
insert into cuisine (nom) values ('Brésiliene')
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('La Bicha', 3.50, 3, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('PraiadoPepê', 2.66, 4, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('Shakira', 3.56, 2, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('Woko', 0.00, 1, utc_timestamp, utc_timestamp)
insert into moyen_de_payment (description) values ('carte')
insert into moyen_de_payment (description) values ('virement')
insert into moyen_de_payment (description) values ('ticket prépayé')
insert into restaurant_moyen_de_payment (restaurant_id, moyen_de_payment_id) values (1, 1), (1, 2), (1, 3), (2, 1), (3, 2), (3, 3)
insert into region (nom) values ('Nouvelle Aquitaine')
insert into region (nom) values ('Bourgogne')
insert into ville (nom, region_id) values ('Bordeaux', 1)
insert into ville (nom, region_id) values ('Dijon', 2)
insert into ville (nom, region_id) values ('Beaune', 2)
insert into groupe (nom) values ('user')
insert into groupe (nom) values ('admin')
insert into autorisation (nom, description) values ('lire','permission pour lecture' )
insert into autorisation (nom, description) values ('editer', 'permission pour edition')
insert into groupe_autorisation (groupe_id, autorisation_id) values (1, 1), (2, 1), (2, 2)
insert into utilisateur (nom, email, mot_de_passe, date_creation) values ('Fernanda', 'fernanda@email.com', 'Test1234', utc_timestamp)
insert into utilisateur (nom, email, mot_de_passe, date_creation) values ('Lucca', 'lucca@email.com', 'Test1234', utc_timestamp)
insert into utilisateur_groupe (utilisateur_id, groupe_id) values (1, 1), (1, 2), (2, 2)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Porc', 'Roti de porc fumé', 78.90, 1, 3)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Soupe', 'Soupe de légumes', 6.55, 1, 2)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Viande', 'Picanha ', 20.50, 1, 4)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Pizza', 'Reine ', 5.50, 1, 1)
create table autorisation (id bigint not null auto_increment, description varchar(255) not null, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table cuisine (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table groupe (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table groupe_autorisation (groupe_id bigint not null, autorisation_id bigint not null) engine=InnoDB
create table moyen_de_payment (id bigint not null auto_increment, description varchar(255) not null, primary key (id)) engine=InnoDB
create table produit (id bigint not null auto_increment, actif bit not null, description varchar(255) not null, nom varchar(255) not null, prix decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table region (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table restaurant (id bigint not null auto_increment, adresse_code_postal varchar(255), adresse_complement varchar(255), adresse_numero varchar(255), adresse_voie varchar(255), date_mise_a_jour datetime(6) not null, date_registre datetime(6) not null, frais_transport decimal(19,2) not null, nom varchar(255) not null, adresse_ville_id bigint, cuisine_id bigint not null, primary key (id)) engine=InnoDB
create table restaurant_moyen_de_payment (restaurant_id bigint not null, moyen_de_payment_id bigint not null) engine=InnoDB
create table utilisateur (id bigint not null auto_increment, date_creation datetime(6) not null, email varchar(255) not null, mot_de_passe varchar(255) not null, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table utilisateur_groupe (utilisateur_id bigint not null, groupe_id bigint not null) engine=InnoDB
create table ville (id bigint not null auto_increment, nom varchar(255) not null, region_id bigint not null, primary key (id)) engine=InnoDB
alter table groupe_autorisation add constraint FKj22hcdqb5g6et1dtpfv7ged7a foreign key (autorisation_id) references autorisation (id)
alter table groupe_autorisation add constraint FKixks4boccfspkwethjpvufsvq foreign key (groupe_id) references groupe (id)
alter table produit add constraint FKcw6nx1j083cgy5f50xuq1qyy8 foreign key (restaurant_id) references restaurant (id)
alter table restaurant add constraint FKlsseceu7am3v7uktdu5oks792 foreign key (adresse_ville_id) references ville (id)
alter table restaurant add constraint FKa1mopooywwfnvq23on35n6xdm foreign key (cuisine_id) references cuisine (id)
alter table restaurant_moyen_de_payment add constraint FKt0by5lix7px4q4fh3av3psr1f foreign key (moyen_de_payment_id) references moyen_de_payment (id)
alter table restaurant_moyen_de_payment add constraint FKi7bscp75y2cnek7r2bt0fgyjy foreign key (restaurant_id) references restaurant (id)
alter table utilisateur_groupe add constraint FKl296pow140muh5t3xuvyg5glr foreign key (groupe_id) references groupe (id)
alter table utilisateur_groupe add constraint FKhqoflviid3rxk7nji5pj5578x foreign key (utilisateur_id) references utilisateur (id)
alter table ville add constraint FKrxxss6tb7mtrux2io14r3wygf foreign key (region_id) references region (id)
insert into cuisine (nom) values ('Italienne')
insert into cuisine (nom) values ('Indienne')
insert into cuisine (nom) values ('Française')
insert into cuisine (nom) values ('Brésiliene')
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('La Bicha', 3.50, 3, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('PraiadoPepê', 2.66, 4, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('Shakira', 3.56, 2, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('Woko', 0.00, 1, utc_timestamp, utc_timestamp)
insert into moyen_de_payment (description) values ('carte')
insert into moyen_de_payment (description) values ('virement')
insert into moyen_de_payment (description) values ('ticket prépayé')
insert into restaurant_moyen_de_payment (restaurant_id, moyen_de_payment_id) values (1, 1), (1, 2), (1, 3), (2, 1), (3, 2), (3, 3)
insert into region (nom) values ('Nouvelle Aquitaine')
insert into region (nom) values ('Bourgogne')
insert into ville (nom, region_id) values ('Bordeaux', 1)
insert into ville (nom, region_id) values ('Dijon', 2)
insert into ville (nom, region_id) values ('Beaune', 2)
insert into groupe (nom) values ('user')
insert into groupe (nom) values ('admin')
insert into autorisation (nom, description) values ('lire','permission pour lecture' )
insert into autorisation (nom, description) values ('editer', 'permission pour edition')
insert into groupe_autorisation (groupe_id, autorisation_id) values (1, 1), (2, 1), (2, 2)
insert into utilisateur (nom, email, mot_de_passe, date_creation) values ('Fernanda', 'fernanda@email.com', 'Test1234', utc_timestamp)
insert into utilisateur (nom, email, mot_de_passe, date_creation) values ('Lucca', 'lucca@email.com', 'Test1234', utc_timestamp)
insert into utilisateur_groupe (utilisateur_id, groupe_id) values (1, 1), (1, 2), (2, 2)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Porc', 'Roti de porc fumé', 78.90, 1, 3)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Soupe', 'Soupe de légumes', 6.55, 1, 2)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Viande', 'Picanha ', 20.50, 1, 4)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Pizza', 'Reine ', 5.50, 1, 1)
create table autorisation (id bigint not null auto_increment, description varchar(255) not null, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table cuisine (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table groupe (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table groupe_autorisation (groupe_id bigint not null, autorisation_id bigint not null) engine=InnoDB
create table moyen_de_payment (id bigint not null auto_increment, description varchar(255) not null, primary key (id)) engine=InnoDB
create table produit (id bigint not null auto_increment, actif bit not null, description varchar(255) not null, nom varchar(255) not null, prix decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table region (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table restaurant (id bigint not null auto_increment, adresse_code_postal varchar(255), adresse_complement varchar(255), adresse_numero varchar(255), adresse_voie varchar(255), date_mise_a_jour datetime(6) not null, date_registre datetime(6) not null, frais_transport decimal(19,2) not null, nom varchar(255) not null, adresse_ville_id bigint, cuisine_id bigint not null, primary key (id)) engine=InnoDB
create table restaurant_moyen_de_payment (restaurant_id bigint not null, moyen_de_payment_id bigint not null) engine=InnoDB
create table utilisateur (id bigint not null auto_increment, date_creation datetime(6) not null, email varchar(255) not null, mot_de_passe varchar(255) not null, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table utilisateur_groupe (utilisateur_id bigint not null, groupe_id bigint not null) engine=InnoDB
create table ville (id bigint not null auto_increment, nom varchar(255) not null, region_id bigint not null, primary key (id)) engine=InnoDB
alter table groupe_autorisation add constraint FKj22hcdqb5g6et1dtpfv7ged7a foreign key (autorisation_id) references autorisation (id)
alter table groupe_autorisation add constraint FKixks4boccfspkwethjpvufsvq foreign key (groupe_id) references groupe (id)
alter table produit add constraint FKcw6nx1j083cgy5f50xuq1qyy8 foreign key (restaurant_id) references restaurant (id)
alter table restaurant add constraint FKlsseceu7am3v7uktdu5oks792 foreign key (adresse_ville_id) references ville (id)
alter table restaurant add constraint FKa1mopooywwfnvq23on35n6xdm foreign key (cuisine_id) references cuisine (id)
alter table restaurant_moyen_de_payment add constraint FKt0by5lix7px4q4fh3av3psr1f foreign key (moyen_de_payment_id) references moyen_de_payment (id)
alter table restaurant_moyen_de_payment add constraint FKi7bscp75y2cnek7r2bt0fgyjy foreign key (restaurant_id) references restaurant (id)
alter table utilisateur_groupe add constraint FKl296pow140muh5t3xuvyg5glr foreign key (groupe_id) references groupe (id)
alter table utilisateur_groupe add constraint FKhqoflviid3rxk7nji5pj5578x foreign key (utilisateur_id) references utilisateur (id)
alter table ville add constraint FKrxxss6tb7mtrux2io14r3wygf foreign key (region_id) references region (id)
insert into cuisine (nom) values ('Italienne')
insert into cuisine (nom) values ('Indienne')
insert into cuisine (nom) values ('Française')
insert into cuisine (nom) values ('Brésiliene')
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('La Bicha', 3.50, 3, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('PraiadoPepê', 2.66, 4, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('Shakira', 3.56, 2, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('Woko', 0.00, 1, utc_timestamp, utc_timestamp)
insert into moyen_de_payment (description) values ('carte')
insert into moyen_de_payment (description) values ('virement')
insert into moyen_de_payment (description) values ('ticket prépayé')
insert into restaurant_moyen_de_payment (restaurant_id, moyen_de_payment_id) values (1, 1), (1, 2), (1, 3), (2, 1), (3, 2), (3, 3)
insert into region (nom) values ('Nouvelle Aquitaine')
insert into region (nom) values ('Bourgogne')
insert into ville (nom, region_id) values ('Bordeaux', 1)
insert into ville (nom, region_id) values ('Dijon', 2)
insert into ville (nom, region_id) values ('Beaune', 2)
insert into groupe (nom) values ('user')
insert into groupe (nom) values ('admin')
insert into autorisation (nom, description) values ('lire','permission pour lecture' )
insert into autorisation (nom, description) values ('editer', 'permission pour edition')
insert into groupe_autorisation (groupe_id, autorisation_id) values (1, 1), (2, 1), (2, 2)
insert into utilisateur (nom, email, mot_de_passe, date_creation) values ('Fernanda', 'fernanda@email.com', 'Test1234', utc_timestamp)
insert into utilisateur (nom, email, mot_de_passe, date_creation) values ('Lucca', 'lucca@email.com', 'Test1234', utc_timestamp)
insert into utilisateur_groupe (utilisateur_id, groupe_id) values (1, 1), (1, 2), (2, 2)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Porc', 'Roti de porc fumé', 78.90, 1, 3)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Soupe', 'Soupe de légumes', 6.55, 1, 2)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Viande', 'Picanha ', 20.50, 1, 4)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Pizza', 'Reine ', 5.50, 1, 1)
create table autorisation (id bigint not null auto_increment, description varchar(255) not null, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table cuisine (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table groupe (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table groupe_autorisation (groupe_id bigint not null, autorisation_id bigint not null) engine=InnoDB
create table moyen_de_payment (id bigint not null auto_increment, description varchar(255) not null, primary key (id)) engine=InnoDB
create table produit (id bigint not null auto_increment, actif bit not null, description varchar(255) not null, nom varchar(255) not null, prix decimal(19,2) not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table region (id bigint not null auto_increment, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table restaurant (id bigint not null auto_increment, adresse_code_postal varchar(255), adresse_complement varchar(255), adresse_numero varchar(255), adresse_voie varchar(255), date_mise_a_jour datetime(6) not null, date_registre datetime(6) not null, frais_transport decimal(19,2) not null, nom varchar(255) not null, adresse_ville_id bigint, cuisine_id bigint not null, primary key (id)) engine=InnoDB
create table restaurant_moyen_de_payment (restaurant_id bigint not null, moyen_de_payment_id bigint not null) engine=InnoDB
create table utilisateur (id bigint not null auto_increment, date_creation datetime(6) not null, email varchar(255) not null, mot_de_passe varchar(255) not null, nom varchar(255) not null, primary key (id)) engine=InnoDB
create table utilisateur_groupe (utilisateur_id bigint not null, groupe_id bigint not null) engine=InnoDB
create table ville (id bigint not null auto_increment, nom varchar(255) not null, region_id bigint not null, primary key (id)) engine=InnoDB
alter table groupe_autorisation add constraint FKj22hcdqb5g6et1dtpfv7ged7a foreign key (autorisation_id) references autorisation (id)
alter table groupe_autorisation add constraint FKixks4boccfspkwethjpvufsvq foreign key (groupe_id) references groupe (id)
alter table produit add constraint FKcw6nx1j083cgy5f50xuq1qyy8 foreign key (restaurant_id) references restaurant (id)
alter table restaurant add constraint FKlsseceu7am3v7uktdu5oks792 foreign key (adresse_ville_id) references ville (id)
alter table restaurant add constraint FKa1mopooywwfnvq23on35n6xdm foreign key (cuisine_id) references cuisine (id)
alter table restaurant_moyen_de_payment add constraint FKt0by5lix7px4q4fh3av3psr1f foreign key (moyen_de_payment_id) references moyen_de_payment (id)
alter table restaurant_moyen_de_payment add constraint FKi7bscp75y2cnek7r2bt0fgyjy foreign key (restaurant_id) references restaurant (id)
alter table utilisateur_groupe add constraint FKl296pow140muh5t3xuvyg5glr foreign key (groupe_id) references groupe (id)
alter table utilisateur_groupe add constraint FKhqoflviid3rxk7nji5pj5578x foreign key (utilisateur_id) references utilisateur (id)
alter table ville add constraint FKrxxss6tb7mtrux2io14r3wygf foreign key (region_id) references region (id)
insert into cuisine (nom) values ('Italienne')
insert into cuisine (nom) values ('Indienne')
insert into cuisine (nom) values ('Française')
insert into cuisine (nom) values ('Brésiliene')
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('La Bicha', 3.50, 3, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('PraiadoPepê', 2.66, 4, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('Shakira', 3.56, 2, utc_timestamp, utc_timestamp)
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('Woko', 0.00, 1, utc_timestamp, utc_timestamp)
insert into moyen_de_payment (description) values ('carte')
insert into moyen_de_payment (description) values ('virement')
insert into moyen_de_payment (description) values ('ticket prépayé')
insert into restaurant_moyen_de_payment (restaurant_id, moyen_de_payment_id) values (1, 1), (1, 2), (1, 3), (2, 1), (3, 2), (3, 3)
insert into region (nom) values ('Nouvelle Aquitaine')
insert into region (nom) values ('Bourgogne')
insert into ville (nom, region_id) values ('Bordeaux', 1)
insert into ville (nom, region_id) values ('Dijon', 2)
insert into ville (nom, region_id) values ('Beaune', 2)
insert into groupe (nom) values ('user')
insert into groupe (nom) values ('admin')
insert into autorisation (nom, description) values ('lire','permission pour lecture' )
insert into autorisation (nom, description) values ('editer', 'permission pour edition')
insert into groupe_autorisation (groupe_id, autorisation_id) values (1, 1), (2, 1), (2, 2)
insert into utilisateur (nom, email, mot_de_passe, date_creation) values ('Fernanda', 'fernanda@email.com', 'Test1234', utc_timestamp)
insert into utilisateur (nom, email, mot_de_passe, date_creation) values ('Lucca', 'lucca@email.com', 'Test1234', utc_timestamp)
insert into utilisateur_groupe (utilisateur_id, groupe_id) values (1, 1), (1, 2), (2, 2)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Porc', 'Roti de porc fumé', 78.90, 1, 3)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Soupe', 'Soupe de légumes', 6.55, 1, 2)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Viande', 'Picanha ', 20.50, 1, 4)
insert into produit (nom, description, prix, actif, restaurant_id) values ('Pizza', 'Reine ', 5.50, 1, 1)
