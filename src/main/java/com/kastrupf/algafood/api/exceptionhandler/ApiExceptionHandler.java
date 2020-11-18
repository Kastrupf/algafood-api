package com.kastrupf.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kastrupf.algafood.domain.exception.EntiteEnCoursUtilisationException;
import com.kastrupf.algafood.domain.exception.EntiteNonTrouveeException;
import com.kastrupf.algafood.domain.exception.GeneriqueException;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(EntiteEnCoursUtilisationException.class)
	public ResponseEntity<?> traiterEntiteEnCoursUtilisationException(
			EntiteNonTrouveeException ex, WebRequest request) {
		
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
				HttpStatus.CONFLICT, request);
	}
		
	@ExceptionHandler(EntiteNonTrouveeException.class)
	public ResponseEntity<?> traiterEntiteNonTrouveeException(
			EntiteNonTrouveeException ex, WebRequest request) {
		
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
				HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(GeneriqueException.class)
	public ResponseEntity<?> traiterGeneriqueException(GeneriqueException ex, WebRequest request) {
		
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
				HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
/* Si je reçois un body nul, le message d'état est par défaut, sinon, je retourne un body avec une String dans le message d'erreur */		
		if (body == null) {
			body = Erreur.builder()
				.dateHeure(LocalDateTime.now())
				.message(status.getReasonPhrase())
				.build();						
			} else if (body instanceof String) {
				body = Erreur.builder()
						.dateHeure(LocalDateTime.now())
						.message((String) body)
						.build();		
			}
						
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	
}

