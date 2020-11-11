package com.kastrupf.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kastrupf.algafood.domain.exception.EntityInUseException;
import com.kastrupf.algafood.domain.exception.EntityNotFoundException;
import com.kastrupf.algafood.domain.model.Cuisine;
import com.kastrupf.algafood.domain.repository.CuisineRepository;

@Service
public class RegistreCuisineService {
	
	@Autowired
	private CuisineRepository cuisines;
	
		public Cuisine ajouter(Cuisine cuisine) {
		return cuisines.save(cuisine);
	}
		
		public void supprimer(Long id) {
			try {
				cuisines.deleteById(id);
				
			} catch (EmptyResultDataAccessException e) {	
				throw new EntityNotFoundException(
						String.format("Il n'y a pas de cuisine enregistrée avec le code %d", id));
				
			} catch (DataIntegrityViolationException e) {
				throw new EntityInUseException(
						String.format("La cuisine code %d ne peut pas être retirée car elle est en cours d'utilisation", id));
			}
	}
}
