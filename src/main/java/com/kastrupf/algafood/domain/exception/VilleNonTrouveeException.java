package com.kastrupf.algafood.domain.exception;

public class VilleNonTrouveeException extends EntiteNonTrouveeException {

	private static final long serialVersionUID = 1L;
			
	public VilleNonTrouveeException(String message) {
		super(message);
	}
	
	public VilleNonTrouveeException(Long id) {
		this(String.format("Il n'y a pas de registre de ville avec le code %d", id));
	}
}
