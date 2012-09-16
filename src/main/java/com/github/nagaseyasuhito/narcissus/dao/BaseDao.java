package com.github.nagaseyasuhito.narcissus.dao;

import java.io.Serializable;
import java.util.List;
import java.util.SortedMap;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.github.nagaseyasuhito.fatsia.Criterias;
import com.github.nagaseyasuhito.fatsia.criteria.EntityCriteria;
import com.github.nagaseyasuhito.narcissus.entity.BaseEntity;

public abstract class BaseDao<S extends Serializable, T extends BaseEntity<S>> {

	@Inject
	private EntityManager entityManager;

	public long countByCriteria(EntityCriteria<T> criteria) {
		return Criterias.count(this.entityManager, criteria);
	}

	public T findByCriteria(EntityCriteria<T> criteria) {
		return Criterias.find(this.entityManager, criteria);
	}

	public List<T> findByCriteria(EntityCriteria<T> criteria, SortedMap<String, Boolean> orders) {
		return this.findByCriteria(criteria, orders, -1, -1);
	}

	public List<T> findByCriteria(EntityCriteria<T> criteria, SortedMap<String, Boolean> orders, int firstResult, int maxResults) {
		return Criterias.find(this.entityManager, criteria, orders, firstResult, maxResults);
	}

	public T findById(S id) {
		return this.entityManager.find(this.getEntityClass(), id);
	}

	public abstract Class<T> getEntityClass();

	public T merge(T entity) {
		return this.entityManager.merge(entity);
	}

	public void persist(T entity) {
		this.entityManager.persist(entity);
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
		this.entityManager.remove(entity);
	}
}
