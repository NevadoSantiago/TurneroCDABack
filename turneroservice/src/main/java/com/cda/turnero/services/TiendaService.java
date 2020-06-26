package com.cda.turnero.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cda.turnero.dao.LocalidadDao;
import com.cda.turnero.dao.PaisDao;
import com.cda.turnero.dao.ProvinciaDao;
import com.cda.turnero.dao.SucursalDao;
import com.cda.turnero.dao.TiendaDao;
import com.cda.turnero.model.Pais;
import com.cda.turnero.model.Tienda;


@Service
public class TiendaService {

	@Autowired
	TiendaDao tiendaDao;
	
	@Autowired
	PaisDao paisDao;
	
	@Autowired
	ProvinciaDao provinciaDao;
	
	@Autowired
	LocalidadDao localidadDao;
	
	@Autowired
	SucursalDao sucursalDao;
	
	// TODO: Yaco
	public List<Tienda> getAllTienda(){
		List<Tienda> result = tiendaDao.findAll();
		return result;
	}
	
	public Optional<Tienda> findTiendaById(Integer tiendaId) {
		return tiendaDao.findById(tiendaId);
		
			
	}

	

	public Object getPronviciaByPaisAndTienda(Integer tiendaId) {
		UbicacionService ubicacionService = new UbicacionService();
		return ubicacionService.getProvinciaByTienda(tiendaId);
	}

	public Tienda registrarNuevaTienda(String nombreTienda, MultipartFile imagen) throws IOException {
		byte[] bytes = imagen.getBytes();
		Tienda tienda = new Tienda();
		tienda.setNombre(nombreTienda);
		tienda.setImagenMuestra(bytes);
		tienda.setHabilitada(true);
		return tiendaDao.save(tienda);
		
	}

	public byte[] getImagenTienda(Integer tiendaId) {
		Tienda tienda = tiendaDao.findById(tiendaId).get();
		return tienda.getImagenMuestra();
	}

	

}