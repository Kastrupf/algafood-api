package com.kastrupf.algafood.domain.exception;

public class RegionNonTrouveeException extends EntiteNonTrouveeException {

	private static final long serialVersionUID = 1L;
			
	public RegionNonTrouveeException(String message) {
		super(message);
	}
	
	public RegionNonTrouveeException(Long id) {
		this(String.format("Il n'y a pas de registre de région avec le code %d", id));
	}
}
