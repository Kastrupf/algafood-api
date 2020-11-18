package com.kastrupf.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kastrupf.algafood.domain.exception.EntiteEnCoursUtilisationException;
import com.kastrupf.algafood.domain.exception.RegionNonTrouveeException;
import com.kastrupf.algafood.domain.model.Region;
import com.kastrupf.algafood.domain.repository.RegionRepository;

@Service
public class RegistreRegionService {
	
	private static final String MSG_REGION_IN_USE 
		= "La région avec le code %d ne peut pas être supprimée, car elle est en cours d'utilisation";
	
	@Autowired
	private RegionRepository regions;
	
	public Region ajouter(Region region) {
		return regions.save(region);
	}
	
	public void supprimer(Long id) {
		try {
			regions.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new RegionNonTrouveeException(id);
		
		} catch (DataIntegrityViolationException e) {
			throw new EntiteEnCoursUtilisationException(
				String.format(MSG_REGION_IN_USE, id));
		}
	}
	
	public Region chercherOuEchouer(Long id) {
	    return regions.findById(id)
	        .orElseThrow(() -> new RegionNonTrouveeException(id));
	}
}
