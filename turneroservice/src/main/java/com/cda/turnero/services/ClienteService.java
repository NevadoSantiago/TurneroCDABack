package com.cda.turnero.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.ClienteDao;
import com.cda.turnero.model.Cliente;

@Service
public class ClienteService {

	@Autowired
	ClienteDao clienteDao;
	
	public Cliente getClienteById(Integer id) {
		return clienteDao.findById(id).get();
	}
	public Integer ingresoCliente(String mail) {
		Optional<Cliente> cliente = clienteDao.findByMailLike(mail);
		if(!cliente.isPresent()) {
			return crearCliente(mail).getClienteId();
		}else {
			return cliente.get().getClienteId();
		}
	}
	private Cliente crearCliente(String mail) {
		Cliente cliente = new Cliente(mail);
		return clienteDao.save(cliente);
	}
}
