package de.hbmexample2.entity.one2many.bidirectional;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedEntityGraph(
        name = MotherBiDirectional.EG_PROFILE_FULL,
        attributeNodes = {
                @NamedAttributeNode(value = "name"),
                @NamedAttributeNode(value = "children",
                        subgraph = ChildBiDirectional.EG_PROFILE_FULL)
        }
)
@NamedQueries({
        @NamedQuery(name = "MotherBiDirectional.getIDs", query = "SELECT mum.id FROM MotherBiDirectional mum")
})
public class MotherBiDirectional extends de.hbmexample2.entity.Entity {

    @Transient
    public transient static final String EG_PROFILE_FULL = "MotherFull";

    @Column(name = "child_name")
    private String name;

    @OneToMany(mappedBy = "mother",
            cascade = {CascadeType.ALL})
    @OrderColumn(name = "idx")
    private List<ChildBiDirectional> children;

    /*
     * hibernate "reflection-magic" constructor
     */
    protected MotherBiDirectional(){
        super();
    }

    public MotherBiDirectional(String name, List<ChildBiDirectional> children) {
        super();
        this.name     = name;
        this.children = children;
        adoptChildren(children);
    }

    private void adoptChildren(List<ChildBiDirectional> children) {
        for(ChildBiDirectional child : children) {
              child.setMother(this);
        }
    }

    public String getName() {
        return name;
    }

    public List<ChildBiDirectional> getChildren() {
        return children;
    }
}
