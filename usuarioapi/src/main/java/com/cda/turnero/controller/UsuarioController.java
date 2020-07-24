package com.cda.turnero.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
	@PostMapping("/eliminar/{idUsuario}")
	public ResponseEntity<?> ingresoDeUsuario(@PathVariable("idUsuario") Integer usuarioId){
			return new ResponseEntity<>(usuarioService.eliminarUsuario(usuarioId), HttpStatus.OK);
	}
	@GetMapping("/get/roles")
	public ResponseEntity<?> getRoles(){
			return new ResponseEntity<>(usuarioService.getRoles(), HttpStatus.OK);
	}
	@PatchMapping("/editar")
	public ResponseEntity<?> editarUsuario(@RequestBody String datos){
			return new ResponseEntity<>(usuarioService.editarUsuario(datos), HttpStatus.OK);
	}
}
