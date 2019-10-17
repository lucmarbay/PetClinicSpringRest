package org.springframework.samples.petclinic.services;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.samples.petclinic.dto.PetDTO;
import org.springframework.samples.petclinic.mapper.PetMapper;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class PetServiceImplTest {
	
//	@Autowired
	private PetServiceImpl petService;
	
//	@MockBean
	private PetRepository petRepositoryMock;
	
	@Before
	public void setUp() throws Exception {
		petRepositoryMock = Mockito.mock(PetRepository.class);
		petService = new PetServiceImpl(petRepositoryMock);
	}

	@Test
	public void testFindAll() {
		Pet pet1 = new Pet();
		pet1.setId(1);
		pet1.setName("Pepe");
		Date date1 = new Date(2000, 9, 7);
		pet1.setBirthDate(date1);
		PetType petType1 = new PetType();
		petType1.setId(1);
		petType1.setName("shark");
		pet1.setType(petType1);
		Owner owner1 = new Owner();
		owner1.setId(1);
		owner1.setFirstName("asd");
		owner1.setLastName("fgh");
		owner1.setAddress("calle sin nombre");
		owner1.setCity("Sevilla");
		owner1.setTelephone("666666666");
		pet1.setOwner(owner1);
		Pet pet2 = new Pet();
		pet2.setId(2);
		pet2.setName("Pepe2");
		Date date2 = new Date(2000, 9, 7);
		pet2.setBirthDate(date2);
		PetType petType2 = new PetType();
		petType2.setId(2);
		petType2.setName("shark2");
		pet2.setType(petType2);
		Owner owner2 = new Owner();
		owner2.setId(2);
		owner2.setFirstName("asd");
		owner2.setLastName("fgh");
		owner2.setAddress("calle sin nombre");
		owner2.setCity("Sevilla");
		owner2.setTelephone("666666666");
		pet2.setOwner(owner2);
		
		List<PetDTO> listaEsperada = new ArrayList<>();
		List<Pet> petList = new ArrayList<>();
		petList.add(pet1);
		petList.add(pet2);
		Iterator<Pet> it = petList.iterator();
		while(it.hasNext()) {
			Pet pet = it.next();
			listaEsperada.add(PetMapper.INSTANCE.petToPetDTO(pet));
		}
		
		Mockito.when(petRepositoryMock.findAll()).thenReturn(petList);
		Assert.assertEquals(listaEsperada, petService.findAll());
		
	}

	@Test
	public void testSave() {
		Pet pet1 = new Pet();
		pet1.setId(1);
		pet1.setName("Pepe");
		Date date1 = new Date(2000, 9, 7);
		pet1.setBirthDate(date1);
		PetType petType1 = new PetType();
		petType1.setId(1);
		petType1.setName("shark");
		pet1.setType(petType1);
		Owner owner1 = new Owner();
		owner1.setId(1);
		owner1.setFirstName("asd");
		owner1.setLastName("fgh");
		owner1.setAddress("calle sin nombre");
		owner1.setCity("Sevilla");
		owner1.setTelephone("666666666");
		pet1.setOwner(owner1);
		
		PetDTO petDTOResponse=PetMapper.INSTANCE.petToPetDTO(pet1);
		
		Mockito.when(petRepositoryMock.save(any(Pet.class))).thenReturn(pet1);
		Assert.assertEquals(petDTOResponse, petService.save(petDTOResponse));
		

	}

	@Test
	public void testFindById() {
		Pet pet1 = new Pet();
		pet1.setId(1);
		pet1.setName("Pepe");
		Date date1 = new Date(2000, 9, 7);
		pet1.setBirthDate(date1);
		PetType petType1 = new PetType();
		petType1.setId(1);
		petType1.setName("shark");
		pet1.setType(petType1);
		Owner owner1 = new Owner();
		owner1.setId(1);
		owner1.setFirstName("asd");
		owner1.setLastName("fgh");
		owner1.setAddress("calle sin nombre");
		owner1.setCity("Sevilla");
		owner1.setTelephone("666666666");
		pet1.setOwner(owner1);
		PetDTO petDTOResponse=PetMapper.INSTANCE.petToPetDTO(pet1);
		
		Mockito.when(petRepositoryMock.getOne(any())).thenReturn(pet1);
		Assert.assertEquals(petDTOResponse, petService.findById(1));
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdatePet() {
		fail("Not yet implemented");
	}

}
