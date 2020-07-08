package com.cda.turnero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Localidad;
import com.cda.turnero.model.Provincia;

public interface LocalidadDao extends JpaRepository<Localidad, Integer>{
	
	List<Localidad> findByProvinciaLike(Provincia provincia);

}
