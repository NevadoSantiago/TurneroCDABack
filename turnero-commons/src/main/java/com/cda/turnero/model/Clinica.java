package com.cda.turnero.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Clinica {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tiendaId;
	
	private String nombre;
	private boolean habilitada;
	private Date alta;
	private Date baja;
	private Date modificacion;
	
	@JsonIgnore
	@Lob
	private byte[] imagenMuestra;
//	modificador;
	
	public Integer getTiendaId() {
		return tiendaId;
	}
	public void setTiendaId(Integer tiendaId) {
		this.tiendaId = tiendaId;
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
	public byte[] getImagenMuestra() {
		return imagenMuestra;
	}
	public void setImagenMuestra(byte[] imagenMuestra) {
		this.imagenMuestra = imagenMuestra;
	}
	
	
}
