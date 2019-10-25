package org.springframework.samples.petclinic.error;

import org.springframework.samples.petclinic.dto.OwnerDTO;
import org.springframework.samples.petclinic.dto.PetDTO;

public class PetConstraintViolationException extends RuntimeException {
	
	public PetConstraintViolationException(PetDTO petDTO) {
		//Tengo que hacer que la clase sepa que propiedad es la que esta fallando para dar un mensaje 
//		de error mas específica
        super("La id debe estar entre 1 y 16, pero ha introducido : " + petDTO.getId());
    }

}
