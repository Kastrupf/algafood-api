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
	
	/* si un restaurant n'a aucun moyen de paiement associé, ce restaurant ne sera pas retourné en utilisant 
	 * JOIN FETCH r.formasPagamento. Pour résoudre ce problème, nous devons utiliser LEFT JOIN FETCH r.formasPayment*/
	@Query("from Restaurant r join fetch r.cuisine left join fetch r.moyensDePayement")
	 List<Restaurant> findAll();
			
	int countByCuisineId(Long cuisine);
			
}
