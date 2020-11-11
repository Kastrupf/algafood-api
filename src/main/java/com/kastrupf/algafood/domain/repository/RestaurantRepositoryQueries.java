package com.kastrupf.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.kastrupf.algafood.domain.model.Restaurant;

public interface RestaurantRepositoryQueries {

	List<Restaurant> find(String nom, 
			BigDecimal fraisTransportInitial, BigDecimal fraisTransportFinal);

}