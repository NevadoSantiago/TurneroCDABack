package com.cda.turnero.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.cda.turnero.dto.DetalleReservaAdminDto;
import com.cda.turnero.dto.DetalleSucursalDto;
import com.cda.turnero.model.EspecialidadSucursal;
import com.cda.turnero.model.Reserva;

@Repository
public class ReservaDaoImpl {

	@PersistenceContext	
	private EntityManager entityManager;
	
    public Long getCantidadReservasPorSucursal(Integer sucursalId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Reserva> reserva = criteriaQuery.from(Reserva.class); 
        
        		criteriaQuery.where(criteriaBuilder.and(
        				criteriaBuilder.equal(reserva.join("sucursal").get("sucursalId"), sucursalId),
        				criteriaBuilder.isNull(reserva.get("fechaSalida")),
        				criteriaBuilder.equal(reserva.join("estado").get("nombre"),"PROGRAMADO")));
        		criteriaQuery.select(criteriaBuilder.count(reserva.get("reservaId")));
        		TypedQuery<Long> typedQuery = entityManager.createQuery(criteriaQuery);
        		return typedQuery.getSingleResult();
    }  
    public List<DetalleReservaAdminDto> getListadoReservasPorSucursal(Integer sucursalId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<DetalleReservaAdminDto> criteriaQuery = criteriaBuilder.createQuery(DetalleReservaAdminDto.class);
        Root<Reserva> reserva = criteriaQuery.from(Reserva.class); 
        
        		criteriaQuery.where(criteriaBuilder.and(
        				criteriaBuilder.equal(reserva.join("sucursal").get("sucursalId"), sucursalId),
        				criteriaBuilder.isNull(reserva.get("fechaSalida")),
        				criteriaBuilder.equal(reserva.join("estado").get("nombre"),"PROGRAMADO")));
        		criteriaQuery.multiselect(
        				reserva.join("cliente").get("nombre"),
        				reserva.join("cliente").get("apellido"),
        				reserva.join("cliente").get("nroDocumento"),
        				reserva.join("cliente").get("personaId"),
        				reserva.get("reservaId"),
        				reserva.join("especialidad").get("nombre"));
        		
        		TypedQuery<DetalleReservaAdminDto> typedQuery = entityManager.createQuery(criteriaQuery);
        		List<DetalleReservaAdminDto> result =  typedQuery.getResultList();
        		result.addAll(getListadoReservasSinCliente(sucursalId));
        		return result;
    }
    private List<DetalleReservaAdminDto> getListadoReservasSinCliente(Integer sucursalId){
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    	
        CriteriaQuery<DetalleReservaAdminDto> criteriaQuery = criteriaBuilder.createQuery(DetalleReservaAdminDto.class);
        Root<Reserva> reserva = criteriaQuery.from(Reserva.class); 
        
        		criteriaQuery.where(criteriaBuilder.and(
        				criteriaBuilder.equal(reserva.join("sucursal").get("sucursalId"), sucursalId),
        				criteriaBuilder.isNull(reserva.get("fechaSalida")),
        				criteriaBuilder.isNull(reserva.get("cliente")),
        				criteriaBuilder.equal(reserva.join("estado").get("nombre"),"PROGRAMADO")));
        		criteriaQuery.multiselect(
        				reserva.get("reservaId"),
        				reserva.join("especialidad").get("nombre"));
        		
        		TypedQuery<DetalleReservaAdminDto> typedQuery = entityManager.createQuery(criteriaQuery);
        		return typedQuery.getResultList();
    }
	
}
