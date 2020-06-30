package com.cda.turnero.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cda.turnero.dao.ClienteDao;
import com.cda.turnero.dao.TipoTurnoDao;
import com.cda.turnero.dao.TurnoClienteDao;
import com.cda.turnero.dao.TurnoDao;
import com.cda.turnero.dao.TurnoDaoImpl;
import com.cda.turnero.dto.CodigoQrDto;
import com.cda.turnero.dto.DetalleTurnoDto;
import com.cda.turnero.dto.TurnoDisponibleDto;
import com.cda.turnero.dto.TurnosAsignadosDto;
import com.cda.turnero.enums.EstadoTurno;
import com.cda.turnero.enums.TurnoClienteEnum;
import com.cda.turnero.model.CaracteristicaSucursal;
import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.CodigoQr;
import com.cda.turnero.model.Sucursal;
import com.cda.turnero.model.Especialidad;
import com.cda.turnero.model.Turno;
import com.cda.turnero.model.TurnoCliente;
import com.cda.turnero.utils.DateUtil;
import com.google.gson.Gson;

@Service
public class TurnoService {

	private int RANGO_DIAS = 5;

	@Autowired
	TipoTurnoDao tipoTurnoDao;

	@Autowired
	TurnoClienteDao turnoClienteDaoImpl;

	@Autowired
	TurnoDao turnoDaoImpl;

	@Autowired
	CodigoQrService codigoQrService;

	@Autowired
	ClienteService clienteService;

	@Autowired
	SucursalService sucursalService;

	// TODO: Fabri

	public List<DetalleTurnoDto> consultarTurnosPorFecha(Date fecha, Integer idCliente) {
		Cliente cliente = clienteService.getClienteById(idCliente);
		List<TurnoCliente> tc = turnoClienteDaoImpl.getTurnosByClienteAndEstadoNotLikeAndEstadoNotLike(cliente,
				TurnoClienteEnum.CANCELADO,TurnoClienteEnum.REALIZADO);
		List<DetalleTurnoDto> lista = new ArrayList<>();
		for (TurnoCliente turnoCliente : tc) {
			String fechaTurno = DateUtil.getFormat(turnoCliente.getTurno().getProgramado());
			String fechaTurnoConsultado = DateUtil.getFormat(fecha);
			if (fechaTurnoConsultado.equals(fechaTurno)) {
				lista.add(detalleTurnoMapper(turnoCliente));
			}
		}
		return lista;
	}

	public byte[] getCodigoQrByTurnoAndCliente(Integer turnoId, Integer clienteId) {
		TurnoCliente turnoCliente = turnoClienteDaoImpl.findTurnoClienteByIdTurnoAndIdClienteAndEstadoNotLikeAnd(turnoId,
				clienteId, TurnoClienteEnum.CANCELADO,TurnoClienteEnum.REALIZADO);
		if (turnoCliente != null) {
			String json = getQRCodeByTurnoCliente(turnoCliente);
			System.out.println(json);
			return codigoQrService.showQRCodeImage(json);
		} else
			return null;

	}

	public String pruebaQr(Integer turnoId, Integer clienteId) {
		TurnoCliente turnoCliente = turnoClienteDaoImpl.findTurnoClienteByIdTurnoAndIdClienteAndEstadoNotLikeAnd(turnoId,
				clienteId, TurnoClienteEnum.CANCELADO,TurnoClienteEnum.REALIZADO);

		return getQRCodeByTurnoCliente(turnoCliente);

	}

	public String getQRCodeByTurnoCliente(TurnoCliente turnoCliente) {

		String JSON;
		if (turnoCliente == null) {
			return "null";
		} else {
			Gson gson = new Gson();
			System.out.println(turnoCliente.getCodigoQr().getCodigo());
			JSON = turnoCliente.getCodigoQr().getCodigo();
			CodigoQrDto codigoQr = gson.fromJson(JSON, CodigoQrDto.class);
			codigoQr.setCodigoQrId(turnoCliente.getCodigoQr().getCodigoQrId());
			codigoQr.setTurnoId(turnoCliente.getTurno().getTurnoId());

			return gson.toJson(codigoQr);
		}

	}
	public String cancelarTurno(Integer turnoId, Integer idCliente) {
		Turno turno = turnoDaoImpl.findById(turnoId).get();
		Cliente cliente = clienteService.getClienteById(idCliente);
		TurnoCliente tc = turnoClienteDaoImpl
				.findByClienteAndTurnoAndEstadoNotLike(cliente, turno, TurnoClienteEnum.CANCELADO).get();
		tc.setEstado(TurnoClienteEnum.CANCELADO);
		turno.setEstado(EstadoTurno.DISPONIBLE);
		turnoClienteDaoImpl.save(tc);
		turnoDaoImpl.save(turno);
		return "Se cancelo el turno";
	}

