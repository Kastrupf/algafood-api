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

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RegistreCuisineIT {
	
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
	public void doitAvoir2Cuisines_QuandRechercherCuisines() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(2));
//		.body("nom", hasItems("Thailandaise", "Anglaise"));
	}
	
	@Test
	public void doitRetounerStatus201_QuandEnregistrerCuisine() {
		given()
			.body("{ \"nom\": \"Brésilienne\" }")
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
			.pathParam("id", 2)
			.accept(ContentType.JSON)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nom", equalTo("Anglaise"));
	}
	
	@Test
	public void doitRetounerStatus404_QuandRechercherCuisineInexistante() {
		given()
			.pathParam("id", 666)
			.accept(ContentType.JSON)
		.when()
			.get("/{id}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
		
	private void preparerDonnees() {
		Cuisine cuisine1 = new Cuisine();
		cuisine1.setNom("Thailandaise");
		cuisines.save(cuisine1);
		
		Cuisine cuisine2 = new Cuisine();
		cuisine2.setNom("Anglaise");
		cuisines.save(cuisine2);
		
	}
	
	
	
	
	
	
}