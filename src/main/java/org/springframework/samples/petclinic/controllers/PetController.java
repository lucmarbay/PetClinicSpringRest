package org.springframework.samples.petclinic.controllers;

import java.util.List;


import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.dto.PetDTO;
import org.springframework.samples.petclinic.error.CustomPetException;
import org.springframework.samples.petclinic.services.IPetService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//https://dzone.com/articles/spring-boot-2-restful-api-documentation-with-swagg
@RestController
@RequestMapping("/pet")
@Validated
public class PetController {
	@Autowired
	private IPetService petService;

	@GetMapping("/")
	public List<PetDTO> getAllPets() {
		return petService.findAll();
	}

	@GetMapping("/{id}")
	public PetDTO findById(@PathVariable @Min(1) Integer id) throws CustomPetException {
		return petService.findById(id);
	}

	@ResponseBody
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public PetDTO createOrSavePet(@RequestBody PetDTO petDTO) throws CustomPetException {
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

	@DeleteMapping("/{id}")
	public PetDTO deletePet(@PathVariable(value = "id") Integer id) {
		return petService.delete(id);
	}
}
