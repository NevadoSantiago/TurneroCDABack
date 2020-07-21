package com.cda.turnero.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.ConfiguracionSucursal;

public interface ConfiguracionSucursalDao extends JpaRepository<ConfiguracionSucursal,Integer>{

}
