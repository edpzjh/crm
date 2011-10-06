package org.jboss.seam.examples.seamcrm.contact;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.jboss.seam.examples.seamcrm.aaa.ContactTools;
import org.jboss.seam.examples.seamcrm.account.Account;
import org.jboss.seam.examples.seamcrm.core.IndexedEntity;
import org.jboss.seam.examples.seamcrm.core.PhoneNumber;
import org.jboss.seam.examples.seamcrm.enumerations.ContactType;
import org.jboss.seam.examples.seamcrm.enumerations.EntityType;
import org.jboss.seam.examples.seamcrm.enumerations.Honorific;
import org.jboss.seam.examples.seamcrm.enumerations.TimeZone;
import org.jboss.seam.examples.seamcrm.tools.StringTools;


/**
 * 
 * @author Cody Lerum
 * 
 */
@Indexed
@EntityListeners(value = { ContactEntityListener.class })
@Entity
public class Contact implements IndexedEntity, Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private int version;
    private ContactType type = ContactType.PERSON;
    private String emailAddress;
    private String firstName;
    private String lastName;
    private String roleName;
    private String alias;
    private String title;
    private Honorific honorific;
    private TimeZone timeZone;
    private Account account;
    private List<PhoneNumber> phoneNumbers = new ArrayList<PhoneNumber>();

    private Date dateCreated;
    private Date dateModified;
    
    private Contact original;

    private static final String[] indexedFields = { "contact.name", "contact.emailAddres", "contact.alias" };

    @Transient
    public String getRecentViewName() {
        return getName();
    }

    @Transient
    public String[] getIndexedFields() {
        return indexedFields;
    }

    @Transient
    public EntityType getEntityType() {
        return EntityType.CONTACT;
    }

    @Transient
    public String getEntityId() {
        return id.toString();
    }

    @Transient
    public String getSearchResultName() {
        return getName();
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
    @Enumerated(EnumType.STRING)
    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    @Field(name = "contact.name", index = Index.YES)
    @Transient
    public String getName() {
        return ContactTools.buildName(this);
    }

    @Field(name = "contact.emailAddress", index = Index.YES)
    @Email
    @Column(unique = true)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @NotNull
    @NotEmpty
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    @NotEmpty
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Field(name = "contact.alias", index = Index.YES)
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Enumerated(EnumType.STRING)
    public Honorific getHonorific() {
        return honorific;
    }

    public void setHonorific(Honorific honorific) {
        this.honorific = honorific;
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    public TimeZone getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account", nullable = false)
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
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

    @Transient
    public String getGravatarURL() {
        return ContactTools.buildGravatarURL(this, 180);
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
        setDateCreated(new Date());
        setDateModified(getDateCreated());
    }

    @SuppressWarnings("unused")
    @PreUpdate
    private void preUpdate() {
        setDateModified(new Date());
        normalize();
    }

    private void normalize() {
        firstName = StringTools.normalize(firstName);
        lastName = StringTools.normalize(lastName);
        roleName = StringTools.normalize(roleName);
        alias = StringTools.normalize(alias);
        title = StringTools.normalize(title);
        emailAddress = StringTools.normalize(emailAddress);
    }

    private Contact auditClone() {

        Contact c = new Contact();
        c.setAccount(account);
        c.setFirstName(firstName);
        c.setLastName(lastName);
        c.setRoleName(roleName);
        c.setAlias(alias);
        c.setTitle(title);
        c.setType(type);
        c.setTimeZone(timeZone);
        c.setHonorific(honorific);
        c.setEmailAddress(emailAddress);
        return c;
    }

    @Transient
    public Contact getOriginal() {
        return original;
    }
}
