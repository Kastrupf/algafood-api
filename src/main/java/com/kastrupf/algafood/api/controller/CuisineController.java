package com.kastrupf.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kastrupf.algafood.domain.model.Cuisine;
import com.kastrupf.algafood.domain.repository.CuisineRepository;
import com.kastrupf.algafood.domain.service.RegistreCuisineService;

@RestController
@RequestMapping("/cuisines")
public class CuisineController {
	
	@Autowired
	private CuisineRepository cuisineRepository;
	
	@Autowired
	private RegistreCuisineService registreCuisine;
	
	@GetMapping
	public List<Cuisine> lister() {
		return cuisineRepository.findAll();
	}
				
	@GetMapping("/{id}")
	public ResponseEntity<Cuisine> chercher(@PathVariable Long id) {
		Optional<Cuisine> cuisine = cuisineRepository.findById(id);
		
		if (cuisine.isPresent()) {
//			return ResponseEntity.status(HttpStatus.OK).body(cuisine);
			return ResponseEntity.ok(cuisine.get());
		}
		
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cuisine ajouter(@RequestBody Cuisine cuisine) {
		return registreCuisine.ajouter(cuisine);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cuisine> mettreAJour(@PathVariable Long id,
			@RequestBody Cuisine cuisine) {
		Optional<Cuisine> cuisineActuelle = cuisineRepository.findById(id);
		
		if (cuisineActuelle.isPresent()) { 			
//			cuisineActuelle.setNom(cuisine.getNom());
			BeanUtils.copyProperties(cuisine, cuisineActuelle.get(), "id");
		
			Cuisine cuisineSaved = registreCuisine.ajouter(cuisineActuelle.get());
			return ResponseEntity.ok(cuisineSaved); 
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void supprimer(@PathVariable Long id) {
		registreCuisine.supprimer(id);
	}
				
/*	@DeleteMapping("/{id}")
	public ResponseEntity<?> supprimer(@PathVariable Long id) {
		try {
			registreCuisine.supprimer(id);
			return ResponseEntity.noContent().build();
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).
					body(e.getMessage());
		}
	} */	
	
}