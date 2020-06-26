package com.cda.turnero.dto;

import java.util.Date;

import com.cda.turnero.enums.TurnoClienteEnum;

public class DetalleTurnoDto {
	
	private String fechaProgramado;
	private String horario;
	private Integer idTurno;
	private TurnoClienteEnum estado;
	private Integer duracionDelTurno;
	private String sucursal;
	private String detalleDelTurno;
	private String nombreTienda;
	private String codigoSeguimiento;
	private Integer tiendaId;

	public Integer getTiendaId() {
		return tiendaId;
	}
	public void setTiendaId(Integer tiendaId) {
		this.tiendaId = tiendaId;
	}
	public String getHorario() {
		return horario;
	}
	public void setHorario(String horario) {
		this.horario = horario;
	}
	public Integer getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(Integer idTurno) {
		this.idTurno = idTurno;
	}
	public TurnoClienteEnum getEstado() {
		return estado;
	}
	public void setEstado(TurnoClienteEnum estado) {
		this.estado = estado;
	}
	public String getFechaProgramado() {
		return fechaProgramado;
	}
	public void setFechaProgramado(String fechaProgramado) {
		this.fechaProgramado = fechaProgramado;
	}
	public Integer getDuracionDelTurno() {
		return duracionDelTurno;
	}
	public void setDuracionDelTurno(Integer duracionDelTurno) {
		this.duracionDelTurno = duracionDelTurno;
	}
	public String getDetalleDelTurno() {
		return detalleDelTurno;
	}
	public void setDetalleDelTurno(String detalleDelTurno) {
		this.detalleDelTurno = detalleDelTurno;
	}
	public String getNombreTienda() {
		return nombreTienda;
	}
	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public String getCodigoSeguimiento() {
		return codigoSeguimiento;
	}
	public void setCodigoSeguimiento(String codigoSeguimiento) {
		this.codigoSeguimiento = codigoSeguimiento;
	}
	
	
	
	

}
