package com.github.jajisdo.hbmexample2.entity;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by domann on 15.07.15.
 */
@MappedSuperclass
@Access(AccessType.FIELD)
public class PersistableEntity implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(unique=true, nullable = false)
    private final Long id;

    public PersistableEntity() {
        this.id = 0L;
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean isNew() {
        return id == 0L;
    }
}
