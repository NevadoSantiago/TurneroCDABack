package com.cda.turnero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cda.turnero.service.UsuarioService;



@RequestMapping("/api/usuario")
@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/ingresar/{mail}")
	public ResponseEntity<?> ingresoDeUsuario(@PathVariable("mail") String mail) {
		return new ResponseEntity<>(usuarioService.ingresoUsuario(mail), HttpStatus.OK);	
	}
}
