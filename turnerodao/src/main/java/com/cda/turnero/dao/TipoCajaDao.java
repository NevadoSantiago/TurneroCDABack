package com.cda.turnero.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cda.turnero.model.TipoCaja;

public interface TipoCajaDao extends JpaRepository<TipoCaja, Integer>{
	
	
	
}
