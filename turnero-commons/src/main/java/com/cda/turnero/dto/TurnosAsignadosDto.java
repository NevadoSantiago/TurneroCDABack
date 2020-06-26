package com.cda.turnero.dto;

import com.cda.turnero.enums.TurnoClienteEnum;

public class TurnosAsignadosDto {

	private String fecha;
	private TurnoClienteEnum estado;
	private Integer idTurno;
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public TurnoClienteEnum getEstado() {
		return estado;
	}
	public void setEstado(TurnoClienteEnum estado) {
		this.estado = estado;
	}
	public Integer getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(Integer idTurno) {
		this.idTurno = idTurno;
	}
	
	
	
}
