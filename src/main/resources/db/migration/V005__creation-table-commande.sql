create table commande (
    id bigint not null auto_increment,
    total decimal(10,2) not null,
    frais_de_port decimal(10,2) not null,
    total_plus_frais decimal(10,2) not null,

    restaurant_id bigint not null,
    utilisateur_client_id bigint not null,
    moyen_de_payment_id bigint not null,
    
    adresse_id bigint(20) not null,
    code_postal varchar(9) not null,
    numero varchar(20) not null,
    voie varchar(100) not null,
	complement varchar(60) null,
    
    status varchar(10) null,
    date_creation datetime null,
    date_confirmation datetime null,
    date_annulation datetime null,
    date_livraison datetime null,

    primary key (id),

    constraint fk_commande_adresse foreign key (adresse_id) references ville (id),
    constraint fk_commande_restaurant foreign key (restaurant_id) references restaurant (id),
    constraint fk_commande_utilisateur_client foreign key (utilisateur_client_id) references utilisateur (id),
    constraint fk_commande_moyen_de_payment foreign key (moyen_de_payment_id) references moyen_de_payment (id)
) engine=InnoDB default charset=utf8;

create table item_commande (
    id bigint not null auto_increment,
    quantite smallint(6) not null,
    prix_unitaire decimal(10,2) not null,
    prix_total decimal(10,2) not null,
    commentaire varchar(255) null,
    commande_id bigint not null,
    produit_id bigint not null,
    
    primary key (id),
    unique key uk_item_commande_produit (commande_id, produit_id),

    constraint fk_item_commande_commande foreign key (commande_id) references commande (id),
    constraint fk_item_commande_produit foreign key (produit_id) references produit (id)
) engine=InnoDB default charset=utf8;