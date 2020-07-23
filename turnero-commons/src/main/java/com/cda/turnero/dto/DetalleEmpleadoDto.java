package com.cda.turnero.dto;

import java.util.Date;

public class DetalleEmpleadoDto {
	
	private Integer idEmpleado;
	private String nombre;
	private String apellido;
	private String mail;
	private String rol;
	private Date fechaAlta;
	
	
	
	public DetalleEmpleadoDto() {
	}
	public DetalleEmpleadoDto(Integer idEmpleado, String nombre, String apellido, String mail, String rol) {
		this.idEmpleado = idEmpleado;
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.rol = rol;
	}
	public Integer getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	
	
	

}
