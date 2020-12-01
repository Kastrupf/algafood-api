package com.kastrupf.algafood;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kastrupf.algafood.domain.exception.CuisineNonTrouveeException;
import com.kastrupf.algafood.domain.exception.EntiteEnCoursUtilisationException;
import com.kastrupf.algafood.domain.model.Cuisine;
import com.kastrupf.algafood.domain.service.RegistreCuisineService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegistreCuisineIT {

	@Autowired
	private RegistreCuisineService registreCuisine;
	
	@Test(expected = EntiteEnCoursUtilisationException.class)
	public void neDoitPasSupprimerLaCuisine_QuandElleEstEnCoursDutilisation() {
	    registreCuisine.supprimer(1L);
	}
	
	@Test(expected = CuisineNonTrouveeException.class)
	public void neDoitPasSupprimerLaCuisine_QuandElleEstInexistente() {
		registreCuisine.supprimer(100L);
	}    
	  	
	@Test
	public void doitAjouterCuisine_QuandUnNomLuiEstAttibué() {
		// scenario
		Cuisine nouvelleCuisine = new Cuisine();
		nouvelleCuisine.setNom("Chinoise");
		
		// action
		nouvelleCuisine = registreCuisine.ajouter(nouvelleCuisine);
		
		// validation
		assertThat(nouvelleCuisine).isNotNull();
		assertThat(nouvelleCuisine.getId()).isNotNull();
	}
	
//	@Test(expected = ConstraintViolationException.class)
//	public void neDoitPasAjouterCuisine_QuandLeNomNestPasAttribué() {
//		Cuisine nouvelleCuisine = new Cuisine();
//		nouvelleCuisine.setNom(null);
		
//		nouvelleCuisine = registreCuisine.ajouter(nouvelleCuisine);
//	}
}

