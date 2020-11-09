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
import com.kastrupf.algafood.domain.model.Ville;
import com.kastrupf.algafood.domain.repository.VilleRepository;
import com.kastrupf.algafood.domain.service.RegistreVilleService;

@RestController
@RequestMapping("/villes")
public class VilleController {
	
	@Autowired
	private VilleRepository villeRepository;
	
	@Autowired
	private RegistreVilleService registreVille;
	
	@GetMapping
	public List<Ville> lister() {
		return villeRepository.toutes();
	}
				
	@GetMapping("/{id}")
	public ResponseEntity<Ville> chercher(@PathVariable Long id) {
		Ville ville = villeRepository.parId(id);
		
		if (ville != null) {
//			return ResponseEntity.status(HttpStatus.OK).body(ville);
			return ResponseEntity.ok(ville);
		}
		
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Ville ajouter(@RequestBody Ville ville) {
		return registreVille.ajouter(ville);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> mettreAJour(@PathVariable Long id,
			@RequestBody Ville ville) {
		try {
			Ville villeActuelle = villeRepository.parId(id);
			
			if (villeActuelle != null) {
				BeanUtils.copyProperties(ville, villeActuelle, "id");
				
				villeActuelle = registreVille.ajouter(villeActuelle);
				return ResponseEntity.ok(villeActuelle);
			}
			
			return ResponseEntity.notFound().build();
		
		} catch (EntityNotFoundException e) {
			return ResponseEntity.badRequest()
					.body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Ville> supprimer(@PathVariable Long id) {
		try {
			registreVille.supprimer(id);
			return ResponseEntity.noContent().build();
			
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		
		} catch (EntityInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}