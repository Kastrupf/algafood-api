insert into cuisine (id, nom) values (1, 'Italienne');
insert into cuisine (id, nom) values (2, 'Indienne');
insert into cuisine (id, nom) values (3, 'Française');

insert into restaurant (nom, frais_transport, cuisine_id) values ('LaMama', 5.25, 1);
insert into restaurant (nom, frais_transport, cuisine_id) values ('PraiadoPepê', 2.66, 1);
insert into restaurant (nom, frais_transport, cuisine_id) values ('Shakira', 3.56, 2);

insert into moyen_de_payment (description) values ('carte');
insert into moyen_de_payment (description) values ('virement');

insert into autorisation (nom, description) values ('admin','administrateur' );
insert into autorisation (nom, description) values ('user', 'utilisateur');

insert into ville (nom) values ('Bordeaux');
insert into ville (nom) values ('Dijon');

insert into region (nom) values ('Nouvelle Aquitaine');
insert into region (nom) values ('Bourgogne');