package com.kastrupf.algafood.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.kastrupf.algafood.domain.model.Restaurant;

public class RestaurantSpecs {

	public static Specification<Restaurant> avecFraisDeTransportGratuit() {
		return (root, query, builder) -> 
			builder.equal(root.get("fraisTransport"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurant> avecNomSimilaire(String nom) {
		return (root, query, builder) ->
			builder.like(root.get("nom"), "%" + nom + "%");
	}
}
