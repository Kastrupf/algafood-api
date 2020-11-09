package com.kastrupf.algafood.domain.repository;

import java.util.List;

import com.kastrupf.algafood.domain.model.Region;

public interface RegionRepository {
	
	List<Region> toutes();
	Region parId(Long id);
	Region ajouter(Region region);
	void supprimer(Long id);

}
