package com.kastrupf.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	private CuisineRepository cuisines;
	
	@Autowired
	private RegistreCuisineService registreCuisine;
	
	@GetMapping
	public List<Cuisine> lister() {
		return cuisines.findAll();
	}
				
	@GetMapping("/{id}")
	public Cuisine chercher(@PathVariable Long id) {
		return registreCuisine.chercherOuEchouer(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cuisine ajouter(@RequestBody Cuisine cuisine) {
		return registreCuisine.ajouter(cuisine);
	}
	
	@PutMapping("/{id}")
	public Cuisine mettreAJour(@PathVariable Long id,
			@RequestBody Cuisine cuisine) {
		Cuisine cuisineActuelle = registreCuisine.chercherOuEchouer(id);
				
		BeanUtils.copyProperties(cuisine, cuisineActuelle, "id");
		
		return registreCuisine.ajouter(cuisineActuelle);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void supprimer(@PathVariable Long id) {
		registreCuisine.supprimer(id);
	}

}