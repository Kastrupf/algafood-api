package com.kastrupf.algafood;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.math.BigDecimal;

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
import com.kastrupf.algafood.domain.model.Restaurant;
import com.kastrupf.algafood.domain.repository.CuisineRepository;
import com.kastrupf.algafood.domain.repository.RestaurantRepository;
import com.kastrupf.algafood.util.DatabaseCleaner;
import com.kastrupf.algafood.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class RegistreRestaurantIT {

    private static final String MESSAGE_INCOMPREHENSIBLE_PROBLEM_TYPE = "Message incompréhensible";

    private static final String DONNEES_INVALIDES_PROBLEM_TITLE = "Données invalides";

    private static final int RESTAURANT_ID_INEXISTANT = 100;

    @LocalServerPort
    private int port;
    
    @Autowired
    private DatabaseCleaner databaseCleaner;
    
    @Autowired
    private CuisineRepository cuisines;
    
    @Autowired
    private RestaurantRepository restaurants;
    
    private String jsonRestaurantCorrect;
    private String jsonRestaurantSansFraisTransport;
    private String jsonRestaurantSansCuisine;
    private String jsonRestaurantAvecCuisineInnexistante;
    
    private Restaurant burgerTopRestaurant;
    
    @Before
    public void setUp() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/restaurants";

        jsonRestaurantCorrect = ResourceUtils.getContentFromResource(
                "/json/correct/restaurant-new-york-barbecue.json");
        
        jsonRestaurantSansFraisTransport = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurant-new-york-barbecue-sans-frais-transport.json");
        
        jsonRestaurantSansCuisine = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurante-new-york-barbecue-sans-cuisine.json");
        
        jsonRestaurantAvecCuisineInnexistante = ResourceUtils.getContentFromResource(
                "/json/incorrect/restaurante-new-york-barbecue-avec-cuisine-innexistante.json");
        
        databaseCleaner.clearTables();
        preparerDonnees();
    }
    
    @Test
    public void doitRetounerStatus200_QuandRechercherRestaurants() {
        given()
            .accept(ContentType.JSON)
        .when()
            .get()
        .then()
            .statusCode(HttpStatus.OK.value());
    }
    
    @Test
    public void doitRetounerStatus201_QuandEnregistrerRestaurant() {
        given()
            .body(jsonRestaurantCorrect)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.CREATED.value());
    }
    
    @Test
    public void doitRetounerStatus404_QuandRechercherRestaurantSansFraisDeTransport() {
        given()
            .body(jsonRestaurantSansFraisTransport)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", equalTo(DONNEES_INVALIDES_PROBLEM_TITLE));
    }

    @Test
    public void doitRetounerStatus404_QuandEnregistrerRestaurantSansCuisine() {
        given()
            .body(jsonRestaurantSansCuisine)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", equalTo(MESSAGE_INCOMPREHENSIBLE_PROBLEM_TYPE));
    }
    
    @Test
    public void doitRetounerStatus404_QuandEnregistrerRestaurantAvecCuisineInnexistante() {
        given()
            .body(jsonRestaurantAvecCuisineInnexistante)
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
        .when()
            .post()
        .then()
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .body("title", equalTo(MESSAGE_INCOMPREHENSIBLE_PROBLEM_TYPE));
    }
    
    @Test
    public void doitRetounerReponseEtStatusCorrects_QuandRechercherRestaurantExistant() {
        given()
            .pathParam("id", burgerTopRestaurant.getId())
            .accept(ContentType.JSON)
        .when()
            .get("/{id}")
        .then()
            .statusCode(HttpStatus.OK.value())
            .body("nom", equalTo(burgerTopRestaurant.getNom()));
    }
    
    @Test
    public void doitRetounerStatus404_QuandRechercherRestaurantInexistant() {
        given()
            .pathParam("id", RESTAURANT_ID_INEXISTANT)
            .accept(ContentType.JSON)
        .when()
            .get("/{id}")
        .then()
            .statusCode(HttpStatus.NOT_FOUND.value());
    }
    
    private void preparerDonnees() {
        Cuisine cuisineBresilienne = new Cuisine();
        cuisineBresilienne.setNom("Brésilienne");
        cuisines.save(cuisineBresilienne);

        Cuisine cuisineAmericaine = new Cuisine();
        cuisineAmericaine.setNom("Americaine");
        cuisines.save(cuisineAmericaine);
        
        burgerTopRestaurant = new Restaurant();
        burgerTopRestaurant.setNom("Burger Top");
        burgerTopRestaurant.setFraisTransport(new BigDecimal(10));
        burgerTopRestaurant.setCuisine(cuisineAmericaine);
        restaurants.save(burgerTopRestaurant);
        
        Restaurant CariocaRestaurant = new Restaurant();
        CariocaRestaurant.setNom("Carioca");
        CariocaRestaurant.setFraisTransport(new BigDecimal(10));
        CariocaRestaurant.setCuisine(cuisineBresilienne);
        restaurants.save(CariocaRestaurant);
    }            
}