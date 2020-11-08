package com.kastrupf.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kastrupf.algafood.domain.model.Region;
import com.kastrupf.algafood.domain.repository.RegionRepository;

@RestController
@RequestMapping("/regions")
public class RegionController {
	
	@Autowired
	private RegionRepository regionRepository;
	
	@GetMapping
	public List<Region> listar() {
		return regionRepository.toutes();
	}
	
}