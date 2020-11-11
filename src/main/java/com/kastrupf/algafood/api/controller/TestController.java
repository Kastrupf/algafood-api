package com.kastrupf.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kastrupf.algafood.domain.model.Restaurant;
import com.kastrupf.algafood.domain.repository.RestaurantRepository;

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
	
	
	
	
	
}
