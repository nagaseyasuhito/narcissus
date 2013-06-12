package com.github.nagaseyasuhito.narcissus.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class BaseEntity<T extends Serializable> implements Serializable, Comparable<BaseEntity<T>> {

	public abstract T getId();

	@Override
	public int compareTo(BaseEntity<T> o) {
		return new CompareToBuilder().append(this.getId(), o.getId()).toComparison();
	}

	@Override
	public int hashCode() {
		return this.getId() == null ? super.hashCode() : this.getId().hashCode();
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}

		if (!this.getClass().isAssignableFrom(object.getClass()) && !object.getClass().isAssignableFrom(this.getClass())) {
			return false;
		}

		BaseEntity<?> entity = (BaseEntity<?>) object;
		if (this.getId() == null || entity.getId() == null) {
			return super.equals(entity);
		}

		return this.getId().equals(entity.getId());
	}

	@Override
	public String toString() {
		Class<?> clazz = this.getClass();
		List<String> excludeFieldNames = new ArrayList<String>();
		do {
			for (Field field : clazz.getDeclaredFields()) {
				Class<?> type = field.getType();

				if (Collection.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type)) {
					excludeFieldNames.add(field.getName());
				}
			}
		} while (null != (clazz = clazz.getSuperclass()));

		ReflectionToStringBuilder reflectionToStringBuilder = new ReflectionToStringBuilder(this);
		reflectionToStringBuilder.setExcludeFieldNames(excludeFieldNames.toArray(new String[0]));
		return reflectionToStringBuilder.toString();
	}
}
