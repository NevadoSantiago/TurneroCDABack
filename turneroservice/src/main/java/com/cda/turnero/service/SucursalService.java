package com.cda.turnero.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.ConfiguracionSucursalDao;
import com.cda.turnero.dao.SucursalDao;
import com.cda.turnero.dao.SucursalEspecialidadDao;
import com.cda.turnero.dto.DetalleSucursalDto;
import com.cda.turnero.model.ConfiguracionSucursal;
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
	
}
