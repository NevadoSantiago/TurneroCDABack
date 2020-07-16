package com.cda.turnero.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.ClienteDao;
import com.cda.turnero.dto.ClienteLogueadoDto;
import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.EstadoReserva;
import com.cda.turnero.model.Reserva;

@Service
public class UsuarioService {
	
	@Autowired
	ClienteDao clienteDaoImpl;
	@Autowired
	ReservaService reservaService;
	@Autowired
	EstadoReservaService estadoReservaService;
	
	public ClienteLogueadoDto ingresoUsuario(String mail) {
		Optional<Cliente> cliente = clienteDaoImpl.findByMailLike(mail);
		if(cliente.isEmpty()) {
			Cliente clienteNuevo = crearCliente(mail);
			
			return clienteLogueadoMapper(clienteNuevo, null);
		}else {
			Reserva reserva = reservaService.getReservaByClienteAndEstadoLike(cliente.get(),estadoReservaService.getEstadoProgramado());
			return clienteLogueadoMapper(cliente.get(), reserva);
		}
	}
	
	private ClienteLogueadoDto clienteLogueadoMapper(Cliente cliente, Reserva reserva) {
		ClienteLogueadoDto clienteLogueado;
			if(reserva == null) {
				clienteLogueado = new ClienteLogueadoDto(cliente);
			}else {
				clienteLogueado = new ClienteLogueadoDto(cliente,reserva);
			}
		
		
		return clienteLogueado;
	}
	public Cliente getClienteById(Integer idCliente) {
		Optional<Cliente> cliente = clienteDaoImpl.findById(idCliente);
		if(cliente.isEmpty()) {
			return null;
		}else return cliente.get();
	}

	private Cliente crearCliente(String mail) {
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setMail(mail);
		return clienteDaoImpl.save(nuevoCliente);
	}
	public boolean existeReservaDeCliente(Integer idCliente) {
		Cliente cliente = getClienteById(idCliente);
		EstadoReserva estado = estadoReservaService.getEstadoProgramado();
		if(reservaService.getReservaByClienteAndEstadoLike(cliente,estado)==null) {
			return false;
		}else {
			return true;
		}
	}

}
