package com.cda.turnero.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Provincia {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer provinciaId;
	
	private String detalle;

	public Integer getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(Integer provinciaId) {
		this.provinciaId = provinciaId;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	
}
