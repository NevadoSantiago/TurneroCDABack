package com.cda.turnero.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.LocalidadDao;
import com.cda.turnero.dao.SucursalDao;
import com.cda.turnero.dao.SucursalTipoTurnoDao;
import com.cda.turnero.dao.TiendaDao;
import com.cda.turnero.model.CaracteristicaSucursal;

import com.cda.turnero.model.Localidad;

import com.cda.turnero.model.Sucursal;
import com.cda.turnero.model.Clinica;
import com.cda.turnero.model.Especialidad;

@Service
public class SucursalService {
	
	@Autowired
	SucursalDao sucursalDaoImp;
	
	@Autowired
	TiendaDao tiendaDao;

    @Autowired
	SucursalTipoTurnoDao sucTipoTurnodao;
    
    @Autowired
    LocalidadDao localidadDao;

	public String listadoFechasDisponibles() {

		return "Devuelve un listado de fechas disponibles";
	}

	public String listadoHorariosDisponibles() {

		return "Devuelve un listado de horarios disponibles";
	}

	public Date setHorariosAperturaCierre() {
		System.out.println("setHorariosAperturaCierre");
		return null;
	}

	public String listadoTipoCajaDisponible() {

		return "Devuelve un listado de tipo de cajas que hay disponibles";
	}

	public Sucursal findSucursalById(Integer sucursalId) {
		Sucursal sucursal = sucursalDaoImp.findById(sucursalId).get();
			return sucursal;
		
	}
	public CaracteristicaSucursal getCaracteristicaSucursalBySucursal(Integer IDsucursal) {
		Sucursal sucursal = findSucursalById(IDsucursal);
		return sucursal.getCaracteristica();	
	}
	
	public List<Especialidad> getAllTipoTurnoBySucursal(Integer sucursalId)  {
		Sucursal sucursal = findSucursalById(sucursalId);
		List<Especialidad> tipoTurno = new ArrayList<Especialidad>();
		sucTipoTurnodao.findAllBySucursal(sucursal).forEach( rel -> {
			tipoTurno.add(rel.getTipoTurno());
		});
		return tipoTurno;
	}
	
	public List<Sucursal> getSucursalesByTienda(Integer id) {
		Clinica tienda = tiendaDao.findById(id).get();
		return sucursalDaoImp.findSucursalesByClinica(tienda);
	}
	
	public List<Sucursal> getSucursalesByTiendaAndLocalidad(Integer tiendaId, Integer localidadId) {

		Clinica clinica = tiendaDao.findById(tiendaId).get();
		Localidad localidad = localidadDao.findById(localidadId).get();

		return sucursalDaoImp.findSucursalesByClinicaAndLocalidad(clinica, localidad);

	}
	
}