package com.cda.turnero.services;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.CajaDao;
import com.cda.turnero.dao.SucursalDao;
import com.cda.turnero.dao.TipoCajaDao;
import com.cda.turnero.dao.TurnoCajaDao;
import com.cda.turnero.dao.TurnoDao;
import com.cda.turnero.model.Caja;
import com.cda.turnero.model.CodigoQr;
import com.cda.turnero.model.TipoCaja;
import com.cda.turnero.model.TurnoCaja;
import com.cda.turnero.model.TurnoCliente;

@Service
public class CajaService {

	@Autowired
	TurnoService turnoService;
	
	@Autowired
	CajaDao cajaDao;
	
	@Autowired
	TipoCajaDao tipoCajaDao;
	
	@Autowired
	SucursalDao sucursalDao;
	@Autowired
	CodigoQrService codigoQrService;
	
	@Autowired
	TurnoDao turnoDao;
	
	@Autowired
	TurnoCajaDao turnoCajaDao;
	
	// TODO: Legui
	public List<Caja> getCajas() {
		return cajaDao.findAll();
	}
	
	public Caja agregarCaja(Integer tipoCajaId, Integer sucursalId) {
		Date date = new Date();
		Caja caja = new Caja();
		caja.setFechaAlta(date);
		caja.setTipoCaja(tipoCajaDao.findById(tipoCajaId).get());
		caja.setSucursal(sucursalDao.findById(sucursalId).get());
		
		return cajaDao.save(caja);
	}
	
	
	public Caja getCajaById(Integer cajaId) {
		return cajaDao.findById(cajaId).get();
	}
	
	public Caja habilitarCaja(Integer cajaId) {

		Caja caja = cajaDao.findById(cajaId).get();
		caja.setActiva(true);
		
		return cajaDao.save(caja);
		
	}

	public Caja deshabilitarCaja(Integer cajaId) {

		Caja caja = cajaDao.findById(cajaId).get();
		caja.setActiva(false);
		
		return cajaDao.save(caja);
		
	}

	// list
	public List<Caja> getCajasByTipoCaja(Integer tipoCajaId) {
		return cajaDao.getCajasByTipoCaja(tipoCajaId);
	}
	
	// list
	public List<Caja> getCajasBySucursal(Integer sucursalId) {
		return cajaDao.getCajasBySucursal(sucursalId);
	}
	
	public TipoCaja crearTipoDeCaja(String detalle) {
		Date date = new Date();
		
		TipoCaja tipoCaja = new TipoCaja();
		tipoCaja.setDetalle(detalle);
		tipoCaja.setFechaAlta(date);
		
		return tipoCajaDao.save(tipoCaja);
	}
	
	public List<Caja> getCajasByEstado(boolean estado){
		return cajaDao.getCajasByEstado(estado);
	}

	public String finalizarTransaccion() {
		
		return "Finaliza una transaccion por caja";
	}
	
	public String iniciarTransaccion() {
		
		return "Inicia una transaccion por caja";
	}

	public Boolean verificarCodigoQr(int codigoQr_id) {
		TurnoCliente turno = turnoService.getTurnoClienteByQr(codigoQr_id);
		Date date = new Date();   
		Calendar calendar = GregorianCalendar.getInstance(); 
		calendar.setTime(date);   
		return true;
		/*
		if(turno.getProgramado() == date) {
			return true;
			if(turno.getHorario() == String.valueOf(calendar.get(Calendar.HOUR_OF_DAY))) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}*/
	}
	
	
	public String asignarCaja(Integer codigoQr_id, Integer tipoCaja_id, Integer sucursal_id) {
		if(verificarCodigoQr(codigoQr_id)) {			
			Long personasCaja = 0L;
			Caja cajaAsignada = null;
			for (Caja caja : cajaDao.getCajasBySucursal(sucursal_id)) {
				Long cantidad = turnoDao.countTurnoCajaInCaja(caja.getCajaId());
				if (cajaAsignada == null || cantidad < personasCaja) {
					personasCaja = cantidad;	
					cajaAsignada = caja;
				}
			}
			
			TurnoCaja turnoCaja = new TurnoCaja();
			turnoCaja.setCaja(cajaAsignada);
			turnoCaja.setTurno(turnoDao.getTurnoClienteByQr(codigoQr_id));
			turnoCaja.setFechaAlta(new Date());
			turnoCajaDao.save(turnoCaja);
			
			return "Te asignamos la caja " + cajaAsignada.getCajaId();
		}
		return "El turno tiene una fecha y horario diferentes al horario actual";
	}

	public CodigoQr deshabilitarQr(Integer idQr) {
		CodigoQr codigoQr = codigoQrService.getById(idQr);
		codigoQr.setEnable(false);
		codigoQrService.updateQr(codigoQr);
		return codigoQr;
	}

}