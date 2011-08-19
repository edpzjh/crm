package org.jboss.seam.examples.seamcrm.aaa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.seam.examples.seamcrm.core.BaseEntity;
import org.jboss.seam.examples.seamcrm.enumerations.EntityType;
import org.jboss.seam.examples.seamcrm.user.User;


/**
 * 
 * @author Cody Lerum
 * 
 */
@Entity
public class EventLog implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private EntityType entityType;
	private String entityId;
	private User user;
	private String message;
	private String remoteAddr;
	private Date date;

	public EventLog() {
		this.date = new Date();
	}

	public EventLog(BaseEntity baseEntity, String message) {
		this();
		this.entityType = baseEntity.getEntityType();
		this.entityId = baseEntity.getEntityId();
		this.message = message;
	}

	@Id
	@GeneratedValue(generator = "eventLogIdGenerator")
	@GenericGenerator(name = "eventLogIdGenerator", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = {
			@Parameter(name = "table_name", value = "primaryKeyPools"),
			@Parameter(name = "segment_value", value = "eventLog"),
			@Parameter(name = "optimizer", value = "pooled"),
			@Parameter(name = "increment_size", value = "5"),
			@Parameter(name = "initial_value", value = "10000") })
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public EntityType getEntityType() {
		return entityType;
	}

	public void setEntityType(EntityType entityType) {
		this.entityType = entityType;
	}

	@NotNull
	@NotEmpty
	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	@ManyToOne
	@JoinColumn(name = "user", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@NotNull
	@NotEmpty
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@NotNull
	@NotEmpty
	public String getRemoteAddr() {
		return remoteAddr;
	}

	public void setRemoteAddr(String remoteAddr) {
		this.remoteAddr = remoteAddr;
	}

	@NotNull
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
