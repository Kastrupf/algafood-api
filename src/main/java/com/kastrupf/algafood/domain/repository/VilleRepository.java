package com.kastrupf.algafood.domain.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kastrupf.algafood.domain.model.Ville;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long>{
		
}
