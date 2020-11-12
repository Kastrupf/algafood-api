package com.kastrupf.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kastrupf.algafood.domain.model.Restaurant;
import com.kastrupf.algafood.domain.repository.RestaurantRepository;
import com.kastrupf.algafood.infrastructure.repository.spec.RestaurantAvecFraisDeTransportGratuitSpec;
import com.kastrupf.algafood.infrastructure.repository.spec.RestaurantAvecNomSimilaireSpec;


@RestController
@RequestMapping("/tests")
public class TestController {

	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	
	@GetMapping("/restaurants/par-nom-et-fraisDeTransport")
	public List<Restaurant> restaurantsParNomFraisTransport(String nom, 
			BigDecimal fraisTransportInitial, BigDecimal fraisTransportFinal) {
		return restaurantRepository.find(nom, fraisTransportInitial, fraisTransportFinal);
	}
	
	@GetMapping("/restaurantes/count-par-cuisine")
	public int restaurantsCountParCuisine(Long cuisineId) {
		return restaurantRepository.countByCuisineId(cuisineId);
	}
	
	@GetMapping("/restaurants/avec-frais-de-transport-gratuit")
	public List<Restaurant> restaurantsAvecFraisDeTransportGratuit(String nom) {
		var avecFraisDeTrasnportGratuit = new RestaurantAvecFraisDeTransportGratuitSpec();
		var avecNomSimilaire = new RestaurantAvecNomSimilaireSpec(nom);
		
		return restaurantRepository.findAll(avecFraisDeTrasnportGratuit.and(avecNomSimilaire));
	}
	
}
