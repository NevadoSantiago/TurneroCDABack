package com.cda.turnero.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Clinica;

public interface TiendaDao extends JpaRepository<Clinica, Integer>{
	
}
