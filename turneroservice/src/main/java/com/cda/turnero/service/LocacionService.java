package com.cda.turnero.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.model.Localidad;
import com.cda.turnero.model.Provincia;
import com.cda.turnero.dao.LocalidadDao;
import com.cda.turnero.dao.ProvinciaDao;
@Service
public class LocacionService {

	@Autowired
	ProvinciaDao provinciaDao;
	@Autowired
	LocalidadDao localidadDao;
	
	public List<Provincia>getAllPronvincias(){
		return provinciaDao.findAll();
	}
	public List<Localidad>getLocalidadByProvinciaId(Integer provinciaId){
		Provincia provincia = provinciaDao.findById(provinciaId).get();
		return localidadDao.findByProvinciaLike(provincia);
	}
}
