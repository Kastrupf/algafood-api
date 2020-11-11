package com.kastrupf.algafood.infrastructure.respository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kastrupf.algafood.domain.model.Ville;
import com.kastrupf.algafood.domain.repository.VilleRepository;

@Repository
public class VilleRepositoryImpl implements VilleRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Ville> toutes() {
		 return manager.createQuery("from Ville", Ville.class)
				 .getResultList();
	}
	
	@Override
	public Ville parId(Long id) {
		return manager.find(Ville.class, id);
	}
	
	@Override
	@Transactional
	public Ville ajouter(Ville ville) {
		return manager.merge(ville);
	}
	
	@Override
	@Transactional
	public void supprimer(Long id) {
		Ville ville = parId(id);
		
		if (ville == null) {
			throw new EmptyResultDataAccessException(1);
		}
			
		manager.remove(ville);
	}

}

