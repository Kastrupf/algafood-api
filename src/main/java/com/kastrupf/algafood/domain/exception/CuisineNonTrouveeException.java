package com.kastrupf.algafood.domain.exception;

public class CuisineNonTrouveeException extends EntiteNonTrouveeException {

	private static final long serialVersionUID = 1L;
			
	public CuisineNonTrouveeException(String message) {
		super(message);
	}
	
	public CuisineNonTrouveeException(Long id) {
		this(String.format("Il n'y a pas de registre de cuisine avec le code %d", id));
	}
}
