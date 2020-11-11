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
	
	@Autowired
	private RegionRepository regionRepository;
	
	public Region ajouter(Region region) {
		return regionRepository.save(region);
	}
	
	public void supprimer(Long id) {
		try {
			regionRepository.deleteById(id);
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException(
				String.format("Il n'y a pas de registre de région avec le code %d", id));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntityInUseException(
				String.format("La région code %d ne peut pas être retirée car elle est en cours d'utilisation", id));
		}
	}
	
}
