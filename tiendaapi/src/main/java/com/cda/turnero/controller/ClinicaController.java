package com.cda.turnero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cda.turnero.model.Clinica;
import com.cda.turnero.model.Especialidad;
import com.cda.turnero.service.ClinicaService;
import com.cda.turnero.service.EspecialidadService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/clinica")
@RestController
public class ClinicaController {
	
	@Autowired
	ClinicaService clinicaService;

	
	@GetMapping("/filtrar/{idEspecialidad}")
	public ResponseEntity<?> getClinicasByEspecialidad(
			@PathVariable("idEspecialidad") Integer idEspecialidad){
		
		List<Clinica> clinicas = clinicaService.getClinicasByEspecialidad(idEspecialidad);
		
		return new ResponseEntity<>(clinicas, HttpStatus.OK);
	}

           
}
