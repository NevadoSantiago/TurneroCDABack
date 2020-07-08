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
	private long localidadId;
	private String nombre;
	private String latitud;
	private String longitud;

	@ManyToOne
	@JoinColumn(name = "provincia_id")
	private Provincia provincia;

	public long getLocalidadId() {
		return localidadId;
	}

	public void setLocalidadId(long localidadId) {
		this.localidadId = localidadId;
	}

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String detalle) {
		this.nombre = detalle;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
}
