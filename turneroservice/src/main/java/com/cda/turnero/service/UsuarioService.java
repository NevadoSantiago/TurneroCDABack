package com.cda.turnero.service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.security.auth.login.CredentialException;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.cda.turnero.dao.ClienteDao;
import com.cda.turnero.dao.EmpleadoDao;
import com.cda.turnero.dao.TipoUsuarioDao;
import com.cda.turnero.dto.ClienteLogueadoDto;
import com.cda.turnero.dto.UsuarioLogueadoDto;
import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.Empleado;
import com.cda.turnero.model.EstadoReserva;
import com.cda.turnero.model.Reserva;
import com.cda.turnero.model.Sucursal;
import com.cda.turnero.model.TipoUsuario;
import com.cda.turnero.model.Usuario;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	ClienteDao clienteDaoImpl;
	@Autowired
	ReservaService reservaService;
	@Autowired
	EmpleadoDao empleadoDaoImpl;
	@Autowired
	EstadoReservaService estadoReservaService;
	@Autowired
	SucursalService sucursalService;
	@Autowired
	TipoUsuarioDao tipoUsuarioDaoImpl;
	
	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		Empleado empleado = empleadoDaoImpl.getEmpleadoByUsername(s);
		String usuario = empleado.getUsuario().getUsuario();
		String pass = empleado.getUsuario().getPassword();
		List<TipoUsuario> listRol =	new ArrayList<>();
		listRol.add(empleado.getUsuario().getTipoUsuario());
		return new User(usuario,pass,listRol);
	}
	
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
	public UsuarioLogueadoDto ingresoUsuario(String autenticacion) throws CredentialException{
			JsonElement json = new JsonParser().parse(autenticacion);
			JsonObject jobject = json.getAsJsonObject();
			
			String usuario = jobject.get("usuario").getAsString();
			String contrasena = jobject.get("contrasena").getAsString();
			return logIn(usuario,contrasena);		
	}
	private Empleado getUsuarioByUsuarioAndContrasena(String usuario, String contrasena) {
		return empleadoDaoImpl.getEmpleadoByUsuarioYContrasena(usuario, contrasena);
		
	}
	public UserDetails getUserDetails (String autenticacion) {
		JsonElement json = new JsonParser().parse(autenticacion);
		JsonObject jobject = json.getAsJsonObject();
		
		String usuario = jobject.get("usuario").getAsString();
		String pass = jobject.get("contrasena").getAsString();
		Empleado empleado = getUsuarioByUsuarioAndContrasena(usuario, pass);
		List<TipoUsuario> listRol = new ArrayList<>();
		listRol.add(empleado.getUsuario().getTipoUsuario());
		return new User(usuario,pass,listRol);
	}
	private UsuarioLogueadoDto logIn(String usuario, String contrasena) throws CredentialException {
		Empleado empleado = getUsuarioByUsuarioAndContrasena(usuario, contrasena);
		
		if(empleado == null) throw new CredentialException() ;
		
		String tipoUsuario = empleado.getUsuario().getTipoUsuario().getDetalle();
		String nombre = empleado.getNombre();
		String apellido = empleado.getApellido();
		Integer personaId = empleado.getPersonaId();
		Sucursal sucursal =empleado.getSucursal();
			
		UsuarioLogueadoDto uLogueadoDto = new UsuarioLogueadoDto(usuario, tipoUsuario,personaId,sucursal, nombre, apellido);
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
	public boolean eliminarUsuario(Integer usuarioId) {
		
		Optional<Empleado> empleado = empleadoDaoImpl.findById(usuarioId);
		if(empleado.isEmpty()) {
			return false;
		}else {
			Date dia = java.sql.Date.valueOf(LocalDate.now().toString("yyyy-MM-dd"));
			Empleado emp = empleado.get();
			emp.setFechaBaja(dia);
			empleadoDaoImpl.save(emp);
			return true;
		}	
	}
	public List<TipoUsuario> getRoles() {
		return tipoUsuarioDaoImpl.findAll();
	}
	public boolean editarUsuario(String datos) {
		
		JsonElement json = new JsonParser().parse(datos);
		JsonObject jobject = json.getAsJsonObject();
		
		Integer idEmpleado = jobject.get("idEmpleado").getAsInt();
		String nombre = jobject.get("nombre").getAsString();
		String apellido = jobject.get("apellido").getAsString();
		String mail = jobject.get("mail").getAsString();
		Integer idRol = jobject.get("rol").getAsInt();
		
		Optional<Empleado> empleado = empleadoDaoImpl.findById(idEmpleado);
		if(empleado.isEmpty()) {
			return false;
		}else {
			Empleado emp = empleado.get();
			emp.setNombre(nombre);
			emp.setApellido(apellido);
			emp.setMail(mail);
			Usuario usuario = emp.getUsuario();
			usuario.setTipoUsuario(tipoUsuarioDaoImpl.findById(idRol).get());
			emp.setUsuario(usuario);
			empleadoDaoImpl.save(emp);
			return true;
		}
		
		
	}

}
