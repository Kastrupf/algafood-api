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
	
	/* si un restaurant n'a aucun moyen de paiement associe, ce restaurant ne sera pas retourne en utilisant 
	 * JOIN FETCH r.moyensDePayment. Pour resoudre ce probleme, nous devons utiliser LEFT JOIN FETCH r.moyesDePayment*/
	@Query("select distinct r from Restaurant r join fetch r.cuisine left join fetch r.moyensDePayement")
	List<Restaurant> findAll();
			
	int countByCuisineId(Long cuisine);
			
}
