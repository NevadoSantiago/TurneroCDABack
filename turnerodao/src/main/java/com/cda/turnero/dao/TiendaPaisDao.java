package com.cda.turnero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Clinica;
import com.cda.turnero.model.TiendaPais;

public interface TiendaPaisDao extends JpaRepository<TiendaPais, Integer> {
	List<TiendaPais> findAllByClinica(Integer clinicaId);
}
