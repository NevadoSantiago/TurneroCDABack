package com.cda.turnero.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.TipoUsuario;

public interface TipoUsuarioDao extends JpaRepository<TipoUsuario, Integer>{
	
	TipoUsuario findByDetalleLike(String detalle);
	
}
