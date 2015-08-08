package com.github.jajisdo.hbmexample2.entity.one2many.bidirectional;

import com.github.jajisdo.hbmexample2.entity.PersistableEntity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@NamedEntityGraph(
        name = Parent.EG_PROFILE_FULL,
        attributeNodes = {
                @NamedAttributeNode(value = "name"),
                @NamedAttributeNode(value = "children",
                        subgraph = Child.EG_PROFILE_FULL)
        }
)
@NamedQueries({
        @NamedQuery(name = "Parent.getIDs", query = "SELECT parent.id FROM Parent parent")
})
public class Parent extends PersistableEntity {

    @Transient
    public transient static final String EG_PROFILE_FULL = "MotherFull";

    @Column(name = "child_name")
    private String name;

    @OneToMany(mappedBy = "parent",
            cascade = {CascadeType.ALL})
    @OrderColumn(name = "idx")
    private List<Child> children;

    /*
     * hibernate "reflection-magic" constructor
     */
    protected Parent(){
        super();
    }

    public Parent(String name, List<Child> children) {
        super();
        this.name     = name;
        this.children = children;
        adoptChildren(children);
    }

    private void adoptChildren(List<Child> children) {
        for(Child child : children) {
              child.setParent(this);
        }
    }

    public String getName() {
        return name;
    }

    public List<Child> getChildren() {
        return children;
    }
}
