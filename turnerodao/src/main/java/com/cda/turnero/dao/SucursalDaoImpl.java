package com.cda.turnero.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import com.cda.turnero.model.Reserva;
import com.cda.turnero.model.Sucursal;

@Repository
public class SucursalDaoImpl {

	@PersistenceContext	
	private EntityManager entityManager;


	public List<Object[]> getSucursalesByReservas() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);
		Root<Reserva> reserva = query.from(Reserva.class);
		
		query.where(criteriaBuilder.isNull(reserva.get("fechaSalida")));
		Subquery querySucursal = query.subquery(Sucursal.class);
		Root<Sucursal> sucursal = querySucursal.from(Sucursal.class);
		querySucursal.groupBy(sucursal.get("sucursalId"));
		query.multiselect(sucursal.get("sucursalId"), sucursal.get("nombre"), sucursal.get("direccion"), criteriaBuilder.count(sucursal.get("sucursalId")).alias("cantidad"));
		query.orderBy(criteriaBuilder.desc(criteriaBuilder.count(sucursal.get("sucursalId"))));
		TypedQuery<Object[]> typedQuery = entityManager.createQuery(query);
		List<Object[]> resultList = typedQuery.getResultList();
		return resultList;
	}
	
}
