package com.kastrupf.algafood.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kastrupf.algafood.domain.exception.EntiteEnCoursUtilisationException;
import com.kastrupf.algafood.domain.exception.EntiteNonTrouveeException;
import com.kastrupf.algafood.domain.exception.GeneriqueException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String MSG_ERREUR_GENERIQUE_UTILISATEUR_FINAL = "Une erreur système interne inattendue s'est produite."
			+ " Réessayez et si le problème persiste, contactez-nous"
			+ "avec l'administrateur système.";

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
	    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;		
	    ProblemType problemType = ProblemType.ERREUR_SYSTEME;
	    String detail = MSG_ERREUR_GENERIQUE_UTILISATEUR_FINAL;

	    // Importante colocar o printStackTrace (pelo menos por enquanto, que não estamos
	    // fazendo logging) para mostrar a stacktrace no console
	    // Se não fizer isso, você não vai ver a stacktrace de exceptions que seriam importantes
	    // para você durante, especialmente na fase de desenvolvimento
	    ex.printStackTrace();
	    
	    Problem problem = createProblemBuilder(status, problemType, detail)
	    		.userMessage(detail)
	    		.build();

	    return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}                      
		
	@ExceptionHandler(EntiteNonTrouveeException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontrada(
			EntiteNonTrouveeException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.RESSOURCE_NON_TROUVE;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(EntiteEnCoursUtilisationException.class)
	public ResponseEntity<?> handleEntidadeEmUso(
			EntiteEnCoursUtilisationException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTITE_EN_COURS_UTILISATION;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(GeneriqueException.class)
	public ResponseEntity<?> handleGenerique(GeneriqueException ex, WebRequest request) {

		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.ERREUR_GENERIQUE;
		String detail = ex.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.build();
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.DONNEE_INVALIDE;
		String detail = "Un ou plusieurs champs ne sont pas valides. Remplissez correctement et réessayez.";
		
		BindingResult bindingResult = ex.getBindingResult();
		
		List<Problem.Field> problemFields = bindingResult.getFieldErrors().stream()
				.map(fieldError -> Problem.Field.builder()
						.name(fieldError.getField())
						.userMessage(fieldError.getDefaultMessage())
						.build())
				.collect(Collectors.toList());
				
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(detail)
				.fields(problemFields)
				.build();	
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
		
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		
		ProblemType problemType = ProblemType.RESSOURCE_NON_TROUVE;
		
		String detail = String.format("La ressource %s, à laquelle vous avez essayé d'accéder, n'existe pas.",
	           ex.getRequestURL());
		
		Problem problem = createProblemBuilder(status, problemType, detail).build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
		
	// MethodArgumentTypeMismatch est un soustype de TypeMismatchException.
	// ResponseEntityExceptionHandler gere deja TypeMismatchException d'une certaine maniere, mais d'une façon plus generique.
	// Ensuite, on specialise la methode handleTypeMismatch et on verifie si l'exception est une instance de MethodArgumentTypeMismatchException.
	// Si tel est le cas, on appelle une methode especialisee pour gerer ce type d'exception.
	// Nous pourrions tout faire dans handleTypeMismatch, mais j'ai prefere le separer dans une autre methode.
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
	        HttpStatus status, WebRequest request) {
	    
	    if (ex instanceof MethodArgumentTypeMismatchException) {
	        return handleMethodArgumentTypeMismatch(
	                (MethodArgumentTypeMismatchException) ex, headers, status, request);
	    }

	    return super.handleTypeMismatch(ex, headers, status, request);
	}
			
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable rootCause = ExceptionUtils.getRootCause(ex);
		
		if (rootCause instanceof InvalidFormatException) {
			return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
		} else if (rootCause instanceof PropertyBindingException) {
	        return handlePropertyBinding((PropertyBindingException) rootCause, headers, status, request); 
	    }
		
		ProblemType problemType = ProblemType.MESSAGE_INCOMPREHENSIBLE;
		String detail = "Le corps de la demande n'est pas valide. Vérifiez l'erreur de syntaxe.";
		
	    Problem problem = createProblemBuilder(status, problemType, detail)
	    		.userMessage(MSG_ERREUR_GENERIQUE_UTILISATEUR_FINAL)
	    		.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null) {
			body = Problem.builder()
				.timestamp(LocalDateTime.now())	
				.title(status.getReasonPhrase())
				.status(status.value())
				.build();
		} else if (body instanceof String) {
			body = Problem.builder()
				.timestamp(LocalDateTime.now())	
				.title((String) body)
				.status(status.value())
				.build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}	
	
	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
	        MethodArgumentTypeMismatchException ex, HttpHeaders headers,
	        HttpStatus status, WebRequest request) {

	    ProblemType problemType = ProblemType.PARAMETRE_INVALIDE;

	    String detail = String.format("Le paramètre d'URL '%s' a reçu la valeur '%s',"
	    		+ " qui est un type non valide. Veuillez corriger et entrer une valeur compatible avec le type %s.",
	            ex.getName(), ex.getValue(), ex.getRequiredType().getSimpleName());

	    Problem problem = createProblemBuilder(status, problemType, detail).build();

	    return handleExceptionInternal(ex, problem, headers, status, request);
	}
		
	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String path = joinPath(ex.getPath());
		
		ProblemType problemType = ProblemType.MESSAGE_INCOMPREHENSIBLE;
		String detail = String.format("On dit que la propriété '%s' est '%s', \"\r\n"
				+ "+ \"c'est un type non valide. Veuillez corriger et entrez une vallée compatible avec le type %s.",
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMessage(MSG_ERREUR_GENERIQUE_UTILISATEUR_FINAL)
				.build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
				
	private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
	        HttpHeaders headers, HttpStatus status, WebRequest request) {

		// J'ai cree la methode joinPath a reutiliser dans toutes les methodes qui necessitent
		// concatene les noms des proprietes (en separant par ".")
	    String path = joinPath(ex.getPath());
	    
	    ProblemType problemType = ProblemType.MESSAGE_INCOMPREHENSIBLE;
	    String detail = String.format("La propriété '%s' n'existe pas."
	    		+ " Corrigez ou supprimez cette propriété et réessayez.", path);

	    Problem problem = createProblemBuilder(status, problemType, detail)
	    		.userMessage(MSG_ERREUR_GENERIQUE_UTILISATEUR_FINAL)
	    		.build();
	    
	    return handleExceptionInternal(ex, problem, headers, status, request);
	}        
		
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status,
			ProblemType problemType, String detail) {
		
		return Problem.builder()
			.timestamp(LocalDateTime.now())
			.status(status.value())
			.type(problemType.getUri())
			.title(problemType.getTitle())
			.detail(detail);
	}
			
	private String joinPath(List<Reference> references) {
	    return references.stream()
	        .map(ref -> ref.getFieldName())
	        .collect(Collectors.joining("."));
	} 
}