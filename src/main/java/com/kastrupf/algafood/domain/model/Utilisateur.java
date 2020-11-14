package com.kastrupf.algafood.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded= true)
@Entity
public class Utilisateur {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable = false)
	private String nom;
	
	@Column (nullable = false)
	private String email;
	
	@Column (name="mot_de_passe", nullable = false)
	private String motDePasse;
	
	
	@JsonIgnore
	@CreationTimestamp
	@Column (name = "date_creation", nullable = false)
	private LocalDateTime dateCreation;
		
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "utilisateur_groupe", 
			joinColumns = @JoinColumn(name = "utilisateur_id"),
			inverseJoinColumns = @JoinColumn(name = "groupe_id"))
	private List<Groupe> groupes = new ArrayList<>();
				
}
