package com.cda.turnero.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cda.turnero.dto.DetalleTurnoDto;
import com.cda.turnero.dto.TurnoDisponibleDto;
import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.CodigoQr;
import com.cda.turnero.model.Pais;
import com.cda.turnero.model.Clinica;
import com.cda.turnero.model.Turno;
import com.cda.turnero.model.TurnoCliente;
import com.cda.turnero.services.CodigoQrService;
import com.cda.turnero.services.SucursalService;
import com.cda.turnero.services.TiendaService;
import com.cda.turnero.services.TurnoClienteService;
import com.cda.turnero.services.TurnoService;
import com.cda.turnero.services.UbicacionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

//import com.services.services.service.TurnoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/turno")
public class TurnoController {
	
//	@Autowired
//	UbicacionService ubiUser;
	
	@Autowired
	TiendaService tiendaService;
	@Autowired
	SucursalService sucursalService;
	
	@Autowired
	TurnoService turnoService;
	
	@Autowired
	TurnoClienteService turnoClienteService;
	
	@Autowired
	UbicacionService ubicacionService;	
	
	@Autowired 
	CodigoQrService qrService;
	
	@ResponseBody
	@RequestMapping(value = "/QR/{turnoId}/{idCliente}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getQRImageFromTurno(@PathVariable("turnoId") Integer turnoId,
													  @PathVariable("idCliente") Integer idCliente	) {
		
	    byte[] image = turnoService.getCodigoQrByTurnoAndCliente(turnoId, idCliente);
	    return ResponseEntity.ok(image);
	}
	
	@GetMapping("/consultarTurnos/{idCliente}/{fecha}")
	public ResponseEntity<?> ConsultarTurnosPorFecha(@PathVariable("idCliente") Integer idCliente,
											@PathVariable("fecha") @DateTimeFormat(pattern="yyyy-MM-dd") Date fecha) {
		return new ResponseEntity<>(turnoService.consultarTurnosPorFecha(fecha, idCliente), HttpStatus.OK);	
	}
	
	@GetMapping("/consultarTurno/{idTurno}/{idCliente}")
	public ResponseEntity<?> ConsultarTurno(@PathVariable("idCliente") Integer idCliente,
											@PathVariable("idTurno") Integer idTurno) {
		return new ResponseEntity<>(turnoService.detallarTurno(idTurno, idCliente), HttpStatus.OK);	
	}

	@RequestMapping(value = "/asignarTurno/{idTurno}/{idCliente}", method = RequestMethod.PATCH)
	public ResponseEntity<?> GuardarTurno(
											@PathVariable("idCliente") Integer idCliente,
											@PathVariable("idTurno") Integer idTurno
			) {
		try {
			TurnoCliente turno = turnoService.asignarTurno(idCliente, idTurno);
			return new ResponseEntity<>(turno, HttpStatus.OK);
		}catch(IllegalArgumentException turnoYaSolicitado) {
			return new ResponseEntity<>(null, HttpStatus.IM_USED);
		}catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
	}

	@PutMapping("/cancelarTurno/{turnoId}/{clienteId}")
	public ResponseEntity<?> CancelarTurno(@PathVariable("turnoId") Integer turnoId, 
										@PathVariable("clienteId") Integer clienteId) {
		return new ResponseEntity<>(turnoService.cancelarTurno(turnoId, clienteId), HttpStatus.OK);	
	}
	
	@GetMapping("/listarTurno/{idSucursal}/{fechaDesde}")// 
	public ResponseEntity<?> listarTurnoPorSucursal(@PathVariable("idSucursal") Integer idSucursal,
													@PathVariable("fechaDesde") @DateTimeFormat(pattern="yyyy-MM-dd") Date fechaDesde){
		List<TurnoDisponibleDto> lista = turnoService.getTurnosBySucursalYFechaDesdeYHasta(idSucursal, fechaDesde);
	return new ResponseEntity<>(lista, HttpStatus.OK);
		
	}
	
	@GetMapping("/consultarTurnosCliente/{clienteId}")// 
	public ResponseEntity<?> consultarTurnosCliente(@PathVariable("clienteId") Integer clienteId) {
		
		List<DetalleTurnoDto> lista = turnoService.getTurnosByCliente(clienteId);
			return new ResponseEntity<>(lista, HttpStatus.OK);	
		
	}
	
	@GetMapping("/consultarProvinciaPorTiendaYPais/{tiendaId}/{paisId}")// 
	public ResponseEntity<?> consultarProvinciaPorTiendaYPais(@PathVariable("tiendaId") Integer tiendaId, @PathVariable("paisId") Integer paisId) {
		return new ResponseEntity<>(ubicacionService.getProvinciaByTiendaYPais(tiendaId, paisId), HttpStatus.OK);	
	}
	@GetMapping("/consultarPais/{tiendaId}")// 
	public ResponseEntity<?> consultarPais(@PathVariable("tiendaId") Integer tiendaId) {
		return new ResponseEntity<>(ubicacionService.getPaisByTienda(tiendaId), HttpStatus.OK);	
	}
	@GetMapping("/consultarProvincia/{tiendaId}")// 
	public ResponseEntity<?> consultarProvincia(@PathVariable("tiendaId") Integer tiendaId) {
		return new ResponseEntity<>(ubicacionService.getProvinciaByTienda(tiendaId), HttpStatus.OK);	
	}
	@GetMapping("/consultarLocalidadPorTienda/{tiendaId}")// 
	public ResponseEntity<?> consultarLocalidad(@PathVariable("tiendaId") Integer tiendaId) {
		return new ResponseEntity<>(ubicacionService.getLocalidadesByTienda(tiendaId), HttpStatus.OK);	
	}
	@GetMapping("/consultarSucursalesPorTiendaYlocalidad/{tiendaId}/{localidadId}")// 
	public ResponseEntity<?> consultarSucursales(@PathVariable("tiendaId") Integer tiendaId,@PathVariable("localidadId") Integer localidadId) {
		return new ResponseEntity<>(sucursalService.getSucursalesByTiendaAndLocalidad(tiendaId,localidadId), HttpStatus.OK);	
	}
	@GetMapping("/consultarSucursalesPorTienda/{tiendaId}")// 
	public ResponseEntity<?> consultarSucursales(@PathVariable("tiendaId") Integer tiendaId) {
		return new ResponseEntity<>(sucursalService.getSucursalesByTienda(tiendaId), HttpStatus.OK);	
	}
	
}
