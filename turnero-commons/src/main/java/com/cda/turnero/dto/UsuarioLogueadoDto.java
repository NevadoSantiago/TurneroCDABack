package com.cda.turnero.dto;

public class UsuarioLogueadoDto {
	
	private String usuario;
	private String tipoUsuario;
	private Integer idUsuario;
	private Integer sucursalId;
	
	public UsuarioLogueadoDto() {
		
	}
	
	
	public UsuarioLogueadoDto(String usuario, String tipoUsuario) {
		super();
		this.usuario = usuario;
		this.tipoUsuario = tipoUsuario;
	}


	public UsuarioLogueadoDto(String usuario, String tipoUsuario, Integer idUsuario, Integer sucursalId) {
		this.usuario = usuario;
		this.tipoUsuario = tipoUsuario;
		this.idUsuario = idUsuario;
		this.sucursalId = sucursalId;
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
	public Integer getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}
	
	

}
