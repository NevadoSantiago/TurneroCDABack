package com.cda.turnero.dto;

import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.Reserva;

public class ClienteLogueadoDto {
	
	private Cliente cliente;
	
	private Reserva reserva;
	
	public ClienteLogueadoDto() {
		
	}
	public ClienteLogueadoDto(Cliente cliente, Reserva reserva) {
		this.cliente = cliente;
		this.reserva = reserva;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
	
	

}
