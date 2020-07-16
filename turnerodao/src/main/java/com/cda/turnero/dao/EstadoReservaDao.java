package com.cda.turnero.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.EstadoReserva;

public interface EstadoReservaDao extends JpaRepository<EstadoReserva, Integer> {

	EstadoReserva findByNombreLike(String nombre);
	
}
