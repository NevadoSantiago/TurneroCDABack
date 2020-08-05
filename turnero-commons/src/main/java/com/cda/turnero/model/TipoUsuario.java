package com.cda.turnero.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class TipoUsuario implements GrantedAuthority {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer tipoId;
	
	private Date fechaAlta;
	
	private Date fechaBaja;
	
	private String detalle;

	public Integer getTipoId() {
		return tipoId;
	}
    @Override
    public String getAuthority() {
        return detalle;
    }

	public void setTipoId(Integer tipoId) {
		this.tipoId = tipoId;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	
	
}
