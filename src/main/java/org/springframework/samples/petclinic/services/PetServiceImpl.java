package org.springframework.samples.petclinic.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.petclinic.dto.PetDTO;
import org.springframework.samples.petclinic.mapper.PetMapper;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.stereotype.Service;

@Service
public class PetServiceImpl implements IPetService{
	
	private PetRepository petRepository;
	
	public PetServiceImpl(PetRepository petRepository) {
		this.petRepository = petRepository;
	}
	
	public List<PetDTO> findAll(){
		
		List<PetDTO> results = new ArrayList<>();
		List<Pet> petList = petRepository.findAll();
		Iterator<Pet> it = petList.iterator();
		while(it.hasNext()) {
			Pet pet = it.next();
			results.add(PetMapper.INSTANCE.petToPetDTO(pet));
		}
		
		return results;
	}
	
	public PetDTO save(PetDTO petDTO) {
		
		Pet pet = this.petRepository.save(PetMapper.INSTANCE.petDTOToPet(petDTO));
		PetDTO petDTOResponse=PetMapper.INSTANCE.petToPetDTO(pet);
		return petDTOResponse;
	}
	
	public PetDTO findById(Integer id) {
		PetDTO petDTO = new PetDTO();
		Pet pet = this.petRepository.getOne(id);
		petDTO.setName(pet.getName());
		petDTO.setId(pet.getId());
		petDTO.setOwner(PetMapper.INSTANCE.ownerToOwnerDTO(pet.getOwner()));
        return petDTO;
	}
	/**
	public List<PetType> findPetTypes(){
		return this.findPetTypes();
	}**/
	public PetDTO delete(Integer id) {
		Pet pet = petRepository.getOne(id);
		petRepository.delete(pet);
		PetDTO responsePetDTO = PetMapper.INSTANCE.petToPetDTO(pet);
		return responsePetDTO;
	}
	
	public PetDTO updatePet(Integer id, PetDTO petDetails) {
		Pet pet = petRepository.getOne(id);
		pet.setName(petDetails.getName());
		PetDTO updatedPet = PetMapper.INSTANCE.petToPetDTO(petRepository.save(pet));
		return updatedPet;
	}

	public PetRepository getPetRepository() {
		return petRepository;
	}

	public void setPetRepository(PetRepository petRepository) {
		this.petRepository = petRepository;
	}
	
	
}
