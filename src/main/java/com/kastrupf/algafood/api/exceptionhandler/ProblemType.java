package com.kastrupf.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	PARAMETRE_INVALIDE("/parametre-invalide", "Paramètre invalide"),
	MESSAGE_INCOMPREHENSIBLE("/message-incompréhensible", "Message incompréhensible"),
	ENTITE_NON_TROUVEE("/entite-non-trouvee", "Entite non trouvée"),
	ENTITE_EN_COURS_UTILISATION("/entite-en-cours-utilisation", "Entite en cours d'utlisation"),
	ERREUR_GENERIQUE("/erreur-generique", "Violation de règle métier"); 
	
	private String title;
	private String uri;	
	
	ProblemType(String path, String title) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}
}
