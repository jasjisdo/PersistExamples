package com.github.jajisdo.hbmexample2.entity.one2one.unidirectional;

import javax.persistence.*;

/**
 * Created by domann on 30.07.15.
 */
@Entity
@NamedEntityGraph(
        name = MatriculationNumberUniDirectional.EG_PROFILE_FULL,
        attributeNodes = {
                @NamedAttributeNode(value = "number")
        }
)
@NamedQueries({
        @NamedQuery(name = "MatriculationNumberUniDirectional.getIDs", query = "SELECT mn.id FROM MatriculationNumberUniDirectional mn")
})
public class MatriculationNumberUniDirectional extends com.github.jajisdo.hbmexample2.entity.Entity {

    @Transient
    public transient static final String EG_PROFILE_FULL = "MatriculationNumberFull";

    @Column(name = "matriculation_number")
    int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
