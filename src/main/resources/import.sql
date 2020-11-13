insert into cuisine (id, nom) values (1, 'Italienne');
insert into cuisine (id, nom) values (2, 'Indienne');
insert into cuisine (id, nom) values (3, 'Française');

insert into region (id, nom) values (1, 'Nouvelle Aquitaine');
insert into region (id, nom) values (2, 'Bourgogne');

insert into ville (id, nom, region_id) values (1, 'Bordeaux', 1);
insert into ville (id, nom, region_id) values (2, 'Dijon', 2);
insert into ville (id, nom, region_id) values (3, 'Beaune', 2);

insert into restaurant (id, nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour, adresse_ville_id, adresse_code_postal, adresse_numero, adresse_voie, adresse_complement) values (1, 'La Bicha', 3.50, 3, utc_timestamp, utc_timestamp, 3, '31000', '1', 'rue Nano', 'bis');
insert into restaurant (id, nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values (2, 'PraiadoPepê', 2.66, 1, utc_timestamp, utc_timestamp);
insert into restaurant (id, nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values (3, 'Shakira', 3.56, 2, utc_timestamp, utc_timestamp);
insert into restaurant (id, nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour, adresse_ville_id, adresse_code_postal, adresse_numero, adresse_voie, adresse_complement) values (4, 'Woko', 0.00, 2, utc_timestamp, utc_timestamp, 2, '21000', '13', 'rue Petitot', 'RDC');

insert into moyen_de_payment (id, description) values (1, 'carte');
insert into moyen_de_payment (id, description) values (2, 'virement');
insert into moyen_de_payment (id, description) values (3, 'ticket prépayé');

insert into autorisation (id, nom, description) values (1, 'admin','administrateur' );
insert into autorisation (id, nom, description) values (2, 'user', 'utilisateur');

insert into restaurant_moyen_payment (restaurant_id, moyen_payment_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

insert into produit (nom, description, prix, actif, restaurant_id) values ('Porc roti', 'Roti de porc fumé', 78.90, 1, 1);

