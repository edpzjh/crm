package org.jboss.seam.examples.seamcrm.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.jboss.seam.examples.seamcrm.address.Address;
import org.jboss.seam.examples.seamcrm.contact.Contact;
import org.jboss.seam.examples.seamcrm.core.IndexedEntity;
import org.jboss.seam.examples.seamcrm.enumerations.EntityType;
import org.jboss.seam.examples.seamcrm.tools.StringTools;


@Indexed
@EntityListeners(value = { AccountEntityListener.class })
@Entity
public class Account implements IndexedEntity, Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private int version;
	private String name;
	private AccountType type;
	private List<Contact> contacts = new ArrayList<Contact>();
	private List<Address> addresses = new ArrayList<Address>();
	private Date dateCreated;
	private Date dateModified;

	private Account original;
	
	private static final String[] indexedFields = { "account.name" };
    
    @Transient
    public String[] getIndexedFields() {
        return indexedFields;
    }

    @Transient
    public String getSearchResultName() {
        return name;
    } 

	@Transient
	public EntityType getEntityType() {
		return EntityType.ACCOUNT;
	}

	@Transient
	public String getEntityId() {
		return id.toString();
	}
	
	@Transient
    public String getRecentViewName()
    {
        return name;
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

    @Field(name = "account.name", index = Index.YES)
	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	public List<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    public List<Address> getAddresses()
    {
        return addresses;
    }

    public void setAddresses(List<Address> addresses)
    {
        this.addresses = addresses;
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

	@SuppressWarnings("unused")
	@PostPersist
	private void postPersist() {
		original = auditClone();
	}

	@SuppressWarnings("unused")
	@PostLoad
	private void postLoad() {
		original = auditClone();
	}

	@SuppressWarnings("unused")
	@PostUpdate
	private void postUpdate() {
		original = auditClone();
	}

	@SuppressWarnings("unused")
	@PrePersist
	private void prePersist() {
		normalize();
		setDateCreated(new Date());
		setDateModified(getDateCreated());
	}

	@SuppressWarnings("unused")
	@PreUpdate
	private void preUpdate() {
		normalize();
		setDateModified(new Date());
	}

	private void normalize() {
		name = StringTools.normalize(name);
	}

	private Account auditClone() {
		Account a = new Account();
		a.setName(name);
		a.setType(type);
		return a;
	}

	@Transient
	public Account getOriginal() {
		return original;
	}
	
	@Override
    public String toString() {
        return name;
    }
}
