package com.kastrupf.algafood.domain.exception;

public abstract class EntiteNonTrouveeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
			
	public EntiteNonTrouveeException(String message) {
		super(message);
	}
}
