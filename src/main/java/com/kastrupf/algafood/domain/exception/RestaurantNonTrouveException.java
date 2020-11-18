package com.kastrupf.algafood.domain.exception;

public class RestaurantNonTrouveException extends EntiteNonTrouveeException {

	private static final long serialVersionUID = 1L;
			
	public RestaurantNonTrouveException(String message) {
		super(message);
	}
	
	public RestaurantNonTrouveException(Long id) {
		this(String.format("Il n'y a pas de registre de restaurant avec le code %d", id));
	}
}
