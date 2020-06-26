package com.cda.turnero.model;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class CaracteristicaSucursal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer caracteristicaSucursalId;
	
	private Date apertura;
	private Date cierre;
	private Integer cantidadDeCajas;
	private Date alta;
	private Date baja;
	private Date modificacion;
	private Integer capacidad;
/*	modificador; */
	
	public Integer getCaracteristicaSucursalId() {
		return caracteristicaSucursalId;
	}
	public void setCaracteristicaSucursalId(Integer caracteristicaSucursalId) {
		this.caracteristicaSucursalId = caracteristicaSucursalId;
	}
	public Date getApertura() {
		return apertura;
	}
	public void setApertura(Date apertura) {
		this.apertura = apertura;
	}
	public Date getCierre() {
		return cierre;
	}
	public void setCierre(Date cierre) {
		this.cierre = cierre;
	}
	public int getCantidadDeCajas() {
		return cantidadDeCajas;
	}
	public void setCantidadDeCajas(Integer cantidadDeCajas) {
		this.cantidadDeCajas = cantidadDeCajas;
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
	public Integer getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(Integer capacidad) {
		this.capacidad = capacidad;
	}
	
	
}