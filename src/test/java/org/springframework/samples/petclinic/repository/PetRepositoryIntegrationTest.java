package org.springframework.samples.petclinic.repository;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.samples.petclinic.PetClinicApplication;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PetClinicApplication.class)
public class PetRepositoryIntegrationTest {
	@Autowired
	private PetRepository petRepository;
	
	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void testFindByName() {
		Pet petBuscada = new Pet();
		petBuscada.setId(1);
		petBuscada.setName("Leo");
		Date date1 = new Date();
				date1.setYear(110);
				date1.setMonth(9);
				date1.setDate(7);
		petBuscada.setBirthDate(date1);
		PetType petType1 = new PetType();
		petType1.setId(1);
		petType1.setName("cat");
		petBuscada.setType(petType1);
		Owner owner1 = new Owner();
		owner1.setId(1);
		owner1.setFirstName("George");
		owner1.setLastName("Franklin");
		owner1.setAddress("110 W. Liberty St.");
		owner1.setCity("Madison");
		owner1.setTelephone("6085551023");
		petBuscada.setOwner(owner1);
		
		Pet pet = petRepository.findByName(petBuscada.getName());
		Assert.assertEquals(petBuscada, pet);
	}

}
