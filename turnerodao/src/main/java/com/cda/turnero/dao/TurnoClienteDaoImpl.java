package com.cda.turnero.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import com.cda.turnero.enums.EstadoTurno;
import com.cda.turnero.enums.TurnoClienteEnum;
import com.cda.turnero.model.Cliente;
import com.cda.turnero.model.Turno;
import com.cda.turnero.model.TurnoCliente;

@Repository
public class TurnoClienteDaoImpl {

	@PersistenceContext	
	private EntityManager entityManager;
    public TurnoCliente getTurnoClienteByTurnoId(Integer turnoId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<TurnoCliente> criteriaQuery = criteriaBuilder.createQuery(TurnoCliente.class);
        Root<TurnoCliente> turnoCliente = criteriaQuery.from(TurnoCliente.class); 
        TypedQuery<TurnoCliente> query = entityManager.createQuery(criteriaQuery.select(turnoCliente)
        		.where(criteriaBuilder.equal(turnoCliente.get("turno"), turnoId)));   
        
        return query.getSingleResult();
    }   
    public TurnoCliente findTurnoClienteByIdTurnoAndIdClienteAndEstadoNotLikeAnd
    (Integer turnoId, Integer clienteId, TurnoClienteEnum primerEstado,TurnoClienteEnum segundoEstado ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<TurnoCliente> criteriaQuery = criteriaBuilder.createQuery(TurnoCliente.class);
        Root<TurnoCliente> turnoCliente = criteriaQuery.from(TurnoCliente.class); 
        TypedQuery<TurnoCliente> query = entityManager.createQuery(criteriaQuery.select(turnoCliente)
        		.where(criteriaBuilder.equal(turnoCliente.get("cliente"),clienteId),
    					criteriaBuilder.equal(turnoCliente.get("turno"),turnoId),
    					criteriaBuilder.notEqual(turnoCliente.get("estado"), primerEstado),
    					criteriaBuilder.notEqual(turnoCliente.get("estado"), segundoEstado)));
        return query.getSingleResult();
    }   
    public List<TurnoCliente> getTurnoClienteByClient(Integer idCliente) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<TurnoCliente> criteriaQuery = criteriaBuilder.createQuery(TurnoCliente.class);
        Root<TurnoCliente> turnoCliente = criteriaQuery.from(TurnoCliente.class); 
        TypedQuery<TurnoCliente> query = entityManager.createQuery(criteriaQuery.select(turnoCliente)
        		.where(criteriaBuilder.equal(turnoCliente.get("cliente"), idCliente)));   
        
        return query.getResultList();
    }    
}
