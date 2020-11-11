package com.kastrupf.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kastrupf.algafood.domain.exception.EntityNotFoundException;
import com.kastrupf.algafood.domain.model.Cuisine;
import com.kastrupf.algafood.domain.model.Restaurant;
import com.kastrupf.algafood.domain.repository.CuisineRepository;
import com.kastrupf.algafood.domain.repository.RestaurantRepository;

@Service
public class RegistreRestaurantService {
	
	@Autowired
	private RestaurantRepository restaurants;
	
	@Autowired
	private CuisineRepository cuisines;
	
		public Restaurant ajouter(Restaurant restaurant) {
			Long cuisineId = restaurant.getCuisine().getId();
			Cuisine cuisine = cuisines.findById(cuisineId)
					.orElseThrow(() -> new EntityNotFoundException(
						String.format("Il n'y a pas de registre de cuisine avec le code %d", cuisineId)));
								
			restaurant.setCuisine(cuisine);
			
			return restaurants.save(restaurant);
		}

}
