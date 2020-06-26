package com.cda.turnero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Tienda;
import com.cda.turnero.model.TiendaPais;

public interface TiendaPaisDao extends JpaRepository<TiendaPais, Integer> {
	List<TiendaPais> findAllByTienda(Integer tiendaId);
}
