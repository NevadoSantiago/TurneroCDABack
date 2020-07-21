package com.cda.turnero.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.ClienteDao;
import com.cda.turnero.dao.EmpleadoDao;
import com.cda.turnero.dto.ClienteLogueadoDto;
import com.cda.turnero.dto.UsuarioLogueadoDto;
import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.Empleado;
import com.cda.turnero.model.EstadoReserva;
import com.cda.turnero.model.Reserva;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class UsuarioService {
	
	@Autowired
	ClienteDao clienteDaoImpl;
	@Autowired
	ReservaService reservaService;
	@Autowired
	EmpleadoDao empleadoDaoImpl;
	@Autowired
	EstadoReservaService estadoReservaService;
	
	public ClienteLogueadoDto ingresoCliente(String mail) {
		Optional<Cliente> cliente = clienteDaoImpl.findByMailLike(mail);
		if(cliente.isEmpty()) {
			Cliente clienteNuevo = crearCliente(mail);
			
			return clienteLogueadoMapper(clienteNuevo, null);
		}else {
			Reserva reserva = reservaService.getReservaByClienteAndEstadoLike(cliente.get(),estadoReservaService.getEstadoProgramado());
			return clienteLogueadoMapper(cliente.get(), reserva);
		}
	}
	public UsuarioLogueadoDto ingresoUsuario(String autenticacion){
			JsonElement json = new JsonParser().parse(autenticacion);
			JsonObject jobject = json.getAsJsonObject();
			
			String usuario = jobject.get("usuario").getAsString();
			String contrasena = jobject.get("contrasena").getAsString();
			return logIn(usuario,contrasena);		
	}
	private Empleado getUsuarioByUsuarioAndContrasena(String usuario, String contrasena) {
		return empleadoDaoImpl.getEmpleadoByUsuarioYContrasena(usuario, contrasena);
		
	}
	private UsuarioLogueadoDto logIn(String usuario, String contrasena) {
		Empleado empleado = getUsuarioByUsuarioAndContrasena(usuario, contrasena);
		
		if(empleado == null)	return null;
		
		String tipoUsuario = empleado.getUsuario().getTipoUsuario().getDetalle();
		Integer personaId = empleado.getPersonaId();
		Integer sucursalId = empleado.getSucursal().getSucursalId();
		
		UsuarioLogueadoDto uLogueadoDto = new UsuarioLogueadoDto(usuario, tipoUsuario,personaId,sucursalId);
		return uLogueadoDto;
	
		
		
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
