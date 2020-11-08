package com.kastrupf.algafood.domain.repository;

import java.util.List;

import com.kastrupf.algafood.domain.model.Autorisation;

public interface AutorisationRepository {
	
	List<Autorisation> toutes();
	Autorisation parId(Long id);
	Autorisation ajouter(Autorisation autorisation);
	void supprimer(Autorisation autorisation);

}
