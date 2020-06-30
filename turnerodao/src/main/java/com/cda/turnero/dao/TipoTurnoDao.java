package com.cda.turnero.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Especialidad;

public interface TipoTurnoDao extends JpaRepository<Especialidad, Integer>{

	
	
}
