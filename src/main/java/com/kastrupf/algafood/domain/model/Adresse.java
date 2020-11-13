package com.kastrupf.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable
public class Adresse {
	
	@Column(name = "adresse_numero")
	private String numero;
	
	@Column(name = "adresse_voie")
	private String voie;
	
	@Column(name = "adresse_complement")
	private String complement;
	
	@Column(name = "adresse_code_postal")
	private String codePostal;
	
	@ManyToOne
	@JoinColumn(name = "adresse_ville_id")
	private Ville ville;
}
