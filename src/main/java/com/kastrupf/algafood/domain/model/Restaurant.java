package com.kastrupf.algafood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kastrupf.algafood.Groups;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded= true)
@Entity
public class Restaurant {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column (nullable = false)
	private String nom;
	
	@NotNull 
	@PositiveOrZero
	@Column (name="frais_transport", nullable = false)
	private BigDecimal fraisTransport;
	
	@Valid
	@ConvertGroup(from = Default.class, to = Groups.CuisineId.class)
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cuisine_id", nullable = false)
	private Cuisine cuisine;
	
	@JsonIgnore
	@Embedded
	private Adresse adresse;
	
	@JsonIgnore
	@CreationTimestamp
	@Column (name = "date_registre", nullable = false)
	private LocalDateTime dateRegistre;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column (name = "date_mise_a_jour", nullable = false)
	private LocalDateTime dateMiseAJour;
		
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurant_moyen_de_payment", 
			joinColumns = @JoinColumn(name = "restaurant_id"),
			inverseJoinColumns = @JoinColumn(name = "moyen_de_payment_id"))
	private List<MoyenDePayment> moyensDePayement = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private List<Produit> produits = new ArrayList<>();
		
}
