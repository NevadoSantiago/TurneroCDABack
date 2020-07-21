package com.cda.turnero.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Empleado;
import com.cda.turnero.model.Usuario;

public interface EmpleadoDao extends JpaRepository<Empleado, Integer> {
	
	Empleado getEmpleadoByUsuarioYContrasena(String usuario, String contrasena);

}
