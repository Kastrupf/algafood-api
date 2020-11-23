package com.kastrupf.algafood.api.exceptionhandler;

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
	
	@ExceptionHandler(EntiteNonTrouveeException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(
			EntiteNonTrouveeException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ErreurType erreurType = ErreurType.ENTITE_NON_TROUVEE;
		String detail = ex.getMessage();
		
		Erreur erreur = createErreurBuilder(status, erreurType, detail).build();
		
		return handleExceptionInternal(ex, erreur, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntiteEnCoursUtilisationException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(
			EntiteEnCoursUtilisationException ex, WebRequest request) {
		
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
				HttpStatus.CONFLICT, request);
	}
	
	@ExceptionHandler(GeneriqueException.class)
	public ResponseEntity<?> handleNegocioException(GeneriqueException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), 
				HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		if (body == null) {
			body = Erreur.builder()
				.title(status.getReasonPhrase())
				.status(status.value())
				.build();
		} else if (body instanceof String) {
			body = Erreur.builder()
				.title((String) body)
				.status(status.value())
				.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
	
	private Erreur.ErreurBuilder createErreurBuilder(HttpStatus status,
			ErreurType erreurType, String detail) {
		
		return Erreur.builder()
			.status(status.value())
			.type(erreurType.getUri())
			.title(erreurType.getTitle())
			.detail(detail);
	}
}