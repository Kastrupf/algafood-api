package com.kastrupf.algafood.domain.repository;

import java.util.List;

import com.kastrupf.algafood.domain.model.Restaurant;

public interface RestaurantRepository {
	
	List<Restaurant> tous();
	Restaurant parId(Long id);
	Restaurant ajouter(Restaurant restaurant);
	void supprimer(Restaurant restaurant);

}
