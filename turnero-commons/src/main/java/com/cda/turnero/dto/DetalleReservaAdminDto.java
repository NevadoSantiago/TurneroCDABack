package com.cda.turnero.dto;

public class DetalleReservaAdminDto {

//	cliente.get("nombre"),
//	cliente.get("apellido"),
//	cliente.get("nroDocumento"),
//	cliente.get("personaId"),
//	reserva.join("especialidad").get("nombre"),
//	reserva.get("reservaId"));
	
	private Integer idCliente;
	private Integer idReserva;
	private String especialidad;
	private String nroDocumento;
	private String nombre;
	private String apellido;
	
	public DetalleReservaAdminDto (){
		
	}
	public DetalleReservaAdminDto (Integer idReserva, String especialidad){
		this.nombre = null;
		this.apellido = null;
		this.nroDocumento=null;
		this.idCliente = null;
		this.especialidad = especialidad;
		this.idReserva=idReserva;
	}
	
	public DetalleReservaAdminDto (
	String nombre,String apellido,String nroDocumento, Integer idCliente, Integer idReserva,String especialidad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroDocumento=nroDocumento;
		this.idCliente = idCliente;
		this.especialidad = especialidad;
		this.idReserva=idReserva;
		
		
	}

	
	
	public String getNroDocumento() {
		return nroDocumento;
	}


	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}


	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public Integer getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	
	
}
