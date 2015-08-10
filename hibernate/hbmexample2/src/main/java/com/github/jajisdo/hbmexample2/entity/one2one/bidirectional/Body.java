package com.github.jajisdo.hbmexample2.entity.one2one.bidirectional;

import com.github.jajisdo.hbmexample2.entity.PersistableEntity;

import javax.persistence.*;

/**
 * Created by domann on 10.08.2015.
 */
@Entity
@NamedEntityGraph(
        name = Body.EG_PROFILE_FULL,
        attributeNodes = {
                @NamedAttributeNode(value = "soul"),
        }
)
@NamedQueries({
        @NamedQuery(name = "Body.getIDs", query = "SELECT b.id FROM Body b")
})
public class Body extends PersistableEntity {

    @Transient
    public static final String EG_PROFILE_FULL = "BodyFull";

    @OneToOne(mappedBy = "body",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    protected Soul soul;

    public Soul getSoul() {
        return soul;
    }

    public void setSoul(Soul soul) {
        this.soul = soul;
    }

}
