package com.cda.turnero.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.enums.EstadoTurno;
import com.cda.turnero.enums.TurnoClienteEnum;
import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.Turno;
import com.cda.turnero.model.TurnoCliente;

public interface TurnoClienteDao extends JpaRepository<TurnoCliente, Integer> {

	Integer countByTurnoAndEstadoNotLike(Turno turno, TurnoClienteEnum cancelado);

	Integer countByTurnoAndEstadoNotLike(Turno turno, EstadoTurno estado);

	TurnoCliente getTurnoClienteByTurnoId(Integer turnoClienteId);
	
	
	Optional<TurnoCliente> findByClienteAndTurnoAndEstadoNotLike(Cliente cliente, Turno turno, TurnoClienteEnum estado );
	
	//SE USA EN IMPL
	TurnoCliente findTurnoClienteByIdTurnoAndIdClienteAndEstadoNotLikeAnd
	(Integer turnoId, Integer clienteId, TurnoClienteEnum primerEstado,TurnoClienteEnum segundoEstado);
	
	List<TurnoCliente> getTurnosByClienteAndEstadoNotLikeAndEstadoNotLike
	(Cliente cliente, TurnoClienteEnum primerEstado, TurnoClienteEnum segundoEstado);
	

}
