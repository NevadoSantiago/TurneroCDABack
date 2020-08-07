package com.cda.turnero.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.model.Empleado;
import com.cda.turnero.model.Usuario;

public interface EmpleadoDao extends JpaRepository<Empleado, Integer> {
	
	Empleado getEmpleadoByUsuarioYContrasena(String usuario, String contrasena);
	
	List<Empleado> getEmpleadosByRolAndSucursalId(String rol, Integer sucursalId);
	
	Empleado getEmpleadoByUsername(String usuario);
	
	
	Empleado findByMailLikeOrNroDocumentoLike(String mail, String dni);

	Empleado getAndValidateNombreEmpleadoByIdsCodigo(Integer idEmpleado, Integer idUsuario);

	List<Empleado> findByFechaAltaIsNull();

}
