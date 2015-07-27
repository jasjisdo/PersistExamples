package com.github.jajisdo.hbmexample2.entity.one2many.bidirectional;

import javax.persistence.*;
import javax.persistence.Entity;

/**
 * Created by domann on 15.07.15.
 */
@Entity
@NamedEntityGraph(
        name = ChildBiDirectional.EG_PROFILE_FULL,
        attributeNodes = {
                @NamedAttributeNode(value = "name"),
                @NamedAttributeNode(value = "mother", subgraph = MotherBiDirectional.EG_PROFILE_FULL)
        }
)
@NamedQueries({
        @NamedQuery(name = "ChildBiDirectional.getIDs", query = "SELECT ch.id FROM ChildBiDirectional ch")
})
public class ChildBiDirectional extends com.github.jajisdo.hbmexample2.entity.Entity {

    @Transient
    public transient static final String EG_PROFILE_FULL = "ChildFull";

    @Column(name = "child_name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="mother_id",
            nullable = true, updatable = true, insertable = true)
    private MotherBiDirectional mother;

    /*
   * hibernate "reflection-magic" constructor
   */
    protected ChildBiDirectional(){
        super();
    }

    public ChildBiDirectional(String name) {
        super();
        this.name   = name;
    }

    public String getName() {
        return name;
    }

    public MotherBiDirectional getMother() {
        return mother;
    }

    public void setMother(MotherBiDirectional mother) {
        this.mother = mother;
    }
}
