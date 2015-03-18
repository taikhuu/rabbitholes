package com.kms.challenges.rbh.domain;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class BaseEntity<ID> {

    @Column(name = "creation_time", nullable = false)
    private Date creationTime;

    @Column(name = "modification_time", nullable = false)
    private Date modificationTime;

    @Version
    private long version;

    public abstract ID getId();

    public Date getCreationTime() {
        return creationTime;
    }

    public Date getModificationTime() {
        return modificationTime;
    }

    public long getVersion() {
        return version;
    }

    @PrePersist
    public void prePersist() {
        Date now = new Date();
        this.creationTime = now;
        this.modificationTime = now;
    }

    @PreUpdate
    public void preUpdate() {
        this.modificationTime = new Date();
    }
}
