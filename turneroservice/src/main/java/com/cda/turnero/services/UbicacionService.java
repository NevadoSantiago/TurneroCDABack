package com.cda.turnero.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.LocalidadDao;
import com.cda.turnero.dao.PaisDao;
import com.cda.turnero.dao.ProvinciaDao;
import com.cda.turnero.dao.TiendaDao;
import com.cda.turnero.dao.TiendaPaisDao;
import com.cda.turnero.model.Localidad;
import com.cda.turnero.model.Pais;
import com.cda.turnero.model.Provincia;
import com.cda.turnero.model.Sucursal;
import com.cda.turnero.model.Tienda;
import com.cda.turnero.model.TiendaPais;

@Service
public class UbicacionService {

	@Autowired
	PaisDao paisDao;

	@Autowired
	TiendaPaisDao tiendapais;
	@Autowired
	TiendaDao tiendaDao;

	@Autowired
	ProvinciaDao provinciaDao;

	@Autowired
	LocalidadDao localidadDao;

	@Autowired
	TiendaService serviceti;
	@Autowired
	SucursalService sucursalService;



	// TODO: Lore
	public List<Pais> getAllPaisByTienda(Integer tiendaId) {
		List<TiendaPais> TiendaPais = tiendapais.findAllByTienda(tiendaId);
		List<Pais> result = new ArrayList<Pais>();

		for (TiendaPais tiendaPais : TiendaPais) {
			result.add(tiendaPais.getPais());
		}

		return result;


	}

	// TODO: Flor
	public List<Localidad> dameLasLocalidades(Integer tiendaId) {
		return localidadDao.findAll();
	}

	public List<Localidad> getLocalidadesByTienda(Integer tiendaId) {
		List<Sucursal> sucursales = sucursalService.getSucursalesByTienda(tiendaId);
		List<Localidad> result = new ArrayList<Localidad>();
		for (Sucursal sucursal : sucursales) {
			result.add(sucursal.getLocalidad());
		}
		return result;
	}

	public List<Provincia> getProvinciaByTienda(Integer tiendaId) {
		List<Localidad> localidades = getLocalidadesByTienda(tiendaId);
		List<Provincia> result = new ArrayList<Provincia>();
		for (Localidad localidad : localidades) {
			result.add(localidad.getProvincia());
		}
		return result;
	}

	public List<Pais> getPaisByTienda(Integer tiendaId){
		List<Provincia> provincias = getProvinciaByTienda(tiendaId);
		List<Pais> paises = new ArrayList<Pais>();
		List<Pais> paisesFiltrados = new ArrayList<Pais>();
		for (Provincia provincia : provincias) {
			paises.add(provincia.getPais());
		}
		for (Pais pais : paises) {
			if(!paisesFiltrados.contains(pais)){paisesFiltrados.add(pais);}
		}
		return paisesFiltrados;
	}

	public List<Provincia> getProvinciaByTiendaYPais(Integer tiendaId, Integer paisId) {
		List<Provincia> provincias = getProvinciaByTienda(tiendaId);
		List<Provincia> result = new ArrayList<Provincia>();
		for(Provincia provincia : provincias) {
			if(provincia.getPais().getPaisId() == paisId) {
				result.add(provincia);
			}
		}
		return result;
	}

}
