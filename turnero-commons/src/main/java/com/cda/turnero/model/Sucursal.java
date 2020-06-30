package com.cda.turnero.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Sucursal {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sucursalId;

	private String nombre;
	private String direccion;
	private Date alta;
	private Date baja;
	private Date modificacion;
	
	@ManyToOne
	@JoinColumn (name = "clinica_id")
	private Clinica clinica;
	
	@ManyToOne
	@JoinColumn (name = "tipo_sucursal_id")
	private TipoSucursal tipo;
	
	@ManyToOne
	@JoinColumn (name = "caracteristica_sucursal_id")
	private CaracteristicaSucursal caracteristica;
	
	@ManyToOne
	@JoinColumn (name = "localidad_id")
	private Localidad localidad;
	
	public Integer getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public TipoSucursal getTipo() {
		return tipo;
	}
	public void setTipo(TipoSucursal tipo) {
		this.tipo = tipo;
	}
	public CaracteristicaSucursal getCaracteristica() {
		return caracteristica;
	}
	public void setCaracteristica(CaracteristicaSucursal caracteristica) {
		this.caracteristica = caracteristica;
	}
	public Localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
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
	public Clinica getTienda() {
		return clinica;
	}
	public void setTienda(Clinica tienda) {
		this.clinica = tienda;
	}		
	
//	modificador
	
}
