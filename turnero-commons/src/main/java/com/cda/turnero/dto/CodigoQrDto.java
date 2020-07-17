package com.cda.turnero.dto;

public class CodigoQrDto {
	
	private Integer reservaId;
	private Integer especialidadId;
	private Integer clienteId;
	private Integer sucursalId;
		
	public CodigoQrDto(Integer reservaId, Integer especialidadId, Integer clienteId, Integer sucursalId) {
		this.reservaId = reservaId;
		this.especialidadId = especialidadId;
		this.clienteId = clienteId;
		this.sucursalId = sucursalId;
	}
	public Integer getReservaId() {
		return reservaId;
	}
	public void setReservaId(Integer reservaId) {
		this.reservaId = reservaId;
	}
	public Integer getEspecialidadId() {
		return especialidadId;
	}
	public void setEspecialidadId(Integer especialidadId) {
		this.especialidadId = especialidadId;
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
