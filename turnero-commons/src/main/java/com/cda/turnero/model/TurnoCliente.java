package com.cda.turnero.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.cda.turnero.enums.EstadoTurno;
import com.cda.turnero.enums.TurnoClienteEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="turnoCliente")
public class TurnoCliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer turnoClienteId;	
	
	private Date fechaIngreso;
	
	@Column(name="estado") 
	@Enumerated(EnumType.STRING) 
	private TurnoClienteEnum estado; 
	
	private String codigoSeguimiento;
	
	@OneToOne
	@JoinColumn(name = "codigo_qr_id", nullable = true)
	private CodigoQr codigoQr;
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = true)
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name = "turno_id", nullable = true)
	private Turno turno;
	
	private Date fechaAlta;

	public Integer getTurnoClienteId() {
		return turnoClienteId;
	}

	public void setTurnoClienteId(Integer turnoClienteId) {
		this.turnoClienteId = turnoClienteId;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public TurnoClienteEnum getEstado() {
		return estado;
	}

	public void setEstado(TurnoClienteEnum sinConfirmar) {
		this.estado = sinConfirmar;
	}

	public String getCodigoSeguimiento() {
		return codigoSeguimiento;
	}

	public void setCodigoSeguimiento(String codigoSeguimiento) {
		this.codigoSeguimiento = codigoSeguimiento;
	}

	public CodigoQr getCodigoQr() {
		return codigoQr;
	}

	public void setCodigoQr(CodigoQr codigoQr) {
		this.codigoQr = codigoQr;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Turno getTurno() {
		return turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}			
}