package com.kastrupf.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kastrupf.algafood.domain.model.Restaurant;
import com.kastrupf.algafood.domain.repository.RestaurantRepository;
import com.kastrupf.algafood.domain.service.RegistreRestaurantService;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RegistreRestaurantService registreRestaurant;
	
	@GetMapping
	public List<Restaurant> lister() {
		return restaurantRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Restaurant parId(@PathVariable Long id) {
		return registreRestaurant.chercherOuEchouer(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurant ajouter(@RequestBody Restaurant restaurant) {
	    return registreRestaurant.ajouter(restaurant);
	}

	@PutMapping("/{id}")
	public Restaurant mettreAJour(@PathVariable Long id,
	        @RequestBody Restaurant restaurant) {
	    Restaurant restaurantActuel = registreRestaurant.chercherOuEchouer(id);
	    
	    BeanUtils.copyProperties(restaurant, restaurantActuel, 
	            "id", "moyensDePayement", "adresse", "dateRegistre", "produits");
	    
	    return registreRestaurant.ajouter(restaurantActuel);
	}
	
	@PatchMapping("/{id}")
	public Restaurant mettreAJourPartiel(@PathVariable Long id,
			@RequestBody Map<String, Object> data) {
		Restaurant restaurantActuel = registreRestaurant.chercherOuEchouer(id);
			
		merge(data, restaurantActuel);
		
		return mettreAJour(id, restaurantActuel);
	}

	private void merge(Map<String, Object> dataOrigen, Restaurant restaurantDestin) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant restaurantOrigen = objectMapper.convertValue(dataOrigen, Restaurant.class);
		
		dataOrigen.forEach((nomPropriete, valeurPropriete) -> {
			Field field = ReflectionUtils.findField(Restaurant.class, nomPropriete);
			field.setAccessible(true);
			
			Object nouveauValeur = ReflectionUtils.getField(field, restaurantOrigen);
			
//			System.out.println(nomePropriete + " = " + valeurPropriete + " = " + nouveauValeur);
			
			ReflectionUtils.setField(field, restaurantDestin, nouveauValeur);
		});
	}
	
}