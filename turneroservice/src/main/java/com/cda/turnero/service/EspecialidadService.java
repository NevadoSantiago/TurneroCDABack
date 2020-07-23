package com.cda.turnero.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.EspecialidadDao;
import com.cda.turnero.dao.SucursalEspecialidadDao;
import com.cda.turnero.model.Especialidad;
import com.cda.turnero.model.EspecialidadSucursal;

@Service
public class EspecialidadService {

	@Autowired
	EspecialidadDao especialidadDaoImpl;
	@Autowired
	SucursalEspecialidadDao sucursalEspecialidadDaoImpl;
	
	public List<Especialidad> getAllEspecialidades(){
		return especialidadDaoImpl.findAll();
	}
	public Especialidad getEspecialidadById(Integer id) {
		return especialidadDaoImpl.findById(id).get();
	}
	public Especialidad agregarEspecialidad(Especialidad especialidad) {
		return especialidadDaoImpl.save(especialidad);
	}
	public Boolean borrarEspecialidad(Integer idEspecialidad) {
		try {
			especialidadDaoImpl.deleteById(idEspecialidad);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public List<Especialidad> getEspecialidadesBySucursal(Integer idSucursal) {
		List<EspecialidadSucursal> espSuc = sucursalEspecialidadDaoImpl.getBySucursalLike(idSucursal);
		List<Especialidad> especialidades= new ArrayList<Especialidad>();
		for(EspecialidadSucursal ES : espSuc) {
			especialidades.add(ES.getEspecialidad());
		}
		return especialidades;
	}
}
