insert into cuisine (id, nom) values (1, 'Italienne');
insert into cuisine (id, nom) values (2, 'Indienne');
insert into cuisine (id, nom) values (3, 'Française');

insert into restaurant (id, nom, frais_transport, cuisine_id) values (1, 'LaMama', 5.25, 1);
insert into restaurant (id, nom, frais_transport, cuisine_id) values (2, 'PraiadoPepê', 2.66, 1);
insert into restaurant (id, nom, frais_transport, cuisine_id) values (3, 'Shakira', 3.56, 2);

insert into region (id, nom) values (1, 'Nouvelle Aquitaine');
insert into region (id, nom) values (2, 'Bourgogne');

insert into ville (id, nom, region_id) values (1, 'Bordeaux', 1);
insert into ville (id, nom, region_id) values (2, 'Dijon', 2);
insert into ville (id, nom, region_id) values (3, 'Beaune', 2);

insert into moyen_de_payment (id, description) values (1, 'carte');
insert into moyen_de_payment (id, description) values (2, 'virement');

insert into autorisation (id, nom, description) values (1, 'admin','administrateur' );
insert into autorisation (id, nom, description) values (2, 'user', 'utilisateur');



