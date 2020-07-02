package com.cda.turnero.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Cliente;

public interface ClienteDao extends JpaRepository<Cliente,Integer> {
	
	Optional<Cliente> findByMailLike(String mail);

}
