package com.kastrupf.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND) 
public class EntiteNonTrouveeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
			
	public EntiteNonTrouveeException(String message) {
		super(message);
	}
}
