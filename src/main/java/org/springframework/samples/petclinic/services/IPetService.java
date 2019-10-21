package org.springframework.samples.petclinic.services;

import java.util.List;

import org.springframework.samples.petclinic.dto.PetDTO;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;

public interface IPetService {
	public List<PetDTO> findAll();
	public PetDTO findById(Integer id);
	/**public List<PetType> findPetTypes();**/
	public PetDTO delete(Integer id);
	public PetDTO updatePet(Integer id, PetDTO petDetails);
	public PetDTO save(PetDTO petDTO);

}
