package com.kastrupf.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kastrupf.algafood.domain.exception.EntityInUseException;
import com.kastrupf.algafood.domain.exception.EntityNotFoundException;
import com.kastrupf.algafood.domain.model.Region;
import com.kastrupf.algafood.domain.repository.RegionRepository;

@Service
public class RegistreRegionService {
	
	private static final String MSG_REGION_IN_USE = 
			"La région code %d ne peut pas être retirée car elle est en cours d'utilisation";
	
	private static final String MSG_REGION_NON_TROUVEE = 
			"Il n'y a pas de registre de région avec le code %d";
	
	@Autowired
	private RegionRepository regions;
	
	public Region ajouter(Region region) {
		return regions.save(region);
	}
	
	public void supprimer(Long id) {
		try {
			regions.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
				String.format(MSG_REGION_NON_TROUVEE, id));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format(MSG_REGION_IN_USE, id));
		}
	}
	
	public Region chercherOuEchouer(Long id) {
	    return regions.findById(id)
	        .orElseThrow(() -> new EntityNotFoundException(
	                String.format(MSG_REGION_NON_TROUVEE, id)));
	}

}
