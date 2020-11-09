package com.kastrupf.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kastrupf.algafood.domain.exception.EntityInUseException;
import com.kastrupf.algafood.domain.exception.EntityNotFoundException;
import com.kastrupf.algafood.domain.model.Region;
import com.kastrupf.algafood.domain.model.Ville;
import com.kastrupf.algafood.domain.repository.RegionRepository;
import com.kastrupf.algafood.domain.repository.VilleRepository;

@Service
public class RegistreVilleService {
	
	@Autowired
	private VilleRepository villes;
	
	@Autowired
	private RegionRepository regions;
	
	public Ville ajouter(Ville ville) {
        Long regionId = ville.getRegion().getId();
        Region region = regions.parId(regionId);
        
        if (region == null) {
            throw new EntityNotFoundException(
                String.format("Il n'y a pas de région enregistrée avec le code %d", regionId));
        }
        
        ville.setRegion(region);
        
        return villes.ajouter(ville);
    }
		
		public void supprimer(Long id) {
			try {
				villes.supprimer(id);
				
			} catch (EmptyResultDataAccessException e) {	
				throw new EntityNotFoundException(
						String.format("Il n'y a pas de ville enregistrée avec le code %d", id));
				
			} catch (DataIntegrityViolationException e) {
				throw new EntityInUseException(
						String.format("La ville code %d ne peut pas être retirée car elle est en cours d'utilisation", id));
			}
	}
}
