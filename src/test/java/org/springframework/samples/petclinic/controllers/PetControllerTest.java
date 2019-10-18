package org.springframework.samples.petclinic.controllers;

import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.dto.PetDTO;
import org.springframework.samples.petclinic.services.PetServiceImpl;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import junit.framework.Assert;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
@RunWith(SpringRunner.class)
@WebMvcTest(PetController.class)
public class PetControllerTest {
	@Autowired
    private MockMvc mvc;
 
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
	    
	    
	    mvc.perform(get("/pet")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$", hasSize(1)))
	      .andExpect(jsonPath("$[1].name", is(pet.getName())));
	    
	}

	@Test
	public void testFindById() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateOrSavePet() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePet() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeletePet() {
		fail("Not yet implemented");
	}

}
