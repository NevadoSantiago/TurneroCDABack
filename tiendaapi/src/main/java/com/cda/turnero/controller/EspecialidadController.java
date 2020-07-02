package com.cda.turnero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cda.turnero.model.Especialidad;
import com.cda.turnero.service.EspecialidadService;


@CrossOrigin(origins = "*")
@RequestMapping("/api/especialidad")
@RestController
public class EspecialidadController {

	@Autowired
	EspecialidadService especialidadService;
	
	@GetMapping()
	public ResponseEntity<?> getAllEspecialidades(){
		
		List<Especialidad> especialidades = especialidadService.getAllEspecialidades();
		
		return new ResponseEntity<>(especialidades, HttpStatus.OK);
	}
	
}
