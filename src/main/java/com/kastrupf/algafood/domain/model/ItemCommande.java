package com.kastrupf.algafood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class ItemCommande {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal prixUnitaire;
    private BigDecimal prixTotal;
    private Integer quantite;
    private String commentaire;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Commande commande;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produit produit;
}