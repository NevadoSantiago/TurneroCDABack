package com.cda.turnero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Recepcion;

public interface CajaDao extends JpaRepository<Recepcion, Integer>{

	List<Recepcion> getCajasByTipoCaja(Integer tipoCajaId);
	List<Recepcion> getCajasBySucursal(Integer sucursalId);
	List<Recepcion> getCajasByEstado(boolean estado);
	
}
