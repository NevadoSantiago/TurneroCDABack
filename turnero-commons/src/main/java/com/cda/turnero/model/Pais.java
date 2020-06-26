package com.cda.turnero.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pais {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer paisId;
	private String detalle;
			
	public Integer getPaisId() {
		return paisId;
	}
	public void setPaisId(Integer paisId) {
		this.paisId = paisId;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	
}
