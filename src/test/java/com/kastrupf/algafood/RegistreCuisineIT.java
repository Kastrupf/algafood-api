package com.kastrupf.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistreCuisineIT {
	
		@LocalServerPort
		private int port;
		
		@Before
		public void setUp() {
			RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
			RestAssured.port = port;
			RestAssured.basePath = "/cuisines";
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
	public void doitAvoir4Cuisines_QuandRechercherCuisines() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(4));
//		.body("nom", hasItems("Française", "Italienne"));
	}
}
