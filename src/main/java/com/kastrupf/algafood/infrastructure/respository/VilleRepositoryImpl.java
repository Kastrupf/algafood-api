package com.kastrupf.algafood.infrastructure.respository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kastrupf.algafood.domain.model.Ville;
import com.kastrupf.algafood.domain.repository.VilleRepository;

@Component
public class VilleRepositoryImpl implements VilleRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Ville parId(Long id) {
		return manager.find(Ville.class, id);
	}
	
	@Override
	public List<Ville> toutes() {
		 return manager.createQuery("from Ville", Ville.class)
				 .getResultList();
	}
	
	@Override
	@Transactional
	public Ville ajouter(Ville ville) {
		return manager.merge(ville);
	}
	
	@Override
	@Transactional
	public void supprimer(Ville ville) {
		ville = parId(ville.getId());
		manager.remove(ville);
	}

}
