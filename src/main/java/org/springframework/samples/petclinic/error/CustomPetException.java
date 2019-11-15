package org.springframework.samples.petclinic.error;

import java.util.ArrayList;
import java.util.List;

public class CustomPetException extends Exception{
	private String propiedadPet;
	public CustomPetException(String mensaje, String propiedadPet) {
		super (mensaje);
		this.propiedadPet=propiedadPet;
	}
	public List<String> obtenerListaMensajes(){
		List<String> listaMensajes = new ArrayList<String>();
		listaMensajes.add(this.getMessage());
		listaMensajes.add(this.propiedadPet);
		return listaMensajes;
		
	}
	public String getPropiedadPet() {
		return propiedadPet;
	}
	public void setPropiedadPet(String propiedadPet) {
		this.propiedadPet = propiedadPet;
	}

}
