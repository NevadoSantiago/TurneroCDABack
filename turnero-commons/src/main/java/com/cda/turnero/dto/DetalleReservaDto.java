package com.cda.turnero.dto;

import com.cda.turnero.model.Reserva;

public class DetalleReservaDto {

	private String nombreSucursal;
	private String direccion;
	private String sintomas;
	private Integer idReserva;
	private String latitud;
	private String longitud;
	
	public DetalleReservaDto(Reserva reserva) {
		nombreSucursal = reserva.getSucursal().getNombre();
		direccion = reserva.getSucursal().getDireccion();
		sintomas = reserva.getDescSintomas();
		idReserva = reserva.getReservaId();
		latitud = reserva.getSucursal().getConfiguracion().getCordLatitud();
		longitud =  reserva.getSucursal().getConfiguracion().getCordLongitud();
	}

	public String getNombreSucursal() {
		return nombreSucursal;
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

	public String getSintomas() {
		return sintomas;
	}

	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	public Integer getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
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
