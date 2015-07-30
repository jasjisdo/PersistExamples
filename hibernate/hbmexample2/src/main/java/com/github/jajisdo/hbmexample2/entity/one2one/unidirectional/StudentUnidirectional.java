package com.github.jajisdo.hbmexample2.entity.one2one.unidirectional;

import javax.persistence.*;

/**
 * Created by domann on 30.07.15.
 */
@Entity
@NamedEntityGraph(
        name = StudentUniDirectional.EG_PROFILE_FULL,
        attributeNodes = {
                @NamedAttributeNode(value = "name"),
                @NamedAttributeNode(value = "matriculationNumber", subgraph = MatriculationNumberUniDirectional.EG_PROFILE_FULL)
        }
)
@NamedQueries({
        @NamedQuery(name = "StudentUniDirectional.getIDs", query = "SELECT s.id FROM StudentUniDirectional s")
})
public class StudentUniDirectional extends com.github.jajisdo.hbmexample2.entity.Entity {

    @Transient
    public transient static final String EG_PROFILE_FULL = "StudentFull";

    @Column(name = "student_name")
    protected String name;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "student_id")
    protected MatriculationNumberUniDirectional matriculationNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MatriculationNumberUniDirectional getMatriculationNumber() {
        return matriculationNumber;
    }

    public void setMatriculationNumber(MatriculationNumberUniDirectional matriculationNumber) throws Exception {
        this.matriculationNumber = matriculationNumber;
    }
}
