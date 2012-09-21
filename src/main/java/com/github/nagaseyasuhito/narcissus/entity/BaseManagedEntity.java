package com.github.nagaseyasuhito.narcissus.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseManagedEntity extends BaseEntity<Long> {
	private static final long serialVersionUID = -7344345608027682608L;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTimestamp;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTimestamp;

	@Column(nullable = false)
	@Version
	private Long version;

	public Date getCreateTimestamp() {
		return this.createTimestamp;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public Date getUpdateTimestamp() {
		return this.updateTimestamp;
	}

	public Long getVersion() {
		return this.version;
	}

	@PrePersist
	public void prePersist() {
		Date now = new Date();
		this.createTimestamp = now;
		this.updateTimestamp = now;
	}

	@PreUpdate
	public void preUpdate() {
		Date now = new Date();
		this.updateTimestamp = now;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}
}
