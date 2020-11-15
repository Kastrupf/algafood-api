create table region (
	id bigint not null auto_increment,
	nom varchar(80) not null,
	
	primary key (id)
	) engine=InnoDB default charset=utf8;

insert into region (nom) select distinct region from ville;

alter table ville add column region_id bigint not null;

update ville v set v.region_id = (select r.id from region r where r.nom = v.nom);

alter table ville add constraint fk_ville_region foreign key (region_id) references region (id);
alter table ville drop column region;

