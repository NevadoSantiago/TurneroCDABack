package com.cda.turnero.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TurnoCaja {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer turnoCajaId;
	
	@ManyToOne
	@JoinColumn (name = "turnoCliente_id")
	private TurnoCliente turno;
	@ManyToOne
	@JoinColumn (name = "caja_id")
	private Recepcion caja;
	private Date fechaDeIngreso;
	private Date fechaDeEgreso;
	private Date fechaAlta;
	
		
	public Integer getTurnoCajaId() {
		return turnoCajaId;
	}
	public void setTurnoCajaId(Integer turnoCajaId) {
		this.turnoCajaId = turnoCajaId;
	}
	public Date getFechaDeIngreso() {
		return fechaDeIngreso;
	}
	public void setFechaDeIngreso(Date fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}
	public Date getFechaDeEgreso() {
		return fechaDeEgreso;
	}
	public void setFechaDeEgreso(Date fechaDeEgreso) {
		this.fechaDeEgreso = fechaDeEgreso;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
	public TurnoCliente getTurno() {
		return turno;
	}
	public void setTurno(TurnoCliente turno) {
		this.turno = turno;
	}
	public Recepcion getCaja() {
		return caja;
	}
	public void setCaja(Recepcion caja) {
		this.caja = caja;
	}
	
}
