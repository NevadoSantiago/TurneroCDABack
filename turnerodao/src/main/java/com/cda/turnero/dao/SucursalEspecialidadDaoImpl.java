package com.cda.turnero.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.cda.turnero.model.EspecialidadSucursal;
import com.cda.turnero.model.Sucursal;

@Repository
public class SucursalEspecialidadDaoImpl {
	
	@PersistenceContext	
	private EntityManager entityManager;
	
    public List<EspecialidadSucursal> getSucursalEspecialidadByEspecialidadId(Integer especialidadId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<EspecialidadSucursal> criteriaQuery = criteriaBuilder.createQuery(EspecialidadSucursal.class);
        Root<EspecialidadSucursal> especialidadSucursal = criteriaQuery.from(EspecialidadSucursal.class); 
        TypedQuery<EspecialidadSucursal> query = entityManager.createQuery(criteriaQuery.select(especialidadSucursal)
        		.where(criteriaBuilder.equal(especialidadSucursal.get("especialidad"), especialidadId)));   
        
        return query.getResultList();
    }  
    public List<EspecialidadSucursal> getBySucursalLike(Integer sucursalId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<EspecialidadSucursal> criteriaQuery = criteriaBuilder.createQuery(EspecialidadSucursal.class);
        Root<EspecialidadSucursal> especialidadSucursal = criteriaQuery.from(EspecialidadSucursal.class); 
        TypedQuery<EspecialidadSucursal> query = entityManager.createQuery(criteriaQuery.select(especialidadSucursal)
        		.where(criteriaBuilder.equal(especialidadSucursal.join("sucursal").get("sucursalId"), sucursalId)));   
        
        return query.getResultList();
    	
    }
    

}
