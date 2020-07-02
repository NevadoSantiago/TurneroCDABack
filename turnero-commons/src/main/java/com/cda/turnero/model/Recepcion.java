package com.cda.turnero.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Recepcion {
	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recepcionId;
	
	@ManyToOne
	@JoinColumn(name = "sucursal_id", nullable = false)
	private Sucursal sucursal;
	
	private Date fechaAlta;
	
	private Date fechaBaja;
	
	private boolean habilitada;

	public Integer getRecepcionId() {
		return recepcionId;
	}

	public void setRecepcionId(Integer recepcionId) {
		this.recepcionId = recepcionId;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
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

	public boolean isHabilitada() {
		return habilitada;
	}

	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}
	
	

}
