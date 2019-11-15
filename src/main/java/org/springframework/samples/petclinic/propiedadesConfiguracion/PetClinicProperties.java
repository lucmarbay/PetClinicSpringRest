package org.springframework.samples.petclinic.propiedadesConfiguracion;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "org.springframework.samples.petclinic")
public class PetClinicProperties {

	private String hola;
	private String nombre;

	public String getHola() {
		return hola;
	}

	public void setHola(String hola) {
		this.hola = hola;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
