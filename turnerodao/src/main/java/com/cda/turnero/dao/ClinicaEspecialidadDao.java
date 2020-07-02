package com.cda.turnero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.EspecialidadClinica;

public interface ClinicaEspecialidadDao extends JpaRepository<EspecialidadClinica, Integer> {

	List<EspecialidadClinica> getByIdEspecialidad(Integer idEspecialidad);
	
}
