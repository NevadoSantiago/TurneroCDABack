package com.cda.turnero.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.ReservaDao;
import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.Reserva;

@Service
public class ReservaService {
	
	@Autowired
	ReservaDao reservaDaoImpl;
	
	public Reserva getReservaByCliente(Cliente cliente) {
		Optional<Reserva> reserva = reservaDaoImpl.findByClienteLikeAndFechaSalidaNotNull(cliente);
		if(reserva.isEmpty()) {
			return null;
		}else return reserva.get();
	}

}
