insert into cuisine (nom) values ('Italienne');
insert into cuisine (nom) values ('Indienne');
insert into cuisine (nom) values ('Française');
insert into cuisine (nom) values ('Brésiliene');

insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('La Bicha', 3.50, 3, utc_timestamp, utc_timestamp);
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('PraiadoPepê', 2.66, 4, utc_timestamp, utc_timestamp);
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('Shakira', 3.56, 2, utc_timestamp, utc_timestamp);
insert into restaurant (nom, frais_transport, cuisine_id, date_registre, date_mise_a_jour) values ('Woko', 0.00, 1, utc_timestamp, utc_timestamp);

insert into moyen_de_payment (description) values ('carte');
insert into moyen_de_payment (description) values ('virement');
insert into moyen_de_payment (description) values ('ticket prépayé');

insert into restaurant_moyen_de_payment (restaurant_id, moyen_de_payment_id) values (1, 1), (1, 2), (1, 3), (2, 1), (3, 2), (3, 3);

insert into region (nom) values ('Nouvelle Aquitaine');
insert into region (nom) values ('Bourgogne');

insert into ville (nom, region_id) values ('Bordeaux', 1);
insert into ville (nom, region_id) values ('Dijon', 2);
insert into ville (nom, region_id) values ('Beaune', 2);

insert into groupe (nom) values ('user');
insert into groupe (nom) values ('admin');

insert into autorisation (nom, description) values ('lire','permission pour lecture' );
insert into autorisation (nom, description) values ('editer', 'permission pour edition');

insert into groupe_autorisation (groupe_id, autorisation_id) values (1, 1), (2, 1), (2, 2);

insert into utilisateur (nom, email, mot_de_passe, date_creation) values ('Fernanda', 'fernanda@email.com', 'Test1234', utc_timestamp);
insert into utilisateur (nom, email, mot_de_passe, date_creation) values ('Lucca', 'lucca@email.com', 'Test1234', utc_timestamp);

insert into utilisateur_groupe (utilisateur_id, groupe_id) values (1, 1), (1, 2), (2, 2);

insert into produit (nom, description, prix, actif, restaurant_id) values ('Porc', 'Roti de porc fumé', 78.90, 1, 3);
insert into produit (nom, description, prix, actif, restaurant_id) values ('Soupe', 'Soupe de légumes', 6.55, 1, 2);
insert into produit (nom, description, prix, actif, restaurant_id) values ('Viande', 'Picanha ', 20.50, 1, 4);
insert into produit (nom, description, prix, actif, restaurant_id) values ('Pizza', 'Reine ', 5.50, 1, 1);