package org.springframework.samples.petclinic.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;

import org.springframework.samples.petclinic.dto.PetDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void savePet() throws Exception{

		PetDTO pet = new PetDTO();
		pet.setName("Alex");

		ResponseEntity<PetDTO> responsePetDTO = restTemplate
				.postForEntity("/pet/", pet, PetDTO.class);

		PetDTO petDTO = responsePetDTO.getBody();

		assertThat(petDTO.getName(), is(pet.getName()));
	}
	@Test
	public void findAll() {
		
		PetDTO pet = new PetDTO();
		pet.setName("Alex");

		List<PetDTO> allPets = Arrays.asList(pet);
		
	}

}
