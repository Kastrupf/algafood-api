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
		return regionRepository.toutes();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Region> parId(@PathVariable Long id) {
		Region region = regionRepository.parId(id);
		
		if (region != null) {
			return ResponseEntity.ok(region);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Region ajouter(@RequestBody Region region) {
		return registreRegion.ajouter(region);
	}
	    
	@PutMapping("/{id}")
	public ResponseEntity<Region> mettreAJour(@PathVariable Long id,
			@RequestBody Region region) {
		Region regionActuelle = regionRepository.parId(id);
			
		if (regionActuelle != null) {
			BeanUtils.copyProperties(region, regionActuelle, "id");
				
			regionActuelle = registreRegion.ajouter(regionActuelle);
			return ResponseEntity.ok(regionActuelle);
		}
			
		return ResponseEntity.notFound().build();
	}
 
	@DeleteMapping("/{id}")
	public ResponseEntity<?> supprimer(@PathVariable Long id) {
		try {
			registreRegion.supprimer(id);	
			return ResponseEntity.noContent().build();
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
		}
	}

}