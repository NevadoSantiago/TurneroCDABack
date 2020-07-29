package com.cda.turnero.dto;

public class EsperaEspecialidadDto {
	
	private String especialidad;
	private Integer idEspecialidad;
	private Long cantidadEspera;
	private Long cantidadRealizadoHoy;
	
	
	
	
	
	public EsperaEspecialidadDto(String especialidad, Integer idEspecialidad, Long cantidadEspera) {
		this.especialidad = especialidad;
		this.idEspecialidad = idEspecialidad;
		this.cantidadEspera = cantidadEspera;
	}
	public EsperaEspecialidadDto(String especialidad, Integer idEspecialidad, Long cantidadEspera,
			Long cantidadRealizadoHoy) {
		this.especialidad = especialidad;
		this.idEspecialidad = idEspecialidad;
		this.cantidadEspera = cantidadEspera;
		this.cantidadRealizadoHoy = cantidadRealizadoHoy;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public Integer getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	public Long getCantidadEspera() {
		return cantidadEspera;
	}
	public void setCantidadEspera(Long cantidadEspera) {
		this.cantidadEspera = cantidadEspera;
	}
	public Long getCantidadRealizadoHoy() {
		return cantidadRealizadoHoy;
	}
	public void setCantidadRealizadoHoy(Long cantidadRealizadoHoy) {
		this.cantidadRealizadoHoy = cantidadRealizadoHoy;
	}
	
	


}
