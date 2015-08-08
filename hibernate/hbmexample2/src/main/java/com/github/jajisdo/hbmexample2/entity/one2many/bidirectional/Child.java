package com.github.jajisdo.hbmexample2.entity.one2many.bidirectional;

import com.github.jajisdo.hbmexample2.entity.PersistableEntity;

import javax.persistence.*;

/**
 * Created by domann on 15.07.15.
 */
@Entity
@NamedEntityGraph(
        name = Child.EG_PROFILE_FULL,
        attributeNodes = {
                @NamedAttributeNode(value = "name"),
                @NamedAttributeNode(value = "parent", subgraph = Parent.EG_PROFILE_FULL)
        }
)
@NamedQueries({
        @NamedQuery(name = "Child.getIDs", query = "SELECT ch.id FROM Child ch")
})
public class Child extends PersistableEntity {

    @Transient
    public transient static final String EG_PROFILE_FULL = "ChildFull";

    @Column(name = "child_name")
    private String name;

    @ManyToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name="parent_id",
            nullable = true, updatable = true, insertable = true)
    private Parent parent;

    /*
   * hibernate "reflection-magic" constructor
   */
    protected Child(){
        super();
    }

    public Child(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

}
