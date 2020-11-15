package com.kastrupf.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Commande {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private BigDecimal total;
    private BigDecimal fraisDePort;
    private BigDecimal totalPlusFrais;

    @Embedded
    private Adresse adresseLivraison;
    
    private StatusCommande status;
    
    @CreationTimestamp
    private LocalDateTime dateCreation;

    private LocalDateTime dateConfirmation;
    private LocalDateTime dateAnnulation;
    private LocalDateTime dateLivraison;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private MoyenDePayment moyenDePayment;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;
    
    @ManyToOne
    @JoinColumn(name = "utilisateur_client_id", nullable = false)
    private Utilisateur client;
    
    @OneToMany(mappedBy = "commande")
    private List<ItemCommande> itens = new ArrayList<>();

}