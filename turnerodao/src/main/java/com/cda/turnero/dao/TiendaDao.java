package com.cda.turnero.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Tienda;

public interface TiendaDao extends JpaRepository<Tienda, Integer>{
	
}
