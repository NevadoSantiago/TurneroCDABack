package com.cda.turnero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Sucursal;
import com.cda.turnero.model.SucursalTipoTurno;

public interface SucursalTipoTurnoDao extends JpaRepository<SucursalTipoTurno, Integer> {
	List<SucursalTipoTurno> findAllBySucursal(Sucursal sucursal);
	
}
