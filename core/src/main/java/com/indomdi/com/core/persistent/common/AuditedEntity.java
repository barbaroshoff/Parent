package com.indomdi.com.core.persistent.common;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

import static javax.persistence.TemporalType.TIMESTAMP;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditedEntity extends Identifiable {

	private static final long serialVersionUID = -646834166240901393L;

	@Column(name = "created_date", updatable = false, nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
	@CreatedDate
	private LocalDateTime created;

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	@Column(name = "updated_date", updatable = true, nullable = true)
	@LastModifiedDate
	@Temporal(TIMESTAMP)
	private Date updated;

	public Date getUpdated() {
		return updated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(created, updated);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof AuditedEntity)) {
			return false;
		}
		final AuditedEntity other = (AuditedEntity) obj;
		return Objects.equals(created, other.created) && Objects.equals(updated, other.updated);
	}
}
