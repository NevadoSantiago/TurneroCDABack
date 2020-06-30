package com.cda.turnero.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.cda.turnero.model.Recepcion;

@Repository
public class CajaDaoImpl{
	
	@PersistenceContext	
	private EntityManager entityManager;
		    
    public List<Recepcion> getCajasByTipoCaja(Integer tipoCajaId) {
    	
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Recepcion> criteriaQuery = criteriaBuilder.createQuery(Recepcion.class);
        Root<Recepcion> caja = criteriaQuery.from(Recepcion.class); 
        TypedQuery<Recepcion> query = entityManager.createQuery(criteriaQuery.select(caja).where(criteriaBuilder.equal
        		(caja.get("tipoCaja").get("tipoCajaId"), tipoCajaId)));
        
        return query.getResultList();
        
    }
    
    public List<Recepcion> getCajasBySucursal(Integer sucursalId) {
    	
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Recepcion> criteriaQuery = criteriaBuilder.createQuery(Recepcion.class);
        Root<Recepcion> caja = criteriaQuery.from(Recepcion.class); 
        TypedQuery<Recepcion> query = entityManager.createQuery(criteriaQuery.select(caja).where(criteriaBuilder.equal
        		(caja.get("sucursal").get("sucursalId"), sucursalId)));
        
        return query.getResultList();
    	
    }
    
    public List<Recepcion> getCajasByEstado(boolean estado) {
    	
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Recepcion> criteriaQuery = criteriaBuilder.createQuery(Recepcion.class);
        Root<Recepcion> caja = criteriaQuery.from(Recepcion.class); 
        TypedQuery<Recepcion> query = entityManager.createQuery(criteriaQuery.select(caja).where(criteriaBuilder.equal
        		(caja.get("activa"), estado)));
        
        return query.getResultList();
    	
    }
	
}
