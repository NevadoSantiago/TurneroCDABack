package com.cda.turnero.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Sucursal {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sucursalId;
	
	private String direccion;
	
	private String nombre;
	
	private boolean habilitada;
	
	private Date fechaAlta;
	
	private Date fechaBaja;
	
	@OneToOne
	@JoinColumn(name="configuracion_id")
	private ConfiguracionSucursal configuracion;
	
	@ManyToOne
	@JoinColumn(name="clinica_id")
	private Clinica clinica;



	public Integer getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
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

	public ConfiguracionSucursal getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(ConfiguracionSucursal caracteristica) {
		this.configuracion = caracteristica;
	}

	public Clinica getClinica() {
		return clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}
	
	
	
	

}
