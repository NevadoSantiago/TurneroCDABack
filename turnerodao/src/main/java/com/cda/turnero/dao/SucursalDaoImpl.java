package com.cda.turnero.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import com.cda.turnero.dto.DetalleSucursalDto;
import com.cda.turnero.model.EspecialidadSucursal;
import com.cda.turnero.model.Reserva;
import com.cda.turnero.model.Sucursal;

@Repository
public class SucursalDaoImpl {

	@PersistenceContext	
	private EntityManager entityManager;

	public List<DetalleSucursalDto> getSucursalesByReservas(Integer idEspecialidad) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DetalleSucursalDto> query = criteriaBuilder.createQuery(DetalleSucursalDto.class);
		Root<Reserva> reserva = query.from(Reserva.class);
		Join sucursal  = reserva.join("sucursal");
		
		Predicate fechaSalidaNullo = criteriaBuilder.isNull(reserva.get("fechaSalida"));
		Predicate especialidad = criteriaBuilder.equal(reserva.get("especialidad").get("especialidadId"),idEspecialidad);
		
		query.where(criteriaBuilder.and(fechaSalidaNullo, especialidad));
		query.multiselect(sucursal.get("sucursalId"), sucursal.get("nombre"), sucursal.get("direccion"), criteriaBuilder.count(sucursal.get("sucursalId")),sucursal.join("configuracion").get("cordLongitud"),sucursal.join("configuracion").get("cordLatitud"));
		query.groupBy(sucursal.get("sucursalId"));
		query.orderBy(criteriaBuilder.asc(criteriaBuilder.count(sucursal.get("sucursalId"))));
		TypedQuery<DetalleSucursalDto> typedQuery = entityManager.createQuery(query);
		List<DetalleSucursalDto> resultList = typedQuery.getResultList();
		List<DetalleSucursalDto> resultado = getSucursalesSinReservas(idEspecialidad);
		resultado.addAll(resultList);
		return resultado;
	}
	private List<DetalleSucursalDto> getSucursalesSinReservas(Integer idEspecialidad){
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();		
		CriteriaQuery<DetalleSucursalDto> query = criteriaBuilder.createQuery(DetalleSucursalDto.class);
		
		Subquery<Reserva> reservaSubquery = query.subquery(Reserva.class);
		Subquery<EspecialidadSucursal> especialidadSucursalSubQuery = query.subquery(EspecialidadSucursal.class);
		
		Root<Reserva> reserva = reservaSubquery.from(Reserva.class);
		Root<EspecialidadSucursal> especialidadSucursal = especialidadSucursalSubQuery.from(EspecialidadSucursal.class);
		Root<Sucursal> sucursal = query.from(Sucursal.class);
		
		
		reservaSubquery.select(reserva.get("sucursal").get("sucursalId"))		
		  .where(criteriaBuilder.and(criteriaBuilder.isNull(reserva.get("fechaSalida")), criteriaBuilder.equal(reserva.get("especialidad").get("especialidadId"), idEspecialidad)));
		especialidadSucursalSubQuery.select(especialidadSucursal.get("sucursal").get("sucursalId"))		
		  .where(criteriaBuilder.equal(especialidadSucursal.get("especialidad").get("especialidadId"),idEspecialidad));
		
		query.multiselect(sucursal.get("sucursalId"), sucursal.get("nombre"), sucursal.get("direccion"), sucursal.join("configuracion").get("cordLongitud"),sucursal.join("configuracion").get("cordLatitud"))
		  .where(criteriaBuilder.and(criteriaBuilder.not(criteriaBuilder.in(sucursal.get("sucursalId")).value(reservaSubquery)))
				  ,criteriaBuilder.in(sucursal.get("sucursalId")).value(especialidadSucursalSubQuery));
		
		TypedQuery<DetalleSucursalDto> typedQuery = entityManager.createQuery(query);
		List<DetalleSucursalDto> resultList = typedQuery.getResultList();
		return resultList;
		
	}
}
