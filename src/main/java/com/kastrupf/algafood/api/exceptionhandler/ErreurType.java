package com.kastrupf.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ErreurType {
	
	ENTITE_NON_TROUVEE("/entite-non-trouvee", "Entite non trouvee"),
	ENTITE_EN_COURS_UTILISATION("/entite-en-cours-utilisation", "Entite en cours d'utlisation"),
	ERREUR_GENERIQUE("/erreur-generique", "Violation de règle métier"); 
	
	private String title;
	private String uri;	
	
	ErreurType(String path, String title) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}
}
