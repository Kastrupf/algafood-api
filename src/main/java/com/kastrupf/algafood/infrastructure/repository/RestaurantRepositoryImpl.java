package com.kastrupf.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.kastrupf.algafood.domain.model.Restaurant;
import com.kastrupf.algafood.domain.repository.RestaurantRepositoryQueries;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Restaurant> find(String nom, 
			BigDecimal fraisTransportInitial, BigDecimal fraisTransportFinal) {
		
		var jpql = new StringBuilder();
		jpql.append("from Restaurant where 0 = 0 ");
		
		var parametres = new HashMap<String, Object>();
		
		if (StringUtils.hasLength(nom)) {
			jpql.append("and nom like :nom ");
			parametres.put("nom", "%" + nom + "%");
		}
		
		if (fraisTransportInitial != null) {
			jpql.append("and fraisTransport >= :fraisInicial ");
			parametres.put("fraisInicial", fraisTransportInitial);
		}
		
		if (fraisTransportFinal != null) {
			jpql.append("and fraisTransport <= :fraisFinal ");
			parametres.put("fraisFinal", fraisTransportFinal);
		}
		
		TypedQuery<Restaurant> query = manager
				.createQuery(jpql.toString(), Restaurant.class);
		
		parametres.forEach((cle, valeur) -> query.setParameter(cle, valeur));

		return query.getResultList();
	}
	
}