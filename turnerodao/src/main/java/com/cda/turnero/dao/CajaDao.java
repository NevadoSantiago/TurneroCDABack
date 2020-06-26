package com.cda.turnero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Caja;

public interface CajaDao extends JpaRepository<Caja, Integer>{

	List<Caja> getCajasByTipoCaja(Integer tipoCajaId);
	List<Caja> getCajasBySucursal(Integer sucursalId);
	List<Caja> getCajasByEstado(boolean estado);
	
}
