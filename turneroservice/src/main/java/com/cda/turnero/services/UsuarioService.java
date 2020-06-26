package com.cda.turnero.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cda.turnero.model.Usuario;

@Service
public class UsuarioService {

	public String getUsuario() {

		return "Este metodo trae un usuario";

	}

//devuelve todos los usuarios(falta poner parametros de entrada[TIENDA-SUCURSAL])
	public List<Usuario> listaDeUsuarios() {

		System.out.println("Devuelve una lista con todos los usuarios");

		return null;
	}

	public String crearUsuario() {

		return "Este metodo crea un usuario";

	}

	public String modificarUsuario() {

		return "Este metodo modifica un usuario";

	}

	public String eliminarUsuario() {

		return "Este metodo eliminar un usuario";

	}

	public String asignarUsuarioATienda() {
		return "Este metodo asigna un usuario a una tienda";

	}

	public String asignarUsuarioASucursal() {
		return "Este metodo asigna un usuario a una sucursal";

	}

}