package com.cda.turnero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cda.turnero.services.TurnoService;

@RestController
@RequestMapping ("/proceso")
public class GenerateController {

	@Autowired
	TurnoService turnoService;
	
	@PostMapping("/generarTurno/{fechaDesde}/{cantidadDias}/{sucursalId}")// 
	public ResponseEntity<?> generarTurno(@PathVariable("fechaDesde") String fechaDesde, @PathVariable("cantidadDias") Integer cantidadDias, @PathVariable("sucursalId") Integer sucursalId ) {
		return new ResponseEntity<>(turnoService.generateTurnos(sucursalId, fechaDesde, cantidadDias), HttpStatus.OK);	
	}
}
