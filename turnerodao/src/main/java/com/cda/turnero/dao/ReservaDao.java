package com.cda.turnero.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cda.turnero.dto.DetalleReservaAdminDto;
import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.EstadoReserva;
import com.cda.turnero.model.Reserva;

public interface ReservaDao extends JpaRepository<Reserva, Integer> {

	Optional<Reserva> findByClienteLikeAndFechaSalidaIsNullAndEstadoLike(Cliente cliente, EstadoReserva estado);
	
	Long getCantidadReservasPorSucursal(Integer idSucursal);

	List<DetalleReservaAdminDto> getListadoReservasPorSucursal(Integer sucursalId);
	
}
