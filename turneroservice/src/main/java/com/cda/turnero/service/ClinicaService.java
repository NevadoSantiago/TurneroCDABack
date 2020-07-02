package com.cda.turnero.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.ClinicaDao;
import com.cda.turnero.dao.ClinicaEspecialidadDao;
import com.cda.turnero.model.Clinica;
import com.cda.turnero.model.EspecialidadClinica;

@Service
public class ClinicaService {

	@Autowired
	ClinicaDao clinicaDaoImpl;
	@Autowired
	ClinicaEspecialidadDao clinicaEspecialidadDaoImpl;

	public List<Clinica> getAllClinicas() {
		return clinicaDaoImpl.findAll();
	}

	public List<Clinica> getClinicasByEspecialidad(Integer idEspecialidad) {
		List<EspecialidadClinica> EC = clinicaEspecialidadDaoImpl.getByIdEspecialidad(idEspecialidad);
		List<Clinica> clinicas = new ArrayList<Clinica>();
		for (EspecialidadClinica es : EC) {
			clinicas.add(es.getClinica());
		}
		return clinicas;
	}

}
