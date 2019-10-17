package org.springframework.samples.petclinic.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.samples.petclinic.dto.OwnerDTO;
import org.springframework.samples.petclinic.dto.PetDTO;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;

@Mapper
public interface PetMapper {

	 PetMapper INSTANCE = Mappers.getMapper(PetMapper.class);
	
	 PetDTO petToPetDTO(Pet pet);
	 Pet petDTOToPet(PetDTO petDTO);
	 
	 OwnerDTO ownerToOwnerDTO(Owner owner);
	 Owner ownerDTOToOwner(OwnerDTO ownerDTO);
	 
	 
}
