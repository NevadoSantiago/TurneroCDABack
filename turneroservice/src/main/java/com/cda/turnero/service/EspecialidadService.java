package com.cda.turnero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.EspecialidadDao;
import com.cda.turnero.model.Especialidad;

@Service
public class EspecialidadService {

	@Autowired
	EspecialidadDao especialidadDaoImpl;
	
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
}
