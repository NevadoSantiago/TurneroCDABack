package com.cda.turnero.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Size;

@Entity
public class CodigoQr {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigoQrId;
	
	@Lob
	private String codigo; /**/
	
	public CodigoQr() {
		

	}
	
	public CodigoQr(int id, String codigo) {
		this.codigo = codigo;
		this.codigoQrId = id;
	}

	private boolean enable;

	public Integer getCodigoQrId() {
		return codigoQrId;
	}
	public void setCodigoQrId(Integer codigoQrId) {
		this.codigoQrId = codigoQrId;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
