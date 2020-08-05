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
import com.cda.turnero.dto.UsuarioLogueadoDto;
import com.cda.turnero.model.Empleado;
import com.cda.turnero.model.EspecialidadSucursal;
import com.cda.turnero.model.Persona;
import com.cda.turnero.model.Reserva;
import com.cda.turnero.model.Usuario;

@Repository
public class EmpleadoDaoImpl {

	@PersistenceContext	
	private EntityManager entityManager;
	
	public Empleado getEmpleadoByUsuarioYContrasena(String usuario, String contrasena) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Empleado> query = criteriaBuilder.createQuery(Empleado.class);
		
		Root<Empleado> empleado = query.from(Empleado.class);
		
		Join userJoin = empleado.join("usuario");
		
		query.select(empleado)
		.where(criteriaBuilder.and(
				criteriaBuilder.equal(userJoin.get("usuario"), usuario),
				criteriaBuilder.equal(userJoin.get("password"),contrasena),
				criteriaBuilder.isNull(empleado.get("fechaBaja"))));
		
		TypedQuery<Empleado> typedQuery = entityManager.createQuery(query);
		Empleado empl = typedQuery.getSingleResult();		
		return empl;
	}
	public List<Empleado> getEmpleadosByRolAndSucursalId(String rol, Integer sucursalId){
		
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Empleado> query = criteriaBuilder.createQuery(Empleado.class);
		
		Root<Empleado> empleado = query.from(Empleado.class);
		
		Join tipoUsuarioJoin = empleado.join("usuario").join("tipoUsuario");
		
		query.select(empleado)
		.where(criteriaBuilder.and(
				criteriaBuilder.equal(tipoUsuarioJoin.get("detalle"), rol),
				criteriaBuilder.equal(empleado.join("sucursal").get("sucursalId"),sucursalId),
				criteriaBuilder.isNull(empleado.get("fechaBaja"))));
		
		TypedQuery<Empleado> typedQuery = entityManager.createQuery(query);
		List<Empleado> empl = typedQuery.getResultList();		
		return empl;
	}
	public Empleado getEmpleadoByUsername(String usuario){
			
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Empleado> query = criteriaBuilder.createQuery(Empleado.class);
		
		Root<Empleado> empleado = query.from(Empleado.class);
		
		query.select(empleado)
		.where(criteriaBuilder.equal(empleado.join("usuario").get("usuario"),usuario));
		
		TypedQuery<Empleado> typedQuery = entityManager.createQuery(query);
		Empleado empl = typedQuery.getSingleResult();		
		return empl;
	}
	
}
