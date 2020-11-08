package com.kastrupf.algafood.infrastructure.respository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kastrupf.algafood.domain.model.Autorisation;
import com.kastrupf.algafood.domain.repository.AutorisationRepository;

@Component
public class AutorisationRepositoryImpl implements AutorisationRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Autorisation parId(Long id) {
		return manager.find(Autorisation.class, id);
	}
	
	@Override
	public List<Autorisation> toutes() {
		 return manager.createQuery("from Autorisation", Autorisation.class)
				 .getResultList();
	}
	
	@Override
	@Transactional
	public Autorisation ajouter(Autorisation autorisation) {
		return manager.merge(autorisation);
	}
	
	@Override
	@Transactional
	public void supprimer(Autorisation autorisation) {
		autorisation = parId(autorisation.getId());
		manager.remove(autorisation);
	}

}