	private boolean consultarSiElTurnoNoEstaAsignadoAlCliente(Cliente cliente, Turno turno) {
		if (!turnoClienteDaoImpl.findByClienteAndTurnoAndEstadoNotLike(cliente, turno, TurnoClienteEnum.CANCELADO)
				.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public TurnoCliente asignarTurno(Integer idCliente, Integer idTurno) throws IllegalArgumentException {
		Cliente cliente = clienteService.getClienteById(idCliente);
		Turno turno = turnoDaoImpl.findById(idTurno).get();
		if (consultarSiElTurnoNoEstaAsignadoAlCliente(cliente, turno)) {
			if (isTurnoDisponible(idTurno)) {
				CodigoQr cqr = new CodigoQr();
				TurnoCliente tc = new TurnoCliente();
				tc.setCliente(cliente);
				tc.setTurno(turno);
				tc.setEstado(TurnoClienteEnum.SIN_CONFIRMAR);
				cqr = codigoQrMapper(tc);
				tc.setCodigoQr(cqr);
				tc = turnoClienteDaoImpl.save(tc);
				// UNA VEZ ASIGNADO EL TURNO VERIFICA SI FUE EL ULTIMO DISPONIBLE Y CAMBIA
				// ESTADO
				if (!isTurnoDisponible(idTurno)) {
					cambiarEstadoTurno(idTurno, EstadoTurno.COMPLETO);
				}

				return tc;
			} else {
				cambiarEstadoTurno(idTurno, EstadoTurno.COMPLETO);
				return null;
			}
		} else {
			throw new IllegalArgumentException();
		}
	}

	public DetalleTurnoDto detallarTurno(Integer idTurno, Integer idCliente) {
		Cliente cliente = clienteService.getClienteById(idCliente);
		Turno turno = turnoDaoImpl.findById(idTurno).get();
		TurnoCliente tc = turnoClienteDaoImpl
				.findByClienteAndTurnoAndEstadoNotLike(cliente, turno, TurnoClienteEnum.CANCELADO).get();

		return detalleTurnoMapper(tc);
	}

	private DetalleTurnoDto detalleTurnoMapper(TurnoCliente tc) {
		DetalleTurnoDto dt = new DetalleTurnoDto();
		Turno turno = tc.getTurno();
		Sucursal sucursal = turno.getSucursal();
		Especialidad tipoTurno = turno.getTipoTurno();

		dt.setTiendaId(sucursal.getTienda().getTiendaId());
		dt.setCodigoSeguimiento("Por ahora vacio");
		dt.setEstado(tc.getEstado());
		dt.setIdTurno(tc.getTurno().getTurnoId());
		dt.setDetalleDelTurno(tipoTurno.getDetalle());
		dt.setDuracionDelTurno(tipoTurno.getDuracion());
		String fechaProgramada = DateUtil.getFormat(turno.getProgramado());
		dt.setFechaProgramado(fechaProgramada);
		dt.setHorario(DateUtil.getFormatHour(turno.getProgramado()));
		dt.setNombreTienda(sucursal.getTienda().getNombre());
		dt.setSucursal(sucursal.getNombre());

		return dt;
	}

	public TurnoCliente getTurnoClienteByQr(int codigoQr_id) {
		return turnoDaoImpl.getTurnoClienteByQr(codigoQr_id);
	}

	public List<TurnoDisponibleDto> getTurnosBySucursalYFechaDesdeYHasta(Integer sucursalId, Date fechaDesde) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fechaDesde);
		calendar.add(Calendar.DAY_OF_YEAR, RANGO_DIAS);
		// A PARTIR DE LA FECHA DESDE OBTENEMOS FECHA HASTA SUMANDO RANGO_DIAS
		Date fechaHasta = calendar.getTime();
		Sucursal sucursal = sucursalService.findSucursalById(sucursalId);

		List<Turno> turnosDisponibles = turnoDaoImpl.findTurnosBySucursalAndProgramadoBetweenAndEstadoLike(sucursal,
				fechaDesde, fechaHasta, EstadoTurno.DISPONIBLE);

		return turnoDisponiblesMapper(turnosDisponibles);
	}

