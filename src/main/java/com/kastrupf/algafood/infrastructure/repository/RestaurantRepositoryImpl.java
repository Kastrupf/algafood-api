package com.kastrupf.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

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
		var builder = manager.getCriteriaBuilder();
		
		var criteria = builder.createQuery(Restaurant.class);
		var root = criteria.from(Restaurant.class);

		var predicates = new ArrayList<Predicate>();
		
		if (StringUtils.hasText(nom)) {
			predicates.add(builder.like(root.get("nom"), "%" + nom + "%"));
		}
		
		if (fraisTransportInitial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("fraisTransport"), fraisTransportInitial));
		}
		
		if (fraisTransportFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("fraisTransport"), fraisTransportFinal));
		}
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		var query = manager.createQuery(criteria);
		return query.getResultList();
	}
		
	
/*	@Override
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
*/
	
}