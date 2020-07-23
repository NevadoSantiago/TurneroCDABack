package com.cda.turnero.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.EstadoReservaDao;
import com.cda.turnero.dao.ReservaDao;
import com.cda.turnero.dto.DetalleReservaDto;
import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.EstadoReserva;
import com.cda.turnero.model.Reserva;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class ReservaService {
	
	@Autowired
	ReservaDao reservaDaoImpl;
	
	@Autowired
	EstadoReservaService estadoReservaService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	SucursalService sucursalService;
	
	@Autowired
	EspecialidadService especialidadService;
	
	@Autowired
	CodigoQrService codigoQrService;
	
	public Reserva getReservaByClienteAndEstadoLike(Cliente cliente, EstadoReserva estado) {
		Optional<Reserva> reserva = reservaDaoImpl.findByClienteLikeAndFechaSalidaIsNullAndEstadoLike(cliente,estado);
		if(reserva.isEmpty()) {
			return null;
		}else return reserva.get();
	}
	public DetalleReservaDto crearReserva(String datosReserva, Integer id)	
	{
		
		if(usuarioService.existeReservaDeCliente(id)) {
			return null;
		}else {
			
			JsonElement json = new JsonParser().parse(datosReserva);
			JsonObject jobject = json.getAsJsonObject();
			
			Cliente cliente = usuarioService.getClienteById(id);	
			Integer idSucursal = jobject.get("sucursalId").getAsInt();
			Integer idEspecialidad = jobject.get("especialidadId").getAsInt();
			String descSintomas = jobject.get("descSintomas").getAsString();
			String longitud = jobject.get("latitud").getAsString();
			String latitud = jobject.get("longitud").getAsString();
			
			Reserva reserva = new Reserva();
			reserva.setSucursal(sucursalService.getSucursalById(idSucursal));
			reserva.setEspecialidad(especialidadService.getEspecialidadById(idEspecialidad));
			reserva.setDescSintomas(descSintomas);		
			reserva.setEstado(estadoReservaService.getEstadoProgramado());
			reserva.setCliente(cliente);
			reserva.setCodigoQr(datosReserva);
			reserva.setLatitud(latitud);
			reserva.setLongitud(longitud);
			
			reserva = reservaDaoImpl.save(reserva);
			
			return new DetalleReservaDto(reserva);
		}
	}
	public boolean crearReservaEnEntrada(String datosReserva) {
			
			JsonElement json = new JsonParser().parse(datosReserva);
			JsonObject jobject = json.getAsJsonObject();
			
			Integer idEspecialidad = jobject.get("especialidadId").getAsInt();
			Integer idSucursal = jobject.get("sucursalId").getAsInt();
			String descSintomas = jobject.get("descSintomas").getAsString();
//			String nombre = jobject.get("nombre").getAsString();
//			String apellido = jobject.get("apellido").getAsString();
			
			Reserva reserva = new Reserva();
			reserva.setSucursal(sucursalService.getSucursalById(idSucursal));
			reserva.setEspecialidad(especialidadService.getEspecialidadById(idEspecialidad));
			reserva.setDescSintomas(descSintomas);		
			reserva.setEstado(estadoReservaService.getEstadoProgramado());
			reserva.setCodigoQr(datosReserva);			
			reserva = reservaDaoImpl.save(reserva);
			return true;
		
	}
	public boolean cancelarReserva(Integer idReserva) {
		Reserva reserva = reservaDaoImpl.findById(idReserva).get();		
		reserva.setEstado(estadoReservaService.getEstadoCancelado());
		reservaDaoImpl.save(reserva);
		return true;
	}
	public byte[] getCodigoQrByReserva(Integer reservaId) {
		Optional<Reserva> reserva = reservaDaoImpl.findById(reservaId);
		if(reserva.isEmpty()) return null;
		else {			
			String codigoQr = reserva.get().getCodigoQr();
			return codigoQrService.showQRCodeImage(codigoQr);
		}		
	}
}
