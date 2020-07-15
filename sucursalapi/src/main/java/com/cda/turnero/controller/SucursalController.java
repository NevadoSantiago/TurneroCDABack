package com.cda.turnero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cda.turnero.dto.DetalleSucursalDto;
import com.cda.turnero.model.Sucursal;
import com.cda.turnero.service.SucursalService;



@RestController
@RequestMapping("/api/sucursal")
public class SucursalController {

	@Autowired
	SucursalService sucursalService;
	
	@GetMapping("/filtrar/{nombre}")
	public ResponseEntity<?> filtrarPorNombreLike(@PathVariable("nombre") String nombre){
		
		List<Sucursal> sucursales = sucursalService.getSucursalesByNombreLike(nombre);
		
		return new ResponseEntity<>(sucursales, HttpStatus.OK);
	}
	@GetMapping("/filtrar/especialidad/{idEspecialidad}")
	public ResponseEntity<?> filtrarPorEspecialidad(
			@PathVariable("idEspecialidad") Integer idEspecialidad){
		
		List<Sucursal> sucursales = sucursalService.getSucursalesByEspecialidad(idEspecialidad);
		
		return new ResponseEntity<>(sucursales, HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<?> getAllSucursales(){
		
		List<Sucursal> sucursales = sucursalService.getAllSucursales();
		
		return new ResponseEntity<>(sucursales, HttpStatus.OK);
	}
	
	@GetMapping("/filtrar/cantidadPersonas/{idEspecialidad}")
	public ResponseEntity<?> getSucursalesByReserva(@PathVariable("idEspecialidad") Integer idEspecialidad){
		
		List<DetalleSucursalDto> SR = sucursalService.getSucursalesByReservas(idEspecialidad);
		
		return new ResponseEntity<>(SR, HttpStatus.OK);
	}

	

}
