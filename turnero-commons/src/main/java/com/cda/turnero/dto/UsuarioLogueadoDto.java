package com.cda.turnero.dto;

import com.cda.turnero.model.Sucursal;

public class UsuarioLogueadoDto {
	
	private String usuario;
	private String nombre;
	private String apellido;
	private String tipoUsuario;
	private Integer idUsuario;
	private Sucursal sucursal;
	
	public UsuarioLogueadoDto() {
		
	}
	
	
	public UsuarioLogueadoDto(String usuario, String tipoUsuario) {
		this.usuario = usuario;
		this.tipoUsuario = tipoUsuario;
	}


	public UsuarioLogueadoDto(String usuario, String tipoUsuario, Integer idUsuario, Sucursal sucursal, String nombre,String apellido) {
		this.usuario = usuario;
		this.tipoUsuario = tipoUsuario;
		this.idUsuario = idUsuario;
		this.sucursal = sucursal;
		this.nombre = nombre;
		this.apellido = apellido;
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


	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Sucursal getSucursal() {
		return sucursal;
	}
	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	

}