	private CodigoQr codigoQrMapper(TurnoCliente turnoCliente) {
		CodigoQrDto cqr = new CodigoQrDto();
		cqr.setClienteId(turnoCliente.getCliente().getClienteId());
		cqr.setProgramado(turnoCliente.getTurno().getProgramado());
		cqr.setSeguimiento(turnoCliente.getCodigoSeguimiento());
		cqr.setSucursalId(turnoCliente.getTurno().getSucursal().getSucursalId());
		cqr.setTipoTurnoId(turnoCliente.getTurno().getTipoTurno().getTipoTurnoId());
		cqr.setTurnoId(turnoCliente.getTurno().getTurnoId());

		Gson gson = new Gson();
		String codigoQr = gson.toJson(cqr);

		return codigoQrService.generarCodigoQr(codigoQr);
	}

	private List<TurnoDisponibleDto> turnoDisponiblesMapper(List<Turno> turnosDisponibles) {
		List<TurnoDisponibleDto> turnosDisponiblesDto = new ArrayList<TurnoDisponibleDto>();
		for (Turno turno : turnosDisponibles) {
			TurnoDisponibleDto turnoDisponible = new TurnoDisponibleDto();
			turnoDisponible.setFecha(DateUtil.getFormat(turno.getProgramado()));
			turnoDisponible.setTurnoId(turno.getTurnoId());
			turnoDisponible.setHorario(DateUtil.getFormatHour(turno.getProgramado()));
			turnoDisponible.setSucursal(turno.getSucursal().getNombre());
			turnosDisponiblesDto.add(turnoDisponible);
		}
		return turnosDisponiblesDto;

	}

	public String generateTurnos(Integer sucursalId, String fechaDesdeStr, Integer cantidadDias) {
		Date fechaDesde = DateUtil.getDateFromString(fechaDesdeStr);
		Sucursal sucursal = sucursalService.findSucursalById(sucursalId);
		if (!existeTurnoEntre(fechaDesde, cantidadDias, sucursal)) {
			int horaApertura = DateUtil.getHourByDate(sucursal.getCaracteristica().getApertura());
			int horaCierre = DateUtil.getHourByDate(sucursal.getCaracteristica().getCierre());
			Calendar calFrom = new GregorianCalendar();

			calFrom.setTime(fechaDesde);
			// TODO: ver como repartir los tipos de turnos
			Especialidad tipoTurno = tipoTurnoDao.findById(1).get();
			for (int dia = 0; dia < cantidadDias; dia++) {
				int hora = horaApertura;
				calFrom.set(Calendar.HOUR_OF_DAY, horaApertura);
				for (; hora < horaCierre; hora++) {
					Turno turno = new Turno();
					turno.setEstado(EstadoTurno.DISPONIBLE);
					turno.setFechaAlta(new Date());
					turno.setProgramado(calFrom.getTime());
					turno.setSucursal(sucursal);
					turno.setTipoTurno(tipoTurno);
					turnoDaoImpl.save(turno);
					calFrom.add(Calendar.HOUR_OF_DAY, 1);
				}
				calFrom.add(Calendar.DATE, 1);

			}

			return "Turnos creados correctamente";
		} else {
			return "Ya existen turnos en ese rango";
		}
	}

	private Boolean existeTurnoEntre(Date fechaDesde, int cantidadDias, Sucursal sucursal) {
		Date fechaHasta = DateUtil.sumarDiaAFecha(fechaDesde, cantidadDias);
		return turnoDaoImpl.existsTurnoByProgramadoBetweenAndSucursalLike(fechaDesde, fechaHasta, sucursal);
	}

	private Boolean isTurnoDisponible(Integer turnoId) {
		Turno turno = turnoDaoImpl.findById(turnoId).get();
		Integer cantidadTurnosReservado = turnoClienteDaoImpl.countByTurnoAndEstadoNotLike(turno,
				TurnoClienteEnum.CANCELADO);
		CaracteristicaSucursal caracteristicaSucursal = turno.getSucursal().getCaracteristica();
		Integer capacidadSucursal = caracteristicaSucursal.getCapacidad();
		if (cantidadTurnosReservado < capacidadSucursal) {
			return true;
		}
		return false;
	}

	private void cambiarEstadoTurno(Integer idTurno, EstadoTurno estado) {
		Turno turno = turnoDaoImpl.findById(idTurno).get();
		turno.setEstado(estado);
		turnoDaoImpl.save(turno);
	}

	public List<DetalleTurnoDto> getTurnosByCliente(Integer clienteId) {
		Cliente cliente = clienteService.getClienteById(clienteId);

		List<TurnoCliente> tc = turnoClienteDaoImpl.getTurnosByClienteAndEstadoNotLikeAndEstadoNotLike(cliente,
				TurnoClienteEnum.CANCELADO,TurnoClienteEnum.REALIZADO);
		List<DetalleTurnoDto> dt = new ArrayList<DetalleTurnoDto>();
		for (TurnoCliente turno : tc) {

			dt.add(detalleTurnoMapper(turno));
		}
		return dt;
	}
}
