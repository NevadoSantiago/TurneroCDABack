package com.cda.turnero.dto;

import com.cda.turnero.model.Empleado;

public class EmpleadoNoRegistradoDto {

	private Integer idEmpleado;
	private String nombre;
	private String apellido;
	private String nombreSucursal;
	private String rol;
	private String codigo;
	
	public EmpleadoNoRegistradoDto() {
		
	}
	public EmpleadoNoRegistradoDto(Empleado empleado, String codigo) {
		this.idEmpleado = empleado.getPersonaId();
		this.nombre = empleado.getNombre();
		this.apellido = empleado.getApellido();
		this.rol = empleado.getUsuario().getTipoUsuario().getDetalle();
		if(empleado.getSucursal()!= null) {
			this.nombreSucursal = empleado.getSucursal().getNombre();
		}else nombreSucursal = "SIN SUCURSAL ASOCIADA";
		
		this.codigo = codigo;
	}
	
	
	public String getNombreSucursal() {
		return nombreSucursal;
	}
	public void setNombreSucursal(String nombreSucursal) {
		this.nombreSucursal = nombreSucursal;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public Integer getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Integer idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
	
}
