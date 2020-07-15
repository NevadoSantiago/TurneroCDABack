package com.cda.turnero.dto;

import com.cda.turnero.model.ConfiguracionSucursal;

public class DetalleSucursalDto {

	private Integer sucursalId;
	private String nombre;
	private String direccion;
	private Long cantidadPersonas;
	private ConfiguracionSucursal configuracion;
	
	public DetalleSucursalDto() {
		
	}
	public DetalleSucursalDto(int sucursalId, String nombreSucursal, String direccion, Long cantidadPersona, String cordLongitud, String cordLatitud) {
		this.nombre = nombreSucursal;
		this.direccion= direccion;
		this.cantidadPersonas = cantidadPersona;
		this.sucursalId = sucursalId;
		ConfiguracionSucursal cs = new ConfiguracionSucursal();
		cs.setCordLatitud(cordLatitud);
		cs.setCordLongitud(cordLongitud);
		this.configuracion = cs;
		
	}
	public DetalleSucursalDto(int sucursalId, String nombreSucursal, String direccion,String cordLongitud, String cordLatitud) {
		this.nombre = nombreSucursal;
		this.direccion= direccion;
		this.cantidadPersonas = 0L;
		this.sucursalId = sucursalId;
		ConfiguracionSucursal cs = new ConfiguracionSucursal();
		cs.setCordLatitud(cordLatitud);
		cs.setCordLongitud(cordLongitud);
		this.configuracion = cs;
		
	}
	
	
	
	public ConfiguracionSucursal getConfiguracion() {
		return configuracion;
	}
	public void setConfiguracion(ConfiguracionSucursal configuracion) {
		this.configuracion = configuracion;
	}
	public String getNombreSucursal() {
		return nombre;
	}
	public Integer getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}
	public void setNombreSucursal(String nombreSucursal) {
		this.nombre = nombreSucursal;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Long getCantidadPersonas() {
		return cantidadPersonas;
	}
	public void setCantidadPersonas(Long cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
	
}
