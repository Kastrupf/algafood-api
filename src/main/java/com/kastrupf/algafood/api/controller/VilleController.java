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

import com.kastrupf.algafood.domain.exception.EntiteNonTrouveeException;
import com.kastrupf.algafood.domain.exception.GeneriqueException;
import com.kastrupf.algafood.domain.model.Ville;
import com.kastrupf.algafood.domain.repository.VilleRepository;
import com.kastrupf.algafood.domain.service.RegistreVilleService;

@RestController
@RequestMapping("/villes")
public class VilleController {
	
	@Autowired
	private VilleRepository villes;
	
	@Autowired
	private RegistreVilleService registreVille;
	
	@GetMapping
	public List<Ville> lister() {
		return villes.findAll();
	}
				
	@GetMapping("/{id}")
	public Ville chercher(@PathVariable Long id) {
		return registreVille.chercherOuEchouer(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Ville ajouter(@RequestBody Ville ville) {
	
		try {
			return registreVille.ajouter(ville);
		} catch (EntiteNonTrouveeException e) {
			throw new GeneriqueException(e.getMessage());
		}
	}
			
	@PutMapping("/{id}")
	public Ville mettreAJour(@PathVariable Long id,
			@RequestBody Ville ville) {
		
		try {
			Ville villeActuelle = registreVille.chercherOuEchouer(id);
			BeanUtils.copyProperties(ville, villeActuelle, "id");
			
			return registreVille.ajouter(villeActuelle);
		} catch (EntiteNonTrouveeException e) {
			throw new GeneriqueException(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
	    registreVille.supprimer(id);	
	}
	
}