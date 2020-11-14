package com.kastrupf.algafood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kastrupf.algafood.domain.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>, RestaurantRepositoryQueries, 
JpaSpecificationExecutor<Restaurant> {
	
	@Query("from Restaurant r join fetch r.cuisine left join fetch r.moyensDePayement")
	 List<Restaurant> findAll();
			
	int countByCuisineId(Long cuisine);
			
}
