package com.cda.turnero.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Especialidad {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer especialidadId;
	
	private String nombre;
	
	private String descripcion;

	public Integer getEspecialidadId() {
		return especialidadId;
	}

	public void setEspecialidadId(Integer especialidadId) {
		this.especialidadId = especialidadId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
}
