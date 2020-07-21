package com.cda.turnero.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/caja")
@RestController
public class CajaController {
	/*
	@Autowired
	CajaService cajaService;
	TurnoService turnoService;
	
	
	
	@PostMapping("/asignar/{QR}/{TipoCaja}/{SucursalId}")
	public ResponseEntity<?> asignarCaja(@PathVariable("QR") int QR,@PathVariable ("TipoCaja") int tipoCajaId,@PathVariable ("SucursalId") int sucursal_Id  ){
		return new ResponseEntity<>(cajaService.asignarCaja(QR, tipoCajaId,sucursal_Id),HttpStatus.OK);
		
	}
	
	@PostMapping("/iniciar/{QR}")
	public ResponseEntity<?> iniciarTransaccion(@PathVariable("QR") String QR){
		return new ResponseEntity<>(cajaService.iniciarTransaccion(), HttpStatus.OK);
	}
		
	@PostMapping("/finalizar/{Date}")
	public ResponseEntity<?> finalizarTransaccion(@PathVariable("Date") String fechaEgreso){
		return new ResponseEntity<>(cajaService.finalizarTransaccion(), HttpStatus.OK);
	}
		
	/*@GetMapping("/tipo/{QR}")
	public ResponseEntity<?> listaTipoCaja(@PathVariable("QR") String QR){
		return new ResponseEntity<>(cajaService.listadoTipoCaja(), HttpStatus.OK);
	}
		
	@PatchMapping("/habilitarCaja/{IdCaja}")
	public ResponseEntity<?> habilitarCaja(@PathVariable("IdCaja") Integer idCaja){
		return new ResponseEntity<>(cajaService.habilitarCaja(idCaja), HttpStatus.OK);
	}
		
	@PatchMapping("/deshabilitarCaja/{IdCaja}")
	public ResponseEntity<?> listaTipoCaja(@PathVariable("IdCaja") Integer idCaja){
		return new ResponseEntity<>(cajaService.deshabilitarCaja(idCaja), HttpStatus.OK);
	}
		
	@GetMapping("/todasLasCajas")
	public ResponseEntity<?> todasLasCajas(){
		return new ResponseEntity<>(cajaService.getCajas(), HttpStatus.OK);
	}
		
	@PostMapping("/agregarCaja/{TipoCajaId}/{SucursalId}")
	public ResponseEntity<?> agregarCaja(@PathVariable("TipoCajaId") Integer tipoCajaId, @PathVariable("SucursalId") Integer sucursalId){
		return new ResponseEntity<>(cajaService.agregarCaja(tipoCajaId, sucursalId), HttpStatus.OK);
	}
	
	@GetMapping("/{IdCaja}")
	public ResponseEntity<?> verCaja(@PathVariable("IdCaja") Integer idCaja){
		return new ResponseEntity<>(cajaService.getCajaById(idCaja), HttpStatus.OK);
	}
	
	@GetMapping("/tipo/{IdTipoCaja}")
	public ResponseEntity<?> verCajasPorTipo(@PathVariable("IdTipoCaja") Integer idTipoCaja){
		return new ResponseEntity<>(cajaService.getCajasByTipoCaja(idTipoCaja), HttpStatus.OK);
	}
	
	@GetMapping("/sucursal/{IdSucursal}")
	public ResponseEntity<?> verCajasPorSucursal(@PathVariable("IdSucursal") Integer idSucursal){
		return new ResponseEntity<>(cajaService.getCajasBySucursal(idSucursal), HttpStatus.OK);
	}
	
	@PostMapping("/agregarTipoCaja/{detalle}")
	public ResponseEntity<?> crearTipoDeCaja(@PathVariable("detalle") String detalle){
		return new ResponseEntity<>(cajaService.crearTipoDeCaja(detalle), HttpStatus.OK);
	}
	
	@GetMapping("/cajasPorEstado/{estado}")
	public ResponseEntity<?> verCajasPorEstado(@PathVariable("estado") Boolean estado){
		return new ResponseEntity<>(cajaService.getCajasByEstado(estado), HttpStatus.OK);
	}
	@GetMapping("/deshabilitarQr/{idQr}")
	public ResponseEntity<?> deshabilitarQr(@PathVariable("idQr") Integer idQr){
		return new ResponseEntity<>(cajaService.deshabilitarQr(idQr), HttpStatus.OK);
	}
	*/
}
