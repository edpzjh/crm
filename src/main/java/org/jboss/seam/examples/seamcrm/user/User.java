package org.jboss.seam.examples.seamcrm.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.seam.examples.seamcrm.contact.Contact;
import org.jboss.seam.examples.seamcrm.core.BaseEntity;
import org.jboss.seam.examples.seamcrm.enumerations.EntityType;


/**
 * 
 * @author Cody Lerum
 * 
 */
@Entity
public class User implements BaseEntity, Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private int version;
	private Contact contact;
	private String password;

	private Date dateCreated;
	private Date dateModified;
	private Date dateLastLogin;
	private Date dateInactive;

	@Transient
    public String getRecentViewName() {
        return contact.getName();
    }
	
	@Transient
	public EntityType getEntityType() {
		return EntityType.USER;
	}

	@Transient
	public String getEntityId() {
		return id.toString();
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
    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "contact")
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@NotNull
	@NotEmpty
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	private void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
	}

	public Date getDateLastLogin() {
		return dateLastLogin;
	}

	public void setDateLastLogin(Date dateLastLogin) {
		this.dateLastLogin = dateLastLogin;
	}

	public Date getDateInactive() {
		return dateInactive;
	}

	public void setDateInactive(Date dateInactive) {
		this.dateInactive = dateInactive;
	}

	@SuppressWarnings("unused")
	@PrePersist
	private void prePersist() {
		setDateModified(getDateCreated());
	}

	@SuppressWarnings("unused")
	@PreUpdate
	private void preUpdate() {
		setDateModified(new Date());
	}    
}
