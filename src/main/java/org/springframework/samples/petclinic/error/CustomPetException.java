package org.springframework.samples.petclinic.error;

public class CustomPetException extends Exception{

	public CustomPetException(String mensaje) {
		super (mensaje);
	}

	

}
