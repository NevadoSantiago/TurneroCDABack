package com.cda.turnero.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="empleado")
@PrimaryKeyJoinColumn(name = "persona_id")
public class Empleado extends Persona {

	@ManyToOne
	@JoinColumn(name="sucursal_id")
	private Sucursal sucursal;

	
	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	
	
}
