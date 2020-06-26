package com.cda.turnero.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.cda.turnero.model.Turno;
import com.cda.turnero.model.TurnoCaja;
import com.cda.turnero.model.TurnoCliente;

@Repository
public class TurnoDaoImpl {

	@PersistenceContext	
	private EntityManager entityManager;
			
    public List<Turno> getTurnosByMail(String mail) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Turno> criteriaQuery = criteriaBuilder.createQuery(Turno.class);
        Root<Turno> turno = criteriaQuery.from(Turno.class); 
        TypedQuery<Turno> query = entityManager.createQuery(criteriaQuery.select(turno)
        		.where(criteriaBuilder.equal(turno.get("cliente").get("mail"), mail)));      
        return query.getResultList();
    }    
    
    public TurnoCliente getTurnoClienteByQr(int codigoQr_id) {
    	
    	CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TurnoCliente> criteriaQuery = criteriaBuilder.createQuery(TurnoCliente.class);
		Root<TurnoCliente> turno = criteriaQuery.from(TurnoCliente.class);
		TypedQuery<TurnoCliente> query = entityManager.createQuery(
				criteriaQuery.select(turno).where(criteriaBuilder.equal(turno.get("codigoQr"), codigoQr_id)));
		return query.getSingleResult();
    	
	}
        
    public Long countTurnoCajaInCaja(Integer cajaId) {
    	CriteriaBuilder qb = entityManager.getCriteriaBuilder();
    	CriteriaQuery<Long> cq = qb.createQuery(Long.class);
    	Root<TurnoCaja> turno = cq.from(TurnoCaja.class);
    	cq.select(qb.count(cq.from(TurnoCliente.class)));
    	cq.where(qb.equal(turno.get("caja"), cajaId));
    	return entityManager.createQuery(cq).getSingleResult();
    }
}