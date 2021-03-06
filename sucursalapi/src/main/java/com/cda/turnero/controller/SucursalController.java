package com.cda.turnero.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cda.turnero.dto.DetalleEmpleadoDto;
import com.cda.turnero.dto.DetalleReservaAdminDto;
import com.cda.turnero.dto.DetalleSucursalDto;
import com.cda.turnero.model.ConfiguracionSucursal;
import com.cda.turnero.model.Empleado;
import com.cda.turnero.model.Reserva;
import com.cda.turnero.model.Sucursal;
import com.cda.turnero.service.SucursalService;


@CrossOrigin(origins="*")
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
	@GetMapping("/get/empleado/{rol}/{sucursalId}")
	public ResponseEntity<?> getPersonalESBySucursal(@PathVariable("sucursalId") Integer sucursalId,
													@PathVariable("rol") String rol){
													
		List<DetalleEmpleadoDto> empleados = sucursalService.getEmpleadosByRolAndSucursal(rol, sucursalId);
		
		return new ResponseEntity<>(empleados, HttpStatus.OK);
	}
	@GetMapping("/get/espera/{sucursalId}")
	@PreAuthorize("hasAuthority('ADMIN_SUCURSAL') or hasAuthority('RECEPCION')")
	public ResponseEntity<?> getEsperaPorSucursal(@PathVariable("sucursalId") Integer sucursalId){
													
		Long cantidad = sucursalService.getCantidadReservasPorSucursal(sucursalId);
		
		return new ResponseEntity<>(cantidad, HttpStatus.OK);
	}
	@GetMapping("/get/listadoEspera/{sucursalId}")
	public ResponseEntity<?> getListadoEsperaPorSucursal(@PathVariable("sucursalId") Integer sucursalId){
													
		List<DetalleReservaAdminDto> DR = sucursalService.getListadoEsperaPorSucursalId(sucursalId);
		
		return new ResponseEntity<>(DR, HttpStatus.OK);
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
	@PreAuthorize("hasAuthority('ADMIN_GENERAL')")
	@GetMapping("/agregar/{direccion}/{nombre}/{latitud}/{longitud}")
	public ResponseEntity<?> addSucursales(@PathVariable("direccion") String direccion,
											@PathVariable("nombre") String nombre,
											@PathVariable("latitud") String latitud,
											@PathVariable("longitud") String longitud){
		ConfiguracionSucursal configuracion = new ConfiguracionSucursal();
		configuracion.setCordLatitud(latitud);
		configuracion.setCordLongitud(longitud);
		configuracion = sucursalService.addConfiguracionSucursal(configuracion);
		Sucursal sucursal = new Sucursal();
		sucursal.setConfiguracion(configuracion);;
		sucursal.setDireccion(direccion);
		sucursal.setNombre(nombre);
		sucursal.setHabilitada(true);
		
		return new ResponseEntity<>(sucursalService.addSucursal(sucursal, configuracion), HttpStatus.OK);
	}
	
	
	@GetMapping("/borrar/{idSucursal}")
	@PreAuthorize("hasAuthority('ADMIN_GENERAL') or hasAuthority('ADMIN_SUCURSAL')")
	public ResponseEntity<?> deleteSucursal(@PathVariable("idSucursal") Integer idSucursal){
		return new ResponseEntity<>(sucursalService.deleteSucursal(idSucursal), HttpStatus.OK);
	}
	@GetMapping("/admin/{idSucursal}")
	@PreAuthorize("hasAuthority('ADMIN_GENERAL') or hasAuthority('ADMIN_SUCURSAL')")
	public ResponseEntity<?> getAdministradoresDeSucursal(@PathVariable("idSucursal") Integer idSucursal){
		return new ResponseEntity<>(sucursalService.getAdminSucursal(idSucursal), HttpStatus.OK);
	}
	@PostMapping("/delete/{idAdmin}")
	@PreAuthorize("hasAuthority('ADMIN_GENERAL') or hasAuthority('ADMIN_SUCURSAL')")
	public ResponseEntity<?> deleteAdministradorDeSucursal(@PathVariable("idAdmin") Integer idAdmin){
		return new ResponseEntity<>(sucursalService.deleteAdminDeSucursal(idAdmin), HttpStatus.OK);
	}
	//ESTADISTICAS
	@GetMapping("/tiempoPromedio/{idEspecialidad}/{idSucursal}")
	@PreAuthorize("hasAuthority('ADMIN_GENERAL')")
	public ResponseEntity<?> getTiempoPromedioPorEspecialidadYSucursal(@PathVariable("idSucursal") Integer idSucursal,
																		@PathVariable("idEspecialidad") Integer idEspecialidad){
		return new ResponseEntity<>(sucursalService.getTiempoPromedioEspecialidadSucursal(idSucursal,idEspecialidad), HttpStatus.OK);
	}
	@GetMapping("/listadoAgrupado/espera/{idSucursal}")
	public ResponseEntity<?> getListadoAgrupadoPorEspecialidad(@PathVariable("idSucursal") Integer idSucursal){
		return new ResponseEntity<>(sucursalService.getListadoAgrupadoPorEspecialidad(idSucursal), HttpStatus.OK);
	}

	

}
