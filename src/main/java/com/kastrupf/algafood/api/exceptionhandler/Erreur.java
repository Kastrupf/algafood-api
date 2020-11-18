package com.kastrupf.algafood.api.exceptionhandler;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Erreur {
	
	private LocalDateTime dateHeure;
	private String message;

}
