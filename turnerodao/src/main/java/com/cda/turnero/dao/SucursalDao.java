
package com.cda.turnero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cda.turnero.model.Localidad;

import com.cda.turnero.model.Sucursal;
import com.cda.turnero.model.Tienda;

public interface SucursalDao extends JpaRepository<Sucursal, Integer>{

	List<Sucursal> findSucursalesByTienda(Tienda tienda);
	List<Sucursal> findSucursalesByTiendaAndLocalidad(Tienda tienda, Localidad localidad);


}

