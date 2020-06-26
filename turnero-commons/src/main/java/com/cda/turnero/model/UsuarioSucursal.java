package com.cda.turnero.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UsuarioSucursal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer usuarioSucursalId;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "sucursal_id")
	private Sucursal sucursal;

	public Integer getUsuarioSucursalId() {
		return usuarioSucursalId;
	}

	public void setUsuarioSucursalId(Integer usuarioSucursalId) {
		this.usuarioSucursalId = usuarioSucursalId;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}	
}