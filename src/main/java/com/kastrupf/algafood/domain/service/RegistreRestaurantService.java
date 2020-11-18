package com.kastrupf.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kastrupf.algafood.domain.exception.RestaurantNonTrouveException;
import com.kastrupf.algafood.domain.model.Cuisine;
import com.kastrupf.algafood.domain.model.Restaurant;
import com.kastrupf.algafood.domain.repository.RestaurantRepository;

@Service
public class RegistreRestaurantService {
		
	@Autowired
	private RestaurantRepository restaurants;
	
	@Autowired
	private RegistreCuisineService registreCuisine;
	
		public Restaurant ajouter(Restaurant restaurant) {
			Long cuisineId = restaurant.getCuisine().getId();
			Cuisine cuisine = registreCuisine.chercherOuEchouer(cuisineId);
			restaurant.setCuisine(cuisine);
			
			return restaurants.save(restaurant);
		}
		
		public Restaurant chercherOuEchouer(Long id) {
		    return restaurants.findById(id)
		        .orElseThrow(() -> new RestaurantNonTrouveException(id));
		}
}
