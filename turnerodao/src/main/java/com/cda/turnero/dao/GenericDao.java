package com.cda.turnero.dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

public class GenericDao <Entity, EntityManager>{	
	
	public Class<Entity> domainClass = getDomainClass();
	public CriteriaBuilder criteriaBuilder;
	public CriteriaQuery<Entity> criteriaQuery;
	
	@SuppressWarnings("rawtypes")
	public Class getDomainClass() {
		ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
		domainClass = (Class) thisType.getActualTypeArguments()[0];		
		criteriaBuilder = ((javax.persistence.EntityManager)thisType.getActualTypeArguments()[1]).getCriteriaBuilder();
		criteriaQuery = criteriaBuilder.createQuery(domainClass);
		return domainClass;
	}
	
	public Root<Entity> getRoot() {        
        Root<Entity> root = criteriaQuery.from(domainClass); 
        return root;
	}
	
	public CriteriaQuery<Entity> getCriteriaPpal() {	
		return criteriaQuery.select(getRoot());
	}
}
