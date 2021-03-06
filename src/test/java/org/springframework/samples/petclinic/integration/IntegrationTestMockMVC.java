package org.springframework.samples.petclinic.integration;


import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.samples.petclinic.PetClinicApplication;
import org.springframework.samples.petclinic.dto.PetDTO;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import junit.framework.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PetClinicApplication.class })
@WebAppConfiguration
public class IntegrationTestMockMVC {

	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private MockMvc mockMvc;
	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	//Test para verificar que se carga correctamente el WebApplicationContext (wac) 
	@Test
	public void givenWac_whenServletContext_thenItProvidesPetController() {
	    ServletContext servletContext = wac.getServletContext();
	     
	    Assert.assertNotNull(servletContext);
	    Assert.assertTrue(servletContext instanceof MockServletContext);
	    Assert.assertNotNull(wac.getBean("petController"));
	}
	
	@Test
	public void findAllTest() throws Exception {
		this.mockMvc.perform(get("/pet/"))
	            .andExpect(status().isOk())
				.andExpect(jsonPath("$[0].name", is("Leo")))
				.andExpect(jsonPath("$[12].name", is("Sly")));
	}
	@Test
	public void savePetTest() throws Exception {
		PetDTO pet = new PetDTO();
		pet.setName("Alex");
		
		this.mockMvc.perform(post("/pet/")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(pet)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name", is("Alex")));
	}
}
