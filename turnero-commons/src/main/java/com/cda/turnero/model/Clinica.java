package com.cda.turnero.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Clinica {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clinicaId;
	
	private String nombre;
	
	private boolean habilitada;
	
	private Date fechaAlta;
	
	private Date fechaBaja;

	public Integer getClinicaId() {
		return clinicaId;
	}

	public void setClinicaId(Integer clinicaId) {
		this.clinicaId = clinicaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isHabilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	
	
	
}
