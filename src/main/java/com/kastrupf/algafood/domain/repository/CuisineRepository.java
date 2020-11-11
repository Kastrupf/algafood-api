package com.kastrupf.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kastrupf.algafood.domain.model.Cuisine;

@Repository
public interface CuisineRepository extends JpaRepository<Cuisine, Long>{
	
//	List<Cuisine> chercherParNom(String nom);

}
