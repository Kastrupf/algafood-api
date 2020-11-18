package com.kastrupf.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.kastrupf.algafood.domain.exception.EntiteEnCoursUtilisationException;
import com.kastrupf.algafood.domain.exception.EntiteNonTrouveeException;
import com.kastrupf.algafood.domain.model.Region;
import com.kastrupf.algafood.domain.model.Ville;
import com.kastrupf.algafood.domain.repository.VilleRepository;

@Service
public class RegistreVilleService {
	
	private static final String MSG_VILLE_IN_USE = 
			"La ville de code %d ne peut pas être supprimée, car elle est en cours d'utilisation";

	private static final String MSG_VILLE_NON_TROUVEE = 
			"Il n'y a pas de ville enregistrée avec le code %d";

	@Autowired
	private VilleRepository villes;
	
	@Autowired
	private RegistreRegionService registreRegion;
	
	
	public Ville ajouter(Ville ville) {
        Long regionId = ville.getRegion().getId();
        Region region = registreRegion.chercherOuEchouer(regionId);
        
        ville.setRegion(region);
        
        return villes.save(ville);
    }
		
		public void supprimer(Long id) {
			try {
				villes.deleteById(id);
				
			} catch (EmptyResultDataAccessException e) {	
				throw new EntiteNonTrouveeException(
						String.format(MSG_VILLE_NON_TROUVEE, id));
				
			} catch (DataIntegrityViolationException e) {
				throw new EntiteEnCoursUtilisationException(
						String.format(MSG_VILLE_IN_USE, id));
			}
	}
		
		public Ville chercherOuEchouer(Long id) {
		    return villes.findById(id)
		        .orElseThrow(() -> new EntiteNonTrouveeException(
		                String.format(MSG_VILLE_NON_TROUVEE, id)));
		}    
		
}
