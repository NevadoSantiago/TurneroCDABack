package com.cda.turnero.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Localidad {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer localidadId;
	private String detalle;
	
	@ManyToOne
	@JoinColumn (name = "provincia_id")
	private Provincia provincia;
			
	public Integer getLocalidadId() {
		return localidadId;
	}
	public void setLocalidadId(Integer localidadId) {
		this.localidadId = localidadId;
	}
	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
}
