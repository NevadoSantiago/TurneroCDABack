package com.cda.turnero.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.ConfiguracionSucursalDao;
import com.cda.turnero.dao.EmpleadoDao;
import com.cda.turnero.dao.ReservaDao;
import com.cda.turnero.dao.SucursalDao;
import com.cda.turnero.dao.SucursalEspecialidadDao;
import com.cda.turnero.dto.DetalleEmpleadoDto;
import com.cda.turnero.dto.DetalleSucursalDto;
import com.cda.turnero.model.ConfiguracionSucursal;
import com.cda.turnero.model.Empleado;
import com.cda.turnero.model.EspecialidadSucursal;
import com.cda.turnero.model.Sucursal;

@Service
public class SucursalService {

	@Autowired
	SucursalDao sucursalDaoImpl;
	@Autowired
	SucursalEspecialidadDao sucursalEspecialidadDao;
	@Autowired
	ConfiguracionSucursalDao configuracionDao;
	@Autowired
	EmpleadoDao empleadoDaoImpl;
	@Autowired
	ReservaDao reservaDaoImpl;
	public List<Sucursal> getSucursalesByNombreLike(String nombre){
		List<Sucursal> sucursales = sucursalDaoImpl.findAllByNombreContaining(nombre);
		return sucursales;
	}
	
	public List<Sucursal> getSucursalesByEspecialidad(Integer idEspecialidad){
		
		List<EspecialidadSucursal> ES =
		sucursalEspecialidadDao.getSucursalEspecialidadByEspecialidadId(idEspecialidad);
		
		List<Sucursal> sucursales = new ArrayList<Sucursal>();
		for(EspecialidadSucursal espSuc : ES) {
			sucursales.add(espSuc.getSucursal());
		}
		return sucursales;
	}
	public List<Sucursal> getAllSucursales() {	
		return sucursalDaoImpl.findAll();
	}
	public Sucursal getSucursalById(Integer idSucursal) {
		return sucursalDaoImpl.findById(idSucursal).get();
	}
	public List<DetalleSucursalDto> getSucursalesByReservas(Integer idEspecialidad) {
		return sucursalDaoImpl.getSucursalesByReservas(idEspecialidad);
	}
	
	public ConfiguracionSucursal addConfiguracionSucursal(ConfiguracionSucursal configuracion) {
		return configuracionDao.save(configuracion);
	}
	
	public Sucursal addSucursal(Sucursal sucursal, ConfiguracionSucursal configuracion) {
		return sucursalDaoImpl.save(sucursal);
	}
	
	public Boolean deleteSucursal(Integer idSucursal, Integer idConfiguracion) {
		try {
			sucursalDaoImpl.deleteById(idSucursal);
			configuracionDao.deleteById(idConfiguracion);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public List<DetalleEmpleadoDto> getEmpleadosByRolAndSucursal(String rol, Integer sucursalId) {
		List<Empleado> listEmpleados = empleadoDaoImpl.getEmpleadosByRolAndSucursalId(rol, sucursalId);
		List<DetalleEmpleadoDto> DE = new ArrayList<DetalleEmpleadoDto>();
		for(Empleado e : listEmpleados) {
			DE.add(detalleEmpleadoMapper(e));
		}
		return DE;
		
	}
	private DetalleEmpleadoDto detalleEmpleadoMapper(Empleado empleado){
		Integer idEmpleado = empleado.getPersonaId();
		String nombre = empleado.getNombre();
		String apellido = empleado.getApellido();
		String mail = empleado.getMail();
		String rol = empleado.getUsuario().getTipoUsuario().getDetalle();
		return new DetalleEmpleadoDto(idEmpleado, nombre, apellido, mail, rol);
	}

	public Long getCantidadReservasPorSucursal(Integer sucursalId) {
		return reservaDaoImpl.getCantidadReservasPorSucursal(sucursalId);
	}
	
}
