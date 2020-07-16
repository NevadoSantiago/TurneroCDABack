package com.cda.turnero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.EstadoReservaDao;
import com.cda.turnero.model.EstadoReserva;

@Service
public class EstadoReservaService {
	
	@Autowired
	EstadoReservaDao estadoReservaDaoImpl;
	
	public EstadoReserva getEstadoRealizado() {
		return estadoReservaDaoImpl.findByNombreLike("REALIZADO");
	}
	public EstadoReserva getEstadoCancelado() {
		return estadoReservaDaoImpl.findByNombreLike("CANCELADO");
	}
	public EstadoReserva getEstadoProgramado() {
		return estadoReservaDaoImpl.findByNombreLike("PROGRAMADO");
	}

}
