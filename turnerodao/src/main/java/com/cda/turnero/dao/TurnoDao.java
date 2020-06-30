package com.cda.turnero.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.enums.EstadoTurno;
import com.cda.turnero.model.Sucursal;
import com.cda.turnero.model.Turno;
import com.cda.turnero.model.TurnoCliente;

public interface TurnoDao extends JpaRepository<Turno, Integer>{

	List<Turno> getTurnosByMail(String mail);
	List<Turno> findTurnosBySucursalAndProgramadoBetweenAndEstadoLike(Sucursal sucursal, Date fechaDesde, Date fechaHasta, EstadoTurno estado);
	boolean existsTurnoByProgramadoBetweenAndSucursalLike(Date fechaDesde, Date fechaHasta, Sucursal sucursal);
	Turno turnoId(Integer turnoId);
	TurnoCliente getTurnoClienteByQr(int qr);
	
}
