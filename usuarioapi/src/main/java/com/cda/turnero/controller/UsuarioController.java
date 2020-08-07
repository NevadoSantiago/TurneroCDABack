package com.cda.turnero.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cda.turnero.dto.EmpleadoNoRegistradoDto;
import com.cda.turnero.dto.UsuarioLogueadoDto;
import com.cda.turnero.service.UsuarioService;
import com.cda.turnero.token.utils.JwtTokenUtil;


@CrossOrigin(origins = "*")
@RequestMapping("/api/usuario")
@RestController
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	@Autowired
	JwtTokenUtil jwtUtil;
	
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
	@PostMapping("/auth/token")
	public ResponseEntity<?> ingresoDeUsuarioByToken(@RequestBody String token) {
		try {
			UsuarioLogueadoDto uLogueado = usuarioService.logueoByToken(token);
			return new ResponseEntity<>(uLogueado, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>("Token invalido", HttpStatus.NOT_FOUND);	
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
	@PutMapping("/editar")
	public ResponseEntity<?> editarUsuario(@RequestBody String datos){
			return new ResponseEntity<>(usuarioService.editarUsuario(datos), HttpStatus.OK);
	}
	@PostMapping("/create/empleado")
	public ResponseEntity<?> crearEmpleado(@RequestBody String datos){
			return new ResponseEntity<>(usuarioService.crearEmpleado(datos), HttpStatus.OK);
	}
	@PostMapping("/create")
	public ResponseEntity<?> crearEmpleadoFinal(@RequestBody String datos){
		try {
			UsuarioLogueadoDto uLogueado = usuarioService.crearEmpleadoFinal(datos);
			return new ResponseEntity<>(uLogueado, HttpStatus.OK);
		}catch(IllegalArgumentException error) {
			System.out.println("Illegal");
			return new ResponseEntity<>(error.getMessage(), HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			System.out.println("ERROR");
			return new ResponseEntity<>("ERROR carga usuario", HttpStatus.BAD_REQUEST);
		}
			
	}
	@GetMapping("/validarCodigo/{codigo}")
	public ResponseEntity<?> validarCodigo(@PathVariable("codigo") String codigo){
		try {
			String nombreApellido = usuarioService.validarCodigo(codigo);
			return new ResponseEntity<>(nombreApellido, HttpStatus.OK);
		}catch(Exception e) {
			System.out.println("ERROR CODIGO");
			return new ResponseEntity<>("CODIGO INVALIDO", HttpStatus.FORBIDDEN);
		}
			
	}
	@GetMapping("/empleadosNoRegistrados")
	public ResponseEntity<?> getListadoEmpleadosNoRegistrados(){
		try {
			List<EmpleadoNoRegistradoDto> empleadosNR = usuarioService.getListadoEmpleadosNoRegistrados();
			return new ResponseEntity<>(empleadosNR, HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(new ArrayList<EmpleadoNoRegistradoDto>(), HttpStatus.FORBIDDEN);
		}
			
	}
//	@DeleteMapping("delete/empleadoNoRegistrado/{idEmpleado}")
//	public ResponseEntity<?> deteleEmpleadoNoRegistrado(@PathVariable("idEmpleado") Integer idEmpleado){
//		try {
//			boolean eliminado = usuarioService.deleteEmpleadoNoRegistrado(idEmpleado);
//			return new ResponseEntity<>(eliminado, HttpStatus.OK);
//		}catch(Exception e) {
//			return new ResponseEntity<>(new ArrayList<EmpleadoNoRegistradoDto>(), HttpStatus.FORBIDDEN);
//		}
//			
//	}
}
