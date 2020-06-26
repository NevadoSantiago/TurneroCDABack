package com.cda.turnero.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TipoSucursal {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tipoSucursalId;

	
	private String detalle;
	//	modificador
	private Date alta;
	private Date baja;
	private Date modificacion;
	
	public Integer getTipoSucursalId() {
			return tipoSucursalId;
		}
	public void setTipoSucursalId(Integer tipoSucursalId) {
		this.tipoSucursalId = tipoSucursalId;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Date getAlta() {
		return alta;
	}
	public void setAlta(Date alta) {
		this.alta = alta;
	}
	public Date getBaja() {
		return baja;
	}
	public void setBaja(Date baja) {
		this.baja = baja;
	}
	public Date getModificacion() {
		return modificacion;
	}
	public void setModificacion(Date modificacion) {
		this.modificacion = modificacion;
	}

}
