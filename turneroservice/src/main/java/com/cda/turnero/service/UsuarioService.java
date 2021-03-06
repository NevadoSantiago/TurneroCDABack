package com.cda.turnero.service;

import java.text.SimpleDateFormat;
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
import com.cda.turnero.dao.UsuarioDao;
import com.cda.turnero.dto.ClienteLogueadoDto;
import com.cda.turnero.dto.EmpleadoNoRegistradoDto;
import com.cda.turnero.dto.UsuarioLogueadoDto;
import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.Empleado;
import com.cda.turnero.model.EstadoReserva;
import com.cda.turnero.model.Reserva;
import com.cda.turnero.model.Sucursal;
import com.cda.turnero.model.TipoUsuario;
import com.cda.turnero.model.Usuario;
import com.cda.turnero.token.utils.JwtTokenUtil;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	ClienteDao clienteDaoImpl;
	@Autowired
	UsuarioDao usuarioDaoImpl;
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
	@Autowired
	JwtTokenUtil jwtUtil;

	@Override
	public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
		Empleado empleado = empleadoDaoImpl.getEmpleadoByUsername(s);
		String usuario = empleado.getUsuario().getUsuario();
		String pass = empleado.getUsuario().getPassword();
		List<TipoUsuario> listRol = new ArrayList<>();
		listRol.add(empleado.getUsuario().getTipoUsuario());
		return new User(usuario, pass, listRol);
	}

	public ClienteLogueadoDto ingresoCliente(String mail) {
		Optional<Cliente> cliente = clienteDaoImpl.findByMailLike(mail);
		if (cliente.isEmpty()) {
			Cliente clienteNuevo = crearCliente(mail);

			return clienteLogueadoMapper(clienteNuevo, null);
		} else {
			Reserva reserva = reservaService.getReservaByClienteAndEstadoLike(cliente.get(),
					estadoReservaService.getEstadoProgramado());
			return clienteLogueadoMapper(cliente.get(), reserva);
		}
	}

	public UsuarioLogueadoDto ingresoUsuario(String autenticacion) throws CredentialException {
		JsonElement json = new JsonParser().parse(autenticacion);
		JsonObject jobject = json.getAsJsonObject();

		String usuario = jobject.get("usuario").getAsString();
		String contrasena = jobject.get("contrasena").getAsString();
		return logIn(usuario, contrasena);
	}

	private Empleado getUsuarioByUsuarioAndContrasena(String usuario, String contrasena) {
		return empleadoDaoImpl.getEmpleadoByUsuarioYContrasena(usuario, contrasena);

	}

	public UserDetails getUserDetailsByEmpleado(Empleado empleado) {

		List<TipoUsuario> listRol = new ArrayList<>();
		listRol.add(empleado.getUsuario().getTipoUsuario());
		String usuario = empleado.getUsuario().getUsuario();
		String pass = empleado.getUsuario().getPassword();
		return new User(usuario, pass, listRol);
	}

	private UsuarioLogueadoDto logIn(String usuario, String contrasena) throws CredentialException {
		Empleado empleado = getUsuarioByUsuarioAndContrasena(usuario, contrasena);

		if (empleado == null)
			throw new CredentialException();

		UsuarioLogueadoDto uLogueadoDto = usuarioLogueadoMapper(empleado);
		return uLogueadoDto;

	}

	private ClienteLogueadoDto clienteLogueadoMapper(Cliente cliente, Reserva reserva) {
		ClienteLogueadoDto clienteLogueado;
		if (reserva == null) {
			clienteLogueado = new ClienteLogueadoDto(cliente);
		} else {
			clienteLogueado = new ClienteLogueadoDto(cliente, reserva);
		}

		return clienteLogueado;
	}

	private UsuarioLogueadoDto usuarioLogueadoMapper(Empleado empleado) {
		UsuarioLogueadoDto uLogueado = new UsuarioLogueadoDto();
		uLogueado.setApellido(empleado.getApellido());
		uLogueado.setIdUsuario(empleado.getPersonaId());
		uLogueado.setNombre(empleado.getNombre());
		uLogueado.setSucursal(empleado.getSucursal());
		uLogueado.setTipoUsuario(empleado.getUsuario().getTipoUsuario().getDetalle());
		uLogueado.setUsuario(empleado.getUsuario().getUsuario());
		
		UserDetails userDetails = getUserDetailsByEmpleado(empleado);
		String token = jwtUtil.generateToken(userDetails);
		uLogueado.setToken(token);
		
		return uLogueado;

	}

	public Cliente getClienteById(Integer idCliente) {
		Optional<Cliente> cliente = clienteDaoImpl.findById(idCliente);
		if (cliente.isEmpty()) {
			return null;
		} else
			return cliente.get();
	}

	private Cliente crearCliente(String mail) {
		Cliente nuevoCliente = new Cliente();
		nuevoCliente.setMail(mail);
		return clienteDaoImpl.save(nuevoCliente);
	}

	public boolean existeReservaDeCliente(Integer idCliente) {
		Cliente cliente = getClienteById(idCliente);
		EstadoReserva estado = estadoReservaService.getEstadoProgramado();
		if (reservaService.getReservaByClienteAndEstadoLike(cliente, estado) == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean eliminarUsuario(Integer usuarioId) {

		Optional<Empleado> empleado = empleadoDaoImpl.findById(usuarioId);
		if (empleado.isEmpty()) {
			return false;
		} else if(empleado.get().getFechaAlta()!=null) {
			Date dia = java.sql.Date.valueOf(LocalDate.now().toString("yyyy-MM-dd"));
			Empleado emp = empleado.get();
			emp.setFechaBaja(dia);
			empleadoDaoImpl.save(emp);
			return true;
		}else {
			empleadoDaoImpl.deleteById(empleado.get().getPersonaId());
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
		Integer idRol = jobject.get("rol").getAsInt();

		Optional<Empleado> empleado = empleadoDaoImpl.findById(idEmpleado);
		if (empleado.isEmpty()) {
			return false;
		} else {
			Empleado emp = empleado.get();
			emp.setNombre(nombre);
			emp.setApellido(apellido);
			
			if(jobject.get("mail") != null) {
				emp.setMail(jobject.get("mail").getAsString());
			}
			
			Usuario usuario = emp.getUsuario();
			usuario.setTipoUsuario(tipoUsuarioDaoImpl.findById(idRol).get());
			emp.setUsuario(usuario);
			empleadoDaoImpl.save(emp);
			return true;
		}

	}

	public String crearEmpleado(String datos) {
		JsonElement json = new JsonParser().parse(datos);
		JsonObject jobject = json.getAsJsonObject();

		Integer idSucursal = jobject.get("idSucursal").getAsInt();
		String nombre = jobject.get("nombre").getAsString();
		String apellido = jobject.get("apellido").getAsString();
		Integer idRol = jobject.get("idRol").getAsInt();

		Sucursal sucursal = sucursalService.getSucursalById(idSucursal);
		TipoUsuario tipoUsuario = tipoUsuarioDaoImpl.findById(idRol).get();

		Empleado empleado = crearEmpleadoProvisorio(sucursal, nombre, apellido, tipoUsuario);
		try {
			empleado = empleadoDaoImpl.save(empleado);
			String codigoUsuario = newCodigoUsuario(empleado);
			return codigoUsuario;
		} catch (Exception e) {
			System.out.println("Error en la carga de usuario");
			return null;
		}

	}

	public UsuarioLogueadoDto crearEmpleadoFinal(String datos) throws IllegalArgumentException {
		JsonElement json = new JsonParser().parse(datos);
		JsonObject jobject = json.getAsJsonObject();

		String codigo = jobject.get("codigo").getAsString().toUpperCase();
		String usuario = jobject.get("usuario").getAsString().toUpperCase();
		String contrasena = jobject.get("contrasena").getAsString();
		String mail = jobject.get("mail").getAsString();
		String dni = jobject.get("dni").getAsString();

		existeUsuario(usuario, contrasena, mail, dni);

		Empleado empleado = getEmpleadoByCodigo(codigo);
		Date date = new Date();
		empleado.setFechaAlta(date);
		empleado.setNroDocumento(dni);
		empleado.setMail(mail);

		Usuario usuarioEmpl = empleado.getUsuario();
		usuarioEmpl.setPassword(contrasena);
		usuarioEmpl.setUsuario(usuario);

		empleado.setUsuario(usuarioEmpl);

		empleado = empleadoDaoImpl.save(empleado);
		UsuarioLogueadoDto uLogueado = usuarioLogueadoMapper(empleado);
		return uLogueado;
	}

	private void existeUsuario(String usuario, String contrasena, String mail, String dni) {

		Empleado usuarioByMailOrDni = empleadoDaoImpl.findByMailLikeOrNroDocumentoLike(mail, dni);
		Boolean existeCliente = clienteDaoImpl.existsByMailLike(mail);
		usuario = usuario.toLowerCase();
		contrasena = contrasena.toLowerCase();
		Boolean existUsername = usuarioDaoImpl.existsByUsuarioLike(usuario);

		if (usuarioByMailOrDni != null || existeCliente)
			throw new IllegalArgumentException("El Mail o Dni ya estan en uso");
		if (existUsername)
			throw new IllegalArgumentException("El usuario ya esta en eso");
		if (usuario.equals(contrasena))
			throw new IllegalArgumentException("El usuario y la contrasena deben ser distintos");

	}

	private Empleado crearEmpleadoProvisorio(Sucursal sucursal, String nombre, String apellido,
			TipoUsuario tipoUsuario) {
		Empleado empleado = new Empleado();
		Usuario usuarioProv = new Usuario();

		usuarioProv.setTipoUsuario(tipoUsuario);
		usuarioProv = usuarioDaoImpl.save(usuarioProv);

		empleado.setApellido(apellido);
		empleado.setSucursal(sucursal);
		empleado.setNombre(nombre);
		empleado.setApellido(apellido);
		empleado.setUsuario(usuarioProv);

		return empleado;

	}

	private String newCodigoUsuario(Empleado empleado) {
		String codigo = empleado.getPersonaId() + "EU" + empleado.getUsuario().getUsuarioId();
		return codigo;
	}

	public String validarCodigo(String codigo) throws IllegalArgumentException {
		Empleado empleado = getEmpleadoByCodigo(codigo);
		if (empleado == null)
			throw new IllegalArgumentException();

		String nombreApellido = empleado.getNombre() + " " + empleado.getApellido();
		return nombreApellido;
	}

	private Empleado getEmpleadoByCodigo(String codigo) {

		if (codigo.contains("EU")) {

			String[] arrayCod = codigo.split("EU");
			Integer idEmpleado = Integer.parseInt(arrayCod[0]);
			Integer idUsuario = Integer.parseInt(arrayCod[1]);
			Empleado empleado = empleadoDaoImpl.getAndValidateNombreEmpleadoByIdsCodigo(idEmpleado, idUsuario);
			return empleado;

		} else
			throw new IllegalArgumentException();
	}

	
	public UsuarioLogueadoDto logueoByToken(String token) {
		JsonElement json = new JsonParser().parse(token);
		JsonObject jobject = json.getAsJsonObject();
		token = jobject.get("token").getAsString();
		
		String usuario = jwtUtil.getUsernameFromToken(token);
		UserDetails userDetails = loadUserByUsername(usuario);
		if(jwtUtil.validateToken(token, userDetails)) {
			Empleado empleado =  empleadoDaoImpl.getEmpleadoByUsername(usuario);
			return usuarioLogueadoMapper(empleado);
		}else {
			throw new IllegalArgumentException("Token invalido");
		}
	}

	public List<EmpleadoNoRegistradoDto> getListadoEmpleadosNoRegistrados() {
		List<Empleado> empleados = empleadoDaoImpl.findByFechaAltaIsNull();
		List<EmpleadoNoRegistradoDto> empleadosNR = new ArrayList<>();
		for(Empleado e : empleados) {
			String codigo = newCodigoUsuario(e);
			EmpleadoNoRegistradoDto er = new EmpleadoNoRegistradoDto(e, codigo);
			empleadosNR.add(er);
		}
		return empleadosNR;
	}

}
