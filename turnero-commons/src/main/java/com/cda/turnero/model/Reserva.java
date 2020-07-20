package com.cda.turnero.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Reserva {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reservaId;
	
	@Lob
	private String codigoQr;
	
	private Date fechaSalida;
	
	private Date fechaEntrada;
	
	private String descSintomas;
	@Column(name="cordLongitud_sol")
	private String longitud;
	@Column(name="cordLatitud_sol")
	private String latitud;
	
	@ManyToOne
	@JoinColumn(name="especialidad_id")
	private Especialidad especialidad;
	
	@ManyToOne
	@JoinColumn(name="estado_id")
	private EstadoReserva estado;
	
	@ManyToOne
	@JoinColumn(name="persona_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name="sucursal_id")
	private Sucursal sucursal;

	public Integer getReservaId() {
		return reservaId;
	}
	

	public String getLongitud() {
		return longitud;
	}


	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}


	public String getLatitud() {
		return latitud;
	}


	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}


	public void setReservaId(Integer reservaId) {
		this.reservaId = reservaId;
	}

	public String getCodigoQr() {
		return codigoQr;
	}

	public void setCodigoQr(String codigoQr) {
		this.codigoQr = codigoQr;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getDescSintomas() {
		return descSintomas;
	}

	public void setDescSintomas(String descSintomas) {
		this.descSintomas = descSintomas;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public EstadoReserva getEstado() {
		return estado;
	}

	public void setEstado(EstadoReserva estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}
	
	
	
	
	
	
	
	
	
	
}
