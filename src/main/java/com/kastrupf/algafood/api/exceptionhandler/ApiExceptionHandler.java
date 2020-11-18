package com.kastrupf.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kastrupf.algafood.domain.exception.EntiteNonTrouveeException;
import com.kastrupf.algafood.domain.exception.GeneriqueException;


@ControllerAdvice
public class ApiExceptionHandler {
	
	@ExceptionHandler(EntiteNonTrouveeException.class)
	public ResponseEntity<?> tratarEntidadeNaoEncontradaException(
			EntiteNonTrouveeException e) {
		Erreur erreur = Erreur.builder()
				.dateHeure(LocalDateTime.now())
				.message(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(erreur);
	}
	
	@ExceptionHandler(GeneriqueException.class)
	public ResponseEntity<?> tratarNegocioException(GeneriqueException e) {
		Erreur erreur = Erreur.builder()
				.dateHeure(LocalDateTime.now())
				.message(e.getMessage()).build();
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(erreur);
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<?> traiterHttpMediaTypeNotSupportedException() {
		Erreur erreur = Erreur.builder()
				.dateHeure(LocalDateTime.now())
				.message("Le type de support n'est pas pris en charge.").build();
		
		return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
				.body(erreur);
	}
	
}

