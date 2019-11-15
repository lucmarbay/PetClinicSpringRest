package org.springframework.samples.petclinic.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.dto.PetDTO;
import org.springframework.samples.petclinic.services.PetServiceImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@WebMvcTest(PetController.class)
public class PetControllerTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	ObjectMapper objectMapper;

	@MockBean
	private PetServiceImpl petService;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAllPets() throws Exception {

		PetDTO pet = new PetDTO();
		pet.setName("Alex");

		List<PetDTO> allPets = Arrays.asList(pet);

//	    when(petService.findAll()).thenReturn(allPets);

		given(petService.findAll()).willReturn(allPets);

		mvc.perform(get("/pet/").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].name", is(pet.getName())));

	}

	@Test
	public void testFindById() throws Exception {
		PetDTO pet = new PetDTO();
		pet.setId(1);
		pet.setName("Alex");

		given(petService.findById(anyInt())).willReturn(pet);

		mvc.perform(get("/pet/{id}", 1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1))).andExpect(jsonPath("$.name", is(pet.getName())));

		verify(petService, times(1)).findById(1);

	}

	@Test
	public void testSavePet() throws Exception {

		PetDTO pet = new PetDTO();
//		pet.setId(1);
		pet.setName("Alex");

//		Gson gson = new Gson();
//		String jsonRecibido = gson.toJson(pet);

//		given(petService.save(any(PetDTO.class))).willReturn(pet);
		when(petService.save(any(PetDTO.class))).thenReturn(pet);

		mvc.perform(post("/pet/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(pet)))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE))
//				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Alex")));

		verify(petService, times(1)).save(pet);
		Assert.assertEquals(pet, petService.save(pet));

	}

	@Test
	public void testUpdatePet() throws JsonProcessingException, Exception {
		PetDTO pet = new PetDTO();
		pet.setId(1);
		pet.setName("Alex");
//		given(petService.updatePet(anyInt(), any(PetDTO.class))).willReturn(pet);
		when(petService.updatePet(anyInt(), any(PetDTO.class))).thenReturn(pet);

		mvc.perform(put("/pet/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(pet)))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is("Alex")));
		
		verify(petService, times(1)).updatePet(1, pet);
		Assert.assertEquals(pet, petService.updatePet(1, pet));
	}

	@Test
	public void testDeletePet() throws Exception {
		PetDTO pet = new PetDTO();
		pet.setId(1);
		pet.setName("Alex");
		given(petService.delete(anyInt())).willReturn(pet);
		
		mvc.perform(delete("/pet/{id}", 1)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(pet.getId())))
				.andExpect(jsonPath("$.name", is(pet.getName())));
		verify(petService, times(1)).delete(pet.getId());
		Assert.assertEquals(pet, petService.delete(1));
	}

}
