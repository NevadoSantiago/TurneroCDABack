package com.cda.turnero.model;

import java.util.Date;

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
	private Integer cajaId;

	private Date fechaAlta;

	private Date fechaBaja;
	
	private Date fechaModificacion;
	
	private Boolean activa;
	
	
	@ManyToOne
	@JoinColumn(name = "sucursal_id", nullable = false)
	private Sucursal sucursal;
	
	public Integer getCajaId() {
		return cajaId;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public void setCajaId(Integer cajaId) {
		this.cajaId = cajaId;
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

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Boolean getActiva() {
		return activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

}
