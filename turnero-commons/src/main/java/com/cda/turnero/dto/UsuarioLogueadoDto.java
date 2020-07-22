package com.cda.turnero.dto;

import com.cda.turnero.model.Sucursal;

public class UsuarioLogueadoDto {
	
	private String usuario;
	private String tipoUsuario;
	private Integer idUsuario;
	private Sucursal sucursal;
	
	public UsuarioLogueadoDto() {
		
	}
	
	
	public UsuarioLogueadoDto(String usuario, String tipoUsuario) {
		super();
		this.usuario = usuario;
		this.tipoUsuario = tipoUsuario;
	}


	public UsuarioLogueadoDto(String usuario, String tipoUsuario, Integer idUsuario, Sucursal sucursal) {
		this.usuario = usuario;
		this.tipoUsuario = tipoUsuario;
		this.idUsuario = idUsuario;
		this.sucursal = sucursal;
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
