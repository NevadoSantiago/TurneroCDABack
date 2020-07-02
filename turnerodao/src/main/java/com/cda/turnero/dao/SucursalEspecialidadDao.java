package com.cda.turnero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.EspecialidadSucursal;

public interface SucursalEspecialidadDao extends JpaRepository<EspecialidadSucursal, Integer> {

	List<EspecialidadSucursal> getSucursalEspecialidadByEspecialidadId(Integer especialidadId);
	
}
