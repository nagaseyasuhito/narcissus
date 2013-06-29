package com.github.nagaseyasuhito.narcissus.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseManagedEntity extends BaseEntity<Long> {
	private static final long serialVersionUID = -937435243358240539L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Version
	private Long version;

	@Override
	public Long getId() {
		return this.id;
	}

	public Long getVersion() {
		return this.version;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
