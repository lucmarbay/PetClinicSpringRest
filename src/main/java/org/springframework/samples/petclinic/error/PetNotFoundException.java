package org.springframework.samples.petclinic.error;

public class PetNotFoundException extends RuntimeException {
	
	public PetNotFoundException(Integer id) {
        super("Pet id not found : " + id);
    }

}
