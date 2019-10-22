package org.springframework.samples.petclinic.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
public class IntegrationTestRestTemplate {

	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void savePet() throws Exception{

		PetDTO pet = new PetDTO();
		pet.setName("Alex");

		ResponseEntity<PetDTO> responsePetDTO = restTemplate
				.postForEntity("/pet/", pet, PetDTO.class);

		PetDTO response = responsePetDTO.getBody();

		assertThat(response.getName(), is(pet.getName()));
	}
	@Test
	public void findAll() {
		
		ResponseEntity<List> response = restTemplate
				.getForEntity("/pet/",List.class);
		List<PetDTO> responseListPetDTO = (List<PetDTO>) response.getBody();
		
		
		Assert.assertNotNull(responseListPetDTO);
		Assert.assertEquals(13, responseListPetDTO.size());
		

		
		
	}

}
