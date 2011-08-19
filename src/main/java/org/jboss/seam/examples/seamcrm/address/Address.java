package org.jboss.seam.examples.seamcrm.address;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.jboss.seam.examples.seamcrm.account.Account;
import org.jboss.seam.examples.seamcrm.core.IndexedEntity;
import org.jboss.seam.examples.seamcrm.enumerations.AddressType;
import org.jboss.seam.examples.seamcrm.enumerations.County;
import org.jboss.seam.examples.seamcrm.enumerations.EntityType;
import org.jboss.seam.examples.seamcrm.enumerations.State;
import org.jboss.seam.examples.seamcrm.tools.StringTools;


/**
 * 
 * @author Cody Lerum
 * 
 */
@Indexed
@Entity
public class Address implements IndexedEntity, Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private int version;
    private Account account;
    private String description;
    private AddressType type;
    private String address1;
    private String address2;
    private String city;
    private State state;
    private String postalCode;
    private County county;
    private Date dateCreated;
    private Date dateModified;
    private Date dateValidated;
    private Date dateDeactivated;

    private static final String[] indexedFields = { "address.description", "address.addressLine" };

    @Transient
    public String[] getIndexedFields() {
        return indexedFields;
    }

    @Transient
    public String getSearchResultName() {
        return address1;
    }

    @Transient
    public String getRecentViewName() {
        return address1;
    }

    @Transient
    public EntityType getEntityType() {
        return EntityType.ADDRESS;
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
    
    @ManyToOne
    @JoinColumn(name = "account", nullable = false)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Field(name = "address.description", index = Index.TOKENIZED)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    public AddressType getType() {
        return type;
    }

    public void setType(AddressType type) {
        this.type = type;
    }

    @NotNull
    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @NotNull
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
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

    public Date getDateValidated() {
        return dateValidated;
    }

    public void setDateValidated(Date dateValidated) {
        this.dateValidated = dateValidated;
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

    @Field(name = "address.addressLine", index = Index.TOKENIZED)
    @Transient
    public String getAddressLine() {
        if (!StringTools.isNullOrBlank(address2)) {
            return address1 + " " + address2;
        } else {
            return address1;
        }
    }

    @Override
    public String toString() {
        return AddressTools.fullAddressSingleLine(this);
    }
}
