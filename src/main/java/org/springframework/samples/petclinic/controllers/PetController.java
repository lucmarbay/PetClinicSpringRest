package org.springframework.samples.petclinic.controllers;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.dto.OwnerDTO;
import org.springframework.samples.petclinic.dto.PetDTO;
import org.springframework.samples.petclinic.error.CustomPetException;
import org.springframework.samples.petclinic.services.IPetService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//https://dzone.com/articles/spring-boot-2-restful-api-documentation-with-swagg
@RestController
@RequestMapping("/pet")
public class PetController {
	@Autowired
	private IPetService petService;

	@GetMapping(value = "/")
	public List<PetDTO> getAllPets() {
		return petService.findAll();
	}

	@GetMapping(value = "/{id}")
	public PetDTO findById(@Valid @PathVariable(value = "id") Integer id) throws CustomPetException {

		return petService.findById(id);

	}

	@ResponseBody
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public PetDTO createOrSavePet(@Valid @RequestBody PetDTO petDTO) throws CustomPetException {
//    	return petService.save(petDTO);
//    	try {
		return petService.save(petDTO);
//    	}catch(ConstraintViolationException e){
//			throw new PetConstraintViolationException(petDTO, e);
//		}
	}

	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	public PetDTO updatePet(@PathVariable(value = "id") Integer id, @RequestBody PetDTO petDetails) {
		return petService.updatePet(id, petDetails);
	}

	@DeleteMapping(value = "/{id}")
	public PetDTO deletePet(@PathVariable(value = "id") Integer id) {
		return petService.delete(id);
	}
}
