package com.github.nagaseyasuhito.narcissus.dao;

import java.io.Serializable;
import java.util.List;
import java.util.SortedMap;

import javax.persistence.EntityManager;

import com.github.nagaseyasuhito.fatsia.Criterias;
import com.github.nagaseyasuhito.fatsia.criteria.EntityCriteria;
import com.github.nagaseyasuhito.narcissus.entity.BaseEntity;

public abstract class BaseDao<S extends Serializable, T extends BaseEntity<S>> {

	protected abstract EntityManager getEntityManager();

	public long countByCriteria(EntityCriteria<T> criteria) {
		return Criterias.count(this.getEntityManager(), criteria);
	}

	public T findByCriteria(EntityCriteria<T> criteria) {
		return Criterias.find(this.getEntityManager(), criteria);
	}

	public List<T> findByCriteria(EntityCriteria<T> criteria, SortedMap<String, Boolean> orders) {
		return Criterias.find(this.getEntityManager(), criteria, orders);
	}

	public List<T> findByCriteria(EntityCriteria<T> criteria, SortedMap<String, Boolean> orders, int firstResult, int maxResults) {
		return Criterias.find(this.getEntityManager(), criteria, orders, firstResult, maxResults);
	}

	public T findById(S id) {
		return this.getEntityManager().find(this.getEntityClass(), id);
	}

	public abstract Class<T> getEntityClass();

	public T merge(T entity) {
		return this.getEntityManager().merge(entity);
	}

	public void persist(T entity) {
		this.getEntityManager().persist(entity);
	}

	public T persistOrMerge(T entity) {
		if (entity.getId() == null) {
			this.persist(entity);
			return entity;
		} else {
			return this.merge(entity);
		}
	}

	public void remove(T entity) {
		this.getEntityManager().remove(entity);
	}
}
