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
import javax.persistence.OneToOne;

import com.cda.turnero.enums.EstadoTurno;

@Entity
public class Turno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer turnoId;
	
	private Date programado;

	private String horario;
	
	private Integer idModificador;
	
	@OneToOne
	@JoinColumn(name = "tipo_turno_id", nullable = true)
	private Especialidad tipoTurno;
	
	@OneToOne
	@JoinColumn(name = "sucursal_id", nullable = false)
	private Sucursal sucursal;

	private Date fechaAlta;	
	private Date fechaModificacion;
	
	@Column(name="estado") 
	@Enumerated(EnumType.STRING) 
	private EstadoTurno estado; 

	public Integer getTurnoId() {
		return turnoId;
	}

	public void setTurnoId(Integer turnoId) {
		this.turnoId = turnoId;
	}

	public Date getProgramado() {
		return programado;
	}

	public void setProgramado(Date programado) {
		this.programado = programado;
	}	

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}	

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}	

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Integer getIdModificador() {
		return idModificador;
	}

	public void setIdModificador(Integer idModificador) {
		this.idModificador = idModificador;
	}	

	public Especialidad getTipoTurno() {
		return tipoTurno;
	}

	public void setTipoTurno(Especialidad tipoTurno) {
		this.tipoTurno = tipoTurno;
	}

	public EstadoTurno getEstado() {
		return estado;
	}

	public void setEstado(EstadoTurno estado) {
		this.estado = estado;
	}		
}
