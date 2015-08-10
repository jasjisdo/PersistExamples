package com.github.jajisdo.hbmexample2.entity.one2one.bidirectional;

import com.github.jajisdo.hbmexample2.entity.PersistableEntity;

import javax.persistence.*;

/**
 * Created by domann on 10.08.2015.
 */
@Entity
@NamedEntityGraph(
        name = Soul.EG_PROFILE_FULL,
        attributeNodes = {
                @NamedAttributeNode(value = "body"),
        }
)
@NamedQueries({
        @NamedQuery(name = "Soul.getIDs", query = "SELECT s.id FROM Soul s")
})
public class Soul extends PersistableEntity {

    @Transient
    public static final String EG_PROFILE_FULL = "SoulFull";

    @OneToOne(
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "body_id")
    protected Body body;

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

}
