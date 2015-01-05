package com.flectosystems.morphiasparkapi.models;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Version;

import java.util.Date;

/**
 * Provide the BaseEntity implementation for all entities:
 * <p/>
 * ID, creation and last change date, version, their getters and setters
 * (including @PrePersist), and some abstract methods we'll require in the
 * specific entities.
 * <p/>
 * Created by ernesto on 1/4/15.
 */
public abstract class BaseEntity {

    @Id
    protected ObjectId id;

    /*
     * Only getters provided, the setters are handled in @PrePersist.
     */
    protected Date creationDate;
    protected Date updateDate;

    @Version
    private long v;

    public abstract String toString();

    public BaseEntity() {
        super();
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * Handles all the behaviour before persisting the entity.
     * Among them, setting the updateDate
     */
    @PrePersist
    protected void prePersist() {
        creationDate = (null != creationDate) ? creationDate : new Date();
        updateDate = (null != updateDate) ? new Date() : creationDate;
    }
}
