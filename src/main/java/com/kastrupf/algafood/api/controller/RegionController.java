package com.kastrupf.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.kastrupf.algafood.domain.model.Region;
import com.kastrupf.algafood.domain.repository.RegionRepository;
import com.kastrupf.algafood.domain.service.RegistreRegionService;

@RestController
@RequestMapping("/regions")
public class RegionController {
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private RegistreRegionService registreRegion;
	
	@GetMapping
	public List<Region> lister() {
		return regionRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Region parId(@PathVariable Long id) {
		return registreRegion.chercherOuEchouer(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Region ajouter(@RequestBody @Valid Region region) {
		return registreRegion.ajouter(region);
	}
	    
	@PutMapping("/{id}")
	public Region mettreAJour(@PathVariable @Valid Long id,
			@RequestBody Region region) {
		Region regionActuelle = registreRegion.chercherOuEchouer(id);
		BeanUtils.copyProperties(region, regionActuelle, "id");
						
		return registreRegion.ajouter(regionActuelle);
	}
	 
	@DeleteMapping("/{id}")
	public void supprimer(@PathVariable Long id) {
		registreRegion.supprimer(id);	
	}
}