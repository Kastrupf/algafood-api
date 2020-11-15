create table autorisation (
	id bigint not null auto_increment, 
	description varchar(60) not null, 
	nom varchar(100) not null, 
	primary key (id)
	) engine=InnoDB default charset=utf8;

create table groupe (
	id bigint not null auto_increment, 
	nom varchar(60) not null, 
	primary key (id)
	) engine=InnoDB default charset=utf8;

create table groupe_autorisation (
	groupe_id bigint not null, 
	autorisation_id bigint not null,
	primary key (groupe_id, autorisation_id)
	) engine=InnoDB default charset=utf8;

create table moyen_de_payment (
	id bigint not null auto_increment, 
	description varchar(60) not null, 
	primary key (id)
	) engine=InnoDB default charset=utf8;

create table produit (
	id bigint not null auto_increment, 
	restaurant_id bigint not null, 
	nom varchar(80) not null, 
	description text not null, 
	prix decimal(10,2) not null, 
	actif tinyint(1) not null, 
	primary key (id)
	) engine=InnoDB default charset=utf8;

create table restaurant (
	id bigint not null auto_increment, 
	cuisine_id bigint not null,
	nom varchar(80) not null, 
	frais_transport decimal(10,2) not null, 
	date_mise_a_jour datetime not null, 
	date_registre datetime not null, 

	adresse_ville_id bigint, 
	adresse_code_postal varchar(5), 
	adresse_numero varchar(20), 
	adresse_voie varchar(100), 
	adresse_complement varchar(60), 
	primary key (id)
	) engine=InnoDB default charset=utf8;

create table restaurant_moyen_de_payment (
	restaurant_id bigint not null, 
	moyen_de_payment_id bigint not null,
	primary key (restaurant_id, moyen_de_payment_id)
	) engine=InnoDB default charset=utf8;

create table utilisateur (
	id bigint not null auto_increment, 
	date_creation datetime not null, 
	email varchar(80) not null, 
	mot_de_passe varchar(80) not null, 
	nom varchar(80) not null, 
	primary key (id)
	) engine=InnoDB default charset=utf8;

create table utilisateur_groupe (
	utilisateur_id bigint not null, 
	groupe_id bigint not null
	) engine=InnoDB default charset=utf8;


alter table groupe_autorisation add constraint fk_groupe_autorisation_autorisation 
	foreign key (autorisation_id) references autorisation (id);

alter table groupe_autorisation add constraint fk_groupe_autorisation_groupe 
	foreign key (groupe_id) references groupe (id);

alter table produit add constraint fk_produit_restaurant
	foreign key (restaurant_id) references restaurant (id);

alter table restaurant add constraint fk_restaurant_ville
	foreign key (adresse_ville_id) references ville (id);

alter table restaurant add constraint fk_restaurant_cuisine
	foreign key (cuisine_id) references cuisine (id);

alter table restaurant_moyen_de_payment add constraint fk_restaurant_moyen_de_payment_moyen_de_payment 
	foreign key (moyen_de_payment_id) references moyen_de_payment (id);

alter table restaurant_moyen_de_payment add constraint fk_restaurant_moyen_de_payment_restaurant
	foreign key (restaurant_id) references restaurant (id);

alter table utilisateur_groupe add constraint fk_utilisateur_groupe_groupe
	foreign key (groupe_id) references groupe (id);

alter table utilisateur_groupe add constraint fk_utilisateur_groupe_utilisateur
	foreign key (utilisateur_id) references utilisateur (id);