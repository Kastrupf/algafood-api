package com.kastrupf.algafood.api.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kastrupf.algafood.domain.exception.EntiteEnCoursUtilisationException;
import com.kastrupf.algafood.domain.exception.EntiteNonTrouveeException;
import com.kastrupf.algafood.domain.exception.GeneriqueException;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ErreurType erreurType = ErreurType.MESSAGE_INCOMPREHENSIBLE;
		String detail = "Le corps de la demande n'est pas valide. Vérifier l'erreur de syntaxe.";
		
		Erreur erreur = createErreurBuilder(status, erreurType, detail).build();
		
		return handleExceptionInternal(ex, erreur, new HttpHeaders(), status, request);
	}
	
	
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
	public ResponseEntity<?> handleEntiteEnCoursUtilisationException(
			EntiteEnCoursUtilisationException ex, WebRequest request) {
	    
	    HttpStatus status = HttpStatus.CONFLICT;
	    ErreurType erreurType = ErreurType.ENTITE_EN_COURS_UTILISATION;
	    String detail = ex.getMessage();
	    
	    Erreur erreur = createErreurBuilder(status, erreurType, detail).build();
	    
	    return handleExceptionInternal(ex, erreur, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(GeneriqueException.class)
	public ResponseEntity<?> handleNegocioException(GeneriqueException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ErreurType erreurType = ErreurType.ERREUR_GENERIQUE;
	    String detail = ex.getMessage();
	    
	    Erreur erreur = createErreurBuilder(status, erreurType, detail).build();
						
	    return handleExceptionInternal(ex, erreur, new HttpHeaders(), status, request);
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