package com.cda.turnero.dto;

import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.Reserva;

public class ClienteLogueadoDto {
	
	private String nombre;
	private Integer idCliente;
	private DetalleReservaDto detalleReserva;

		
	public ClienteLogueadoDto() {
		
	}
	
	public ClienteLogueadoDto(Cliente cliente, Reserva reserva) {
		nombre = cliente.getNombre();
		idCliente = cliente.getPersonaId();
		detalleReserva = new DetalleReservaDto(reserva);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public DetalleReservaDto getDetalleReserva() {
		return detalleReserva;
	}

	public void setDetalleReserva(DetalleReservaDto detalleReserva) {
		this.detalleReserva = detalleReserva;
	}
	
	


}
