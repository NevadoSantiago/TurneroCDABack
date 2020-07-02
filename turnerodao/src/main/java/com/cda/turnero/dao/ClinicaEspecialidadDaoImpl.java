package com.cda.turnero.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.cda.turnero.model.EspecialidadClinica;
import com.cda.turnero.model.EspecialidadSucursal;

@Repository
public class ClinicaEspecialidadDaoImpl {
	
	@PersistenceContext	
	private EntityManager entityManager;
	
    public List<EspecialidadClinica> getByIdEspecialidad(Integer idEspecialidad) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<EspecialidadClinica> criteriaQuery = criteriaBuilder.createQuery(EspecialidadClinica.class);
        Root<EspecialidadClinica> especialidadClinica = criteriaQuery.from(EspecialidadClinica.class); 
        TypedQuery<EspecialidadClinica> query = entityManager.createQuery(criteriaQuery.select(especialidadClinica)
        		.where(criteriaBuilder.equal(especialidadClinica.get("especialidad"), idEspecialidad)));   
        
        return query.getResultList();
    }  

}
