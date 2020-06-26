package com.cda.turnero.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cda.turnero.model.Pais;
import com.cda.turnero.model.Sucursal;
import com.cda.turnero.model.Tienda;
import com.cda.turnero.services.SucursalService;
import com.cda.turnero.services.TiendaService;
import com.cda.turnero.services.UbicacionService;

@CrossOrigin(origins = "*")
@RequestMapping("/api/tienda")
@RestController
public class TiendaController {
	
	@Autowired
	TiendaService tiendaService;
	
	@Autowired
	UbicacionService ubiService;

	@Autowired
	SucursalService sucService;
	
	@GetMapping("/consultarTiendas")// {email}/{codigoQR}
	public ResponseEntity<?> ConsultarTienda(/* Email email, CodigoQR codigoQR*/) {
		
		
		try {
			List<Tienda> listadoTiendas = tiendaService.getAllTienda();
			
			return new ResponseEntity<>(listadoTiendas, HttpStatus.OK);
			
		}catch(Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
			
		}
		
		

	}
	
	@GetMapping("/tienda/{id}")
	public List<Pais> getPaisByTiendaId(@PathVariable Integer id) {
		return ubiService.getAllPaisByTienda(id);
	}
	
	@GetMapping("/tipoTurno")
	public ResponseEntity<?> listadoDeTipoTurno(){
		
		return new ResponseEntity<>(tiendaService, HttpStatus.OK);
		
	}
        
        @GetMapping("/sucursales/{tienda}")
	public ResponseEntity<?> getSucursalByTienda(@PathVariable Integer tienda) {
    		try {
    			List<Sucursal> sucursal = sucService.getSucursalesByTienda(tienda);
    			return new ResponseEntity<>(sucursal, HttpStatus.OK);
    			
    		}catch(Exception e) {
    			System.out.println("ERROR: " + e.getMessage());
    			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    			
    		}

	}
       
        @PostMapping("/registrar/{nombreTienda}")
        public ResponseEntity<?> registrarNuevaTienda(@PathVariable("nombreTienda") String nombreTienda,
        												@RequestParam("imagen") MultipartFile imagen) throws IOException{
        	
        	return new ResponseEntity<>(tiendaService.registrarNuevaTienda(nombreTienda,imagen), HttpStatus.OK);
        	
        }
    	@ResponseBody
    	@RequestMapping(value = "/imagen/{idTienda}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.IMAGE_PNG_VALUE)
    	public ResponseEntity<byte[]> getImagen(@PathVariable("idTienda") Integer tiendaId) {
    		
    	    byte[] image = tiendaService.getImagenTienda(tiendaId);
    	    return ResponseEntity.ok(image);
    	}
    	
           
        

//	@GetMapping("/obtenerListadoSucursales")
//	public ResponseEntity<?> obtenerListadoSucursales(){
//		
//		return new ResponseEntity<>(tiendaService.obtenerListadoSucursales(), HttpStatus.OK);
//		
//	}
}
