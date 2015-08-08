package com.github.jajisdo.hbmexample2.entity.one2one.unidirectional;

import com.github.jajisdo.hbmexample2.entity.PersistableEntity;

import javax.persistence.*;

/**
 * Created by domann on 30.07.15.
 */
@Entity
@NamedEntityGraph(
        name = MatriculationNumber.EG_PROFILE_FULL,
        attributeNodes = {
                @NamedAttributeNode(value = "number")
        }
)
@NamedQueries({
        @NamedQuery(name = "MatriculationNumber.getIDs", query = "SELECT mn.id FROM MatriculationNumber mn")
})
public class MatriculationNumber extends PersistableEntity {

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
