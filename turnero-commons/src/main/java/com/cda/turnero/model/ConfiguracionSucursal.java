package com.cda.turnero.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConfiguracionSucursal {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer configuracionId;
	
	private Date fechaApertura;
	
	private Date fechaCierre;
	
	private Date fechaAlta;
	
	private Date fechaBaja;
	
	private String cordLongitud;
	
	private String cordLatitud;



	public Integer getConfiguracionId() {
		return configuracionId;
	}

	public void setConfiguracionId(Integer configuracionId) {
		this.configuracionId = configuracionId;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
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

	public String getCordLongitud() {
		return cordLongitud;
	}

	public void setCordLongitud(String cordLongitud) {
		this.cordLongitud = cordLongitud;
	}

	public String getCordLatitud() {
		return cordLatitud;
	}

	public void setCordLatitud(String cordLatitud) {
		this.cordLatitud = cordLatitud;
	}
	
	
	
	
}
