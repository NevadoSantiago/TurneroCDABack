package com.cda.turnero.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.Reserva;

public interface ReservaDao extends JpaRepository<Reserva, Integer> {

	Optional<Reserva> findByClienteLikeAndFechaSalidaIsNull(Cliente cliente);
	
}
