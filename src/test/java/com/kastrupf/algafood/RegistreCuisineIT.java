package com.kastrupf.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.kastrupf.algafood.domain.model.Cuisine;
import com.kastrupf.algafood.domain.repository.CuisineRepository;
import com.kastrupf.algafood.util.DatabaseCleaner;
import com.kastrupf.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RegistreCuisineIT {
	
	private static final int CUISINE_ID_INNEXISTENT = 100;

	private Cuisine cuisineAmericaine;
	private int quantiteCuisinesEnregistrees;
	private String jsonCorrectCuisineChinoise;
			
	@LocalServerPort
	private int port;
		
	@Autowired
	private DatabaseCleaner databaseCleaner;
		
	@Autowired
	private CuisineRepository cuisines;
		
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/cuisines";
		
		databaseCleaner.clearTables();
		preparerDonnees();
		
		jsonCorrectCuisineChinoise = ResourceUtils.getContentFromResource(
				"/json/correct/cuisine-chinoise.json");
	}

	@Test
	public void doitRetounerStatus200_QuandRechercherCuisines() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void doitRetournerLaQuantiteCorrectDeCuisines_QuandRechercherCuisines() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(quantiteCuisinesEnregistrees));

	}
	
	@Test
	public void doitRetounerStatus201_QuandEnregistrerCuisine() {
		given()
			.body(jsonCorrectCuisineChinoise)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void doitRetounerReponseEtStatusCorrects_QuandRechercherCuisineExistante() {
		given()
			.pathParam("id", cuisineAmericaine.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nom", equalTo(cuisineAmericaine.getNom()));
	}
	
	@Test
	public void doitRetounerStatus404_QuandRechercherCuisineInexistante() {
		given()
			.pathParam("id", CUISINE_ID_INNEXISTENT)
			.accept(ContentType.JSON)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
		
	private void preparerDonnees() {
		Cuisine cuisineThailandaise = new Cuisine();
		cuisineThailandaise.setNom("Thailandaise");
	    cuisines.save(cuisineThailandaise);

	    cuisineAmericaine = new Cuisine();
	    cuisineAmericaine.setNom("Americaine");
	    cuisines.save(cuisineAmericaine);
	    
	    quantiteCuisinesEnregistrees = (int) cuisines.count();
	}
}