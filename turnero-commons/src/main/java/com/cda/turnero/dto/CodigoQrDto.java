package com.cda.turnero.dto;

import java.util.Date;

public class CodigoQrDto {
	
	private Integer codigoQrId;
	private Date programado;
	private String seguimiento;
	private Integer turnoId;
	private Integer tipoTurnoId;
	private Integer clienteId;
	private Integer sucursalId;
	
	public Integer getCodigoQrId() {
		return codigoQrId;
	}
	public void setCodigoQrId(Integer codigoQrId) {
		this.codigoQrId = codigoQrId;
	}
	public Date getProgramado() {
		return programado;
	}
	public void setProgramado(Date programado) {
		this.programado = programado;
	}
	public String getSeguimiento() {
		return seguimiento;
	}
	public void setSeguimiento(String codigoSeguimiento) {
		this.seguimiento = codigoSeguimiento;
	}
	public Integer getTurnoId() {
		return turnoId;
	}
	public void setTurnoId(Integer turnoId) {
		this.turnoId = turnoId;
	}
	public Integer getTipoTurnoId() {
		return tipoTurnoId;
	}
	public void setTipoTurnoId(Integer tipoTurnoId) {
		this.tipoTurnoId = tipoTurnoId;
	}
	public Integer getClienteId() {
		return clienteId;
	}
	public void setClienteId(Integer clienteId) {
		this.clienteId = clienteId;
	}
	public Integer getSucursalId() {
		return sucursalId;
	}
	public void setSucursalId(Integer sucursalId) {
		this.sucursalId = sucursalId;
	}
	
}
