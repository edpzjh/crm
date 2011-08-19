package org.jboss.seam.examples.seamcrm.core;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.seam.examples.seamcrm.contact.Contact;
import org.jboss.seam.examples.seamcrm.enumerations.EntityType;
import org.jboss.seam.examples.seamcrm.enumerations.PhoneNumberType;


/**
 * 
 * @author Cody Lerum
 * 
 */
@Entity
public class PhoneNumber implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private int version;
	private String number;
	private PhoneNumberType type;
	private boolean verified;
	private Contact contact;
	private Date dateCreated;
	private Date dateModified;
	private Date dateDeactivated;

	@Transient
    public String getRecentViewName() {
        return number;
    }
	
	@Transient
	public EntityType getEntityType() {
		return EntityType.PHONE_NUMBER;
	}

	@Transient
	public String getEntityId() {
		return id.toString();
	}

	@Transient
	public String toString() {
		return number;
	}

	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Version
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@NotNull
	@NotEmpty
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public PhoneNumberType getType() {
		return type;
	}

	public void setType(PhoneNumberType type) {
		this.type = type;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "contact", nullable = true)
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@NotNull
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@NotNull
	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Date getDateDeactivated() {
		return dateDeactivated;
	}

	public void setDateDeactivated(Date dateDeactivated) {
		this.dateDeactivated = dateDeactivated;
	}

	@SuppressWarnings("unused")
	@PrePersist
	private void prePersist() {
		setDateCreated(new Date());
		setDateModified(getDateCreated());
	}

	@SuppressWarnings("unused")
	@PreUpdate
	private void preUpdate() {
		setDateModified(new Date());
	}
}
