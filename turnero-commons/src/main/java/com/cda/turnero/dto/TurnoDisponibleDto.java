package com.cda.turnero.dto;

public class TurnoDisponibleDto {

	private Integer turnoId;
	private String fecha;
	private String horario;
	private String sucursal;
	
	public Integer getTurnoId() {
		return turnoId;
	}
	public void setTurnoId(Integer turnoId) {
		this.turnoId = turnoId;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	
	
}
