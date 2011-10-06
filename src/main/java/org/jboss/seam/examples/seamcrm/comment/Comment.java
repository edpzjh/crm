package org.jboss.seam.examples.seamcrm.comment;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
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
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.Length;
import org.jboss.seam.examples.seamcrm.core.BaseEntity;
import org.jboss.seam.examples.seamcrm.enumerations.EntityType;
import org.jboss.seam.examples.seamcrm.tools.StringTools;
import org.jboss.seam.examples.seamcrm.user.User;
/**
 * 
 * @author Cody Lerum
 * 
 */
@Entity
@Table(appliesTo = "Comment", indexes = { @Index(name = "entityType_entityId", columnNames = { "entityType", "entityId" }) })
public class Comment implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private int version;
    private EntityType entityType;
    private String entityId;
    private User user;
    private String comment;
    private Date dateCreated;
    private Date dateModified;
    private boolean deleted;

    public Comment() {

    }    

    public Comment(BaseEntity baseEntity) {
        this.entityType = baseEntity.getEntityType();
        this.entityId = baseEntity.getEntityId();
    }
    
    public Comment(BaseEntity baseEntity, User user) {
        this(baseEntity);
        this.user = user;
    }
    
    public Comment(BaseEntity baseEntity, String comment) {
        this(baseEntity);
        this.comment = comment;
    }   

    @Id
    @GeneratedValue(generator = "commentIdGenerator")
    @GenericGenerator(name = "commentIdGenerator", strategy = "org.hibernate.id.enhanced.TableGenerator", parameters = { @Parameter(name = "table_name", value = "primaryKeyPools"),
            @Parameter(name = "segment_value", value = "note"), @Parameter(name = "optimizer", value = "pooled"), @Parameter(name = "increment_size", value = "5"),
            @Parameter(name = "initial_value", value = "100") })
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    @Column(length = 25)
    @Enumerated(EnumType.STRING)
    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    @NotNull
    @Column(length = 15)
    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @NotNull
    @Length(max = 4000)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    @NotNull
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    private void normalize() {
        comment = StringTools.normalize(comment);
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
}
