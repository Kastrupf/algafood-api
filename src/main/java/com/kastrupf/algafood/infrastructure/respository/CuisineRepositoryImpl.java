package com.kastrupf.algafood.infrastructure.respository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kastrupf.algafood.domain.model.Cuisine;
import com.kastrupf.algafood.domain.repository.CuisineRepository;

@Component
public class CuisineRepositoryImpl implements CuisineRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Cuisine parId(Long id) {
		return manager.find(Cuisine.class, id);
	}
	
	@Override
	public List<Cuisine> toutes() {
		 return manager.createQuery("from Cuisine", Cuisine.class)
				 .getResultList();
	}
	
	@Override
	@Transactional
	public Cuisine ajouter(Cuisine cuisine) {
		return manager.merge(cuisine);
	}
	
	@Override
	@Transactional
	public void supprimer(Long id) {
		Cuisine cuisine = parId(id);
		
		if (cuisine == null) {
			throw new EmptyResultDataAccessException(1);
		}
			
		manager.remove(cuisine);
	}

}
