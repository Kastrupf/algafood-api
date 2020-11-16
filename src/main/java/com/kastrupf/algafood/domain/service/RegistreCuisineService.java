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
	
	private static final String MSG_CUISINE_IN_USE = "Il n'y a pas de cuisine enregistrée avec le code %d";
	private static final String MSG_CUISINE_NON_TROUVEE = MSG_CUISINE_IN_USE;
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
						String.format(MSG_CUISINE_NON_TROUVEE, id));
				
			} catch (DataIntegrityViolationException e) {
				throw new EntityInUseException(
						String.format("La cuisine code %d ne peut pas être retirée car elle est en cours d'utilisation", id));
			}
	}
		
		public Cuisine chercherOuEchouer(Long id) {
			return cuisines.findById(id)
					.orElseThrow(() -> new EntityNotFoundException(
							String.format(MSG_CUISINE_NON_TROUVEE, id)));
		}
}
