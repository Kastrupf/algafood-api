package com.kastrupf.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kastrupf.algafood.Groups;
import com.kastrupf.algafood.domain.exception.CuisineNonTrouveeException;
import com.kastrupf.algafood.domain.exception.GeneriqueException;
import com.kastrupf.algafood.domain.model.Restaurant;
import com.kastrupf.algafood.domain.repository.RestaurantRepository;
import com.kastrupf.algafood.domain.service.RegistreRestaurantService;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurants;
	
	@Autowired
	private RegistreRestaurantService registreRestaurant;
	
	@GetMapping
	public List<Restaurant> lister() {
		return restaurants.findAll();
	}
	
	@GetMapping("/{id}")
	public Restaurant parId(@PathVariable Long id) {
		return registreRestaurant.chercherOuEchouer(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Restaurant ajouter(
			@RequestBody @Validated(Groups.RegistreRestaurant.class) Restaurant restaurant) {
	   
		try {
			return registreRestaurant.ajouter(restaurant);
		} catch (CuisineNonTrouveeException e) {
			throw new GeneriqueException(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public Restaurant mettreAJour(@PathVariable Long id,
	        @RequestBody Restaurant restaurant) {
	    Restaurant restaurantActuel = registreRestaurant.chercherOuEchouer(id);
	    	    
	    BeanUtils.copyProperties(restaurant, restaurantActuel, 
	            "id", "moyensDePayement", "adresse", "dateRegistre", "produits");
	  	    
	    try {
	    	return registreRestaurant.ajouter(restaurantActuel);
		} catch (CuisineNonTrouveeException e) {
			throw new GeneriqueException(e.getMessage());
		}
	}
	
	@PatchMapping("/{id}")
	public Restaurant mettreAJourPartiel(@PathVariable Long id,
			@RequestBody Map<String, Object> data, HttpServletRequest request) {
		Restaurant restaurantActuel = registreRestaurant.chercherOuEchouer(id);
			
		merge(data, restaurantActuel, request);
		
		return mettreAJour(id, restaurantActuel);
	}

	private void merge(Map<String, Object> dataOrigen, Restaurant restaurantDestin, 
			HttpServletRequest request) {
		ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request); 
		
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
			
			Restaurant restaurantOrigen = objectMapper.convertValue(dataOrigen, Restaurant.class);
			
			dataOrigen.forEach((nomPropriete, valeurPropriete) -> {
				Field field = ReflectionUtils.findField(Restaurant.class, nomPropriete);
				field.setAccessible(true);
				
				Object nouveauValeur = ReflectionUtils.getField(field, restaurantOrigen);
				
				ReflectionUtils.setField(field, restaurantDestin, nouveauValeur);
			});
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
		}
		
	}
	
}