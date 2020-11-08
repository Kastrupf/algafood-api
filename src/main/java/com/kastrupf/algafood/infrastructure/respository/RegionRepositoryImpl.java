package com.kastrupf.algafood.infrastructure.respository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.kastrupf.algafood.domain.model.Region;
import com.kastrupf.algafood.domain.repository.RegionRepository;

@Component
public class RegionRepositoryImpl implements RegionRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Region parId(Long id) {
		return manager.find(Region.class, id);
	}
	
	@Override
	public List<Region> toutes() {
		 return manager.createQuery("from Region", Region.class)
				 .getResultList();
	}
	
	@Override
	@Transactional
	public Region ajouter(Region region) {
		return manager.merge(region);
	}
	
	@Override
	@Transactional
	public void supprimer(Region region) {
		region = parId(region.getId());
		manager.remove(region);
	}

}
