package com.kastrupf.algafood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST) 
public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;
			
	public GenericException(String message) {
		super(message);
	}
}
