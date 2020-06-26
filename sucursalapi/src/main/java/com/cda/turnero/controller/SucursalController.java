package com.cda.turnero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cda.turnero.services.SucursalService;


@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {

	@Autowired
	SucursalService sucursalService;
	
	
	@GetMapping("/tipoTurnoSucursal/{idSucursal}")
	public ResponseEntity<?> getTipoTurnoBySucursal(@PathVariable("idSucursal")Integer idSucursal){
		
		return new ResponseEntity<>(sucursalService.getAllTipoTurnoBySucursal(idSucursal), HttpStatus.OK);
	}
}
