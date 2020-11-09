package com.kastrupf.algafood.domain.repository;

import java.util.List;

import com.kastrupf.algafood.domain.model.Ville;

public interface VilleRepository {
	
	List<Ville> toutes();
	Ville parId(Long id);
	Ville ajouter(Ville ville);
	void supprimer(Long id);

}
