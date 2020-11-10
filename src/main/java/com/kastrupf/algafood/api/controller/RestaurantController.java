package com.kastrupf.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kastrupf.algafood.domain.exception.EntityNotFoundException;
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
		return restaurantRepository.tous();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurant> parId(@PathVariable Long id) {
		Restaurant restaurant = restaurantRepository.parId(id);
		
		if (restaurant != null) {
			return ResponseEntity.ok(restaurant);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> ajouter(@RequestBody Restaurant restaurant) {
		try {
			restaurant = registreRestaurant.ajouter(restaurant);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(restaurant);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> mettreAJour(@PathVariable Long id,
			@RequestBody Restaurant restaurant) {
			try {
				Restaurant restaurantActuel = restaurantRepository.parId(id);
		
				if (restaurantActuel != null) {
					BeanUtils.copyProperties(restaurant, restaurantActuel, "id");
		
					restaurantActuel = registreRestaurant.ajouter(restaurantActuel);
					return ResponseEntity.ok(restaurantActuel); 
				}
		
				return ResponseEntity.notFound().build();
				
			} catch (EntityNotFoundException e) {
				return ResponseEntity.badRequest()
					.body(e.getMessage());
			}
     }
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> mettreAJourPartiel(@PathVariable Long id,
			@RequestBody Restaurant restaurant) {
		
		System.out.println(restaurant);
		
		return ResponseEntity.ok().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
}