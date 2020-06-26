package com.cda.turnero.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.TurnoClienteDao;
import com.cda.turnero.model.TurnoCliente;

@Service
public class TurnoClienteService {

	@Autowired
	TurnoClienteDao turnoClienteDao;
	
	public TurnoCliente getTurnoClienteByTurnoId(Integer turnoClienteId) {
		return turnoClienteDao.getTurnoClienteByTurnoId(turnoClienteId);
	}
	
}
