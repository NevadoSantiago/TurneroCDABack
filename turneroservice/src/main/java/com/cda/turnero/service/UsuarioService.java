package com.cda.turnero.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.ClienteDao;
import com.cda.turnero.dto.ClienteLogueadoDto;
import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.Reserva;

@Service
public class UsuarioService {
	
	@Autowired
	ClienteDao clienteDaoImpl;
	@Autowired
	ReservaService reservaService;
	
	public ClienteLogueadoDto ingresoUsuario(String mail) {
		Optional<Cliente> cliente = clienteDaoImpl.findByMailLike(mail);
		if(cliente.isEmpty()) {
			Cliente clienteNuevo = crearCliente(mail);
			
			return clienteLogueadoMapper(clienteNuevo, null);
		}else {
			Reserva reserva = reservaService.getReservaByCliente(cliente.get());
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

	private Cliente crearCliente(String mail) {
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setMail(mail);
		return clienteDaoImpl.save(nuevoCliente);
	}

}
