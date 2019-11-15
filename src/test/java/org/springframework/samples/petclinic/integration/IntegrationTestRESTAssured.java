package org.springframework.samples.petclinic.integration;

import static org.hamcrest.CoreMatchers.equalTo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.controllers.PetController;
import org.springframework.samples.petclinic.dto.PetDTO;
import org.springframework.samples.petclinic.mapper.PetMapperImpl;
import org.springframework.samples.petclinic.services.IPetService;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.classmate.GenericType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.module.mockmvc.response.ValidatableMockMvcResponse;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import io.restassured.response.ResponseOptions;
import junit.framework.Assert;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = RANDOM_PORT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTestRESTAssured {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void initialiseRestAssuredMockMvcWebApplicationContext() {
		RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
	}

	@Test
	public void dadoElParametroIdIgualA1LanzadoContraFindByIdElResultadoEsperadoEsLeo() {

		RestAssuredMockMvc
		.given()
			.contentType("application/json")
	    .when()
	        .get("/pet/1")
	    .then()
	    	.statusCode(200)
	    	.body("name", equalTo("Leo"));
	}
	
	@Test
	public void lanzandoContraFindAllComparamosElPrimerRegistroYElUltimoRegistro() {
		
		RestAssuredMockMvc
		.given()
			.contentType("application/json")
	    .when()
	        .get("/pet/")
	    .then()
	    	.body("name[0]", equalTo("Leo"))
	    	.body("name[12]", equalTo("Sly"));
	}
	
	@Test
	public void dadoElNuevoRegistroLanzadoContraSavePetElResultadoEsperadoEsAlex() {
		PetDTO petDTO = new PetDTO();
		petDTO.setName("Alex");
		
		RestAssuredMockMvc
		.given()
			.contentType("application/json")
			.body(petDTO)
	    .when()
	        .post("/pet/")
	    .then()
	    	.body("name", equalTo("Alex"));
		
	}
}
