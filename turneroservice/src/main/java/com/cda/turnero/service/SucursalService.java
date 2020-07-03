package com.cda.turnero.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.SucursalDao;
import com.cda.turnero.dao.SucursalEspecialidadDao;
import com.cda.turnero.model.EspecialidadSucursal;
import com.cda.turnero.model.Sucursal;

@Service
public class SucursalService {

	@Autowired
	SucursalDao sucursalDaoImpl;
	@Autowired
	SucursalEspecialidadDao sucursalEspecialidadDao;
	
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
	
}
