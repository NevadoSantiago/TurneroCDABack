package com.cda.turnero.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cda.turnero.service.LocacionService;

@CrossOrigin(origins="*")
@RequestMapping("/api/locacion")
@RestController
public class LocacionController {

	@Autowired
	LocacionService locacionService;
	
	
	@GetMapping("/provincias")
	//@PreAuthorize("hasAuthority('ADMIN_GENERAL')")
	public ResponseEntity<?> getProvincia() {
		return new ResponseEntity<>(locacionService.getAllPronvincias(), HttpStatus.OK);	
	}
	
	@GetMapping("/localidades/{provinciaId}")
	@PreAuthorize("hasAuthority('ADMIN_GENERAL')")
	public ResponseEntity<?> getLocalidadByProvincia(@PathVariable("provinciaId") Integer provinciaId) {
		return new ResponseEntity<>(locacionService.getLocalidadByProvinciaId(provinciaId), HttpStatus.OK);	
	}
}
