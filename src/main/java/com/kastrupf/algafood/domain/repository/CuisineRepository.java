package com.kastrupf.algafood.domain.repository;

import java.util.List;

import com.kastrupf.algafood.domain.model.Cuisine;

public interface CuisineRepository {
	
	List<Cuisine> toutes();
	Cuisine parId(Long id);
	Cuisine ajouter(Cuisine cuisine);
	void supprimer(Long id);

}
