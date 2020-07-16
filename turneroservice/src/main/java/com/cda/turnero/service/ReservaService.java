package com.cda.turnero.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.EstadoReservaDao;
import com.cda.turnero.dao.ReservaDao;
import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.EstadoReserva;
import com.cda.turnero.model.Reserva;
import com.google.gson.JsonObject;

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
	
	public Reserva getReservaByClienteAndEstadoLike(Cliente cliente, EstadoReserva estado) {
		Optional<Reserva> reserva = reservaDaoImpl.findByClienteLikeAndFechaSalidaIsNullAndEstadoLike(cliente,estado);
		if(reserva.isEmpty()) {
			return null;
		}else return reserva.get();
	}
	public boolean crearReserva(JsonObject datosReserva, Integer idCliente)	
	{
		
		if(usuarioService.existeReservaDeCliente(idCliente)) {
			return false;
		}else {
			Cliente cliente = usuarioService.getClienteById(idCliente);	
			Integer idSucursal = datosReserva.get("sucursalId").getAsInt();
			Integer idEspecialidad = datosReserva.get("especialidadId").getAsInt();
			String descSintomas = datosReserva.get("descSintomas").getAsString();
			
			Reserva reserva = new Reserva();
			reserva.setSucursal(sucursalService.getSucursalById(idSucursal));
			reserva.setEspecialidad(especialidadService.getEspecialidadById(idEspecialidad));
			reserva.setDescSintomas(descSintomas);		
			reserva.setCliente(cliente);
			reservaDaoImpl.save(reserva);
			return true;
		}
	}
	public boolean cancelarReserva(Integer idReserva) {
		Reserva reserva = reservaDaoImpl.findById(idReserva).get();		
		reserva.setEstado(estadoReservaService.getEstadoCancelado());
		reservaDaoImpl.save(reserva);
		return true;
	}
}
