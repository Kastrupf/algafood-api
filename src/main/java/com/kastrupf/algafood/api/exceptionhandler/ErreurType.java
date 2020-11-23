package com.kastrupf.algafood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ErreurType {
	
	ENTITE_NON_TROUVEE("/entite-non-trouvee", "Entite non trouvee");
	
	private String title;
	private String uri;	
	
	ErreurType(String path, String title) {
		this.uri = "https://algafood.com.br" + path;
		this.title = title;
	}

}
