create table ville (
  id bigint not null auto_increment,
  nom varchar(80) not null,
  region varchar(80) not null,
  
  primary key (id)
) engine=InnoDB default charset=utf8;