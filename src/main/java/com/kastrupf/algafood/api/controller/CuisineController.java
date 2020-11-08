package com.kastrupf.algafood.api.controller;

import java.util.List;

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

import com.kastrupf.algafood.domain.exception.EntityInUseException;
import com.kastrupf.algafood.domain.exception.EntityNotFoundException;
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
		return cuisineRepository.toutes();
	}
				
	@GetMapping("/{id}")
	public ResponseEntity<Cuisine> chercher(@PathVariable Long id) {
		Cuisine cuisine = cuisineRepository.parId(id);
		
		if (cuisine != null) {
//			return ResponseEntity.status(HttpStatus.OK).body(cuisine);
			return ResponseEntity.ok(cuisine);
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
		Cuisine cuisineActuelle = cuisineRepository.parId(id);
		
		if (cuisineActuelle != null) { 			
//			cuisineActuelle.setNom(cuisine.getNom());
			BeanUtils.copyProperties(cuisine, cuisineActuelle, "id");
		
			cuisineActuelle = registreCuisine.ajouter(cuisineActuelle);
			return ResponseEntity.ok(cuisineActuelle); 
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cuisine> supprimer(@PathVariable Long id) {
		try {
			registreCuisine.supprimer(id);
			return ResponseEntity.noContent().build();
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}