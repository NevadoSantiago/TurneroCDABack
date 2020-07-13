package com.cda.turnero.dto;

public class DetalleSucursalDto {

	private Integer sucursalId;
	private String nombreSucursal;
	private String direccion;
	private Long cantidadPersonas;
	private String latitud;
	private String longitud;
	
	public DetalleSucursalDto() {
		
	}
	public DetalleSucursalDto(int sucursalId, String nombreSucursal, String direccion, Long cantidadPersona) {
		this.nombreSucursal = nombreSucursal;
		this.direccion= direccion;
		this.cantidadPersonas = cantidadPersona;
		this.sucursalId = sucursalId;
	}
	
	public String getNombreSucursal() {
		return nombreSucursal;
	}
	public Integer getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}
	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
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
