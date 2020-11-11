package com.kastrupf.algafood.infrastructure.respository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kastrupf.algafood.domain.model.Restaurant;
import com.kastrupf.algafood.domain.repository.RestaurantRepository;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository{

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Restaurant parId(Long id) {
		return manager.find(Restaurant.class, id);
	}
	
	@Override
	public List<Restaurant> tous() {
		 return manager.createQuery("from Restaurant", Restaurant.class)
				 .getResultList();
	}
	
	@Override
	@Transactional
	public Restaurant ajouter(Restaurant restaurant) {
		return manager.merge(restaurant);
	}
	
	@Override
	@Transactional
	public void supprimer(Restaurant restaurant) {
		restaurant = parId(restaurant.getId());
		manager.remove(restaurant);
	}

}
