package com.cda.turnero.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Sucursal;

public interface SucursalDao extends JpaRepository<Sucursal, Integer> {
	
	List<Sucursal> findAllByNombreContaining(String nombre);
	
	List<Object[]> getSucursalesByReservas();
	
}
