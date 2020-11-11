package com.kastrupf.algafood.infrastructure.respository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kastrupf.algafood.domain.model.Region;
import com.kastrupf.algafood.domain.repository.RegionRepository;

@Repository
public class RegionRepositoryImpl implements RegionRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Region> toutes() {
		 return manager.createQuery("from Region", Region.class)
				 .getResultList();
	}
	
	@Override
	public Region parId(Long id) {
		return manager.find(Region.class, id);
	}
	
	@Override
	@Transactional
	public Region ajouter(Region region) {
		return manager.merge(region);
	}
	
	@Transactional
	@Override
	public void supprimer(Long id) {
		Region region = parId(id);
		
		if (region == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(region);
	}

}
