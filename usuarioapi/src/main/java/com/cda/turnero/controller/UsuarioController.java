package com.cda.turnero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cda.turnero.dto.UsuarioLogueadoDto;
import com.cda.turnero.service.UsuarioService;


@CrossOrigin(origins = "*")
@RequestMapping("/api/usuario")
@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@PostMapping("/ingresar/{mail}")
	public ResponseEntity<?> ingresoDeCliente(@PathVariable("mail") String mail) {
		return new ResponseEntity<>(usuarioService.ingresoCliente(mail), HttpStatus.OK);	
	}
	@PostMapping("/auth")
	public ResponseEntity<?> ingresoDeUsuario(@RequestBody String autenticacion) {
		try {
			UsuarioLogueadoDto uLogueado = usuarioService.ingresoUsuario(autenticacion);
			return new ResponseEntity<>(uLogueado, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);	
		}
	}
}
