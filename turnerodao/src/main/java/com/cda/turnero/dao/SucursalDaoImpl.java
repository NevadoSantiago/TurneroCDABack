package com.cda.turnero.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import com.cda.turnero.dto.DetalleSucursalDto;
import com.cda.turnero.model.Reserva;
import com.cda.turnero.model.Sucursal;

@Repository
public class SucursalDaoImpl {

	@PersistenceContext	
	private EntityManager entityManager;

	public List<DetalleSucursalDto> getSucursalesByReservas() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<DetalleSucursalDto> query = criteriaBuilder.createQuery(DetalleSucursalDto.class);
		Root<Reserva> reserva = query.from(Reserva.class);
		Join sucursal  = reserva.join("sucursal");
		query.where(criteriaBuilder.isNull(reserva.get("fechaSalida")));
		query.multiselect(sucursal.get("sucursalId"), sucursal.get("nombre"), sucursal.get("direccion"), criteriaBuilder.count(sucursal.get("sucursalId")),sucursal.join("configuracion").get("cordLongitud"),sucursal.join("configuracion").get("cordLatitud"));
		query.groupBy(sucursal.get("sucursalId"));
		query.orderBy(criteriaBuilder.asc(criteriaBuilder.count(sucursal.get("sucursalId"))));
		TypedQuery<DetalleSucursalDto> typedQuery = entityManager.createQuery(query);
		List<DetalleSucursalDto> resultList = typedQuery.getResultList();
		List<DetalleSucursalDto> resultado = getSucursalesSinReservas();
		resultado.addAll(resultList);
		return resultado;
	}
	private List<DetalleSucursalDto> getSucursalesSinReservas(){
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();		
		CriteriaQuery<DetalleSucursalDto> query = criteriaBuilder.createQuery(DetalleSucursalDto.class);
		Subquery<Reserva> reservaSubquery = query.subquery(Reserva.class);
		Root<Reserva> reserva = reservaSubquery.from(Reserva.class);
		Root<Sucursal> sucursal = query.from(Sucursal.class);
		reservaSubquery.select(reserva.join("sucursal").get("sucursalId"))
		  .where(criteriaBuilder.isNull(reserva.get("fechaSalida")));
		query.multiselect(sucursal.get("sucursalId"), sucursal.get("nombre"), sucursal.get("direccion"), sucursal.join("configuracion").get("cordLongitud"),sucursal.join("configuracion").get("cordLatitud"))
		  .where(criteriaBuilder.not(criteriaBuilder.in(sucursal.get("sucursalId")).value(reservaSubquery)));
		TypedQuery<DetalleSucursalDto> typedQuery = entityManager.createQuery(query);
		List<DetalleSucursalDto> resultList = typedQuery.getResultList();
		return resultList;
		
	}
//	public List<DetalleSucursalDto> getSucursalesByReservas() {
//		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//		
//		CriteriaQuery<DetalleSucursalDto> query = criteriaBuilder.createQuery(DetalleSucursalDto.class);
//		CriteriaQuery<Reserva> queryReserva = criteriaBuilder.createQuery(Reserva.class);
//		
//		Root<Sucursal> sucursal = query.from(Sucursal.class);
//		Subquery<Long> countReserva = queryReserva.subquery(Long.class);
//		query.where(criteriaBuilder.isNull(reserva.get("fechaSalida")));
//		countReserva.select(criteriaBuilder.count(sucursal.get("sucursalId")));
//		query.multiselect(sucursal.get("sucursalId"), sucursal.get("nombre"), sucursal.get("direccion"), criteriaBuilder.count(sucursal.get("sucursalId")));
//		query.groupBy(sucursal.get("sucursalId"));
//		query.orderBy(criteriaBuilder.desc(criteriaBuilder.count(sucursal.get("sucursalId"))));
//		TypedQuery<DetalleSucursalDto> typedQuery = entityManager.createQuery(query);
//		List<DetalleSucursalDto> resultList = typedQuery.getResultList();
//		return resultList;
//	}
}
