package com.cda.turnero.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Provincia {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer provinciaId;
	
	private String detalle;

	@ManyToOne
	@JoinColumn (name = "pais_id")
	private Pais pais;	

	public Integer getProvinciaId() {
		return provinciaId;
	}

	public void setProvinciaId(Integer provinciaId) {
		this.provinciaId = provinciaId;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	
	
}
