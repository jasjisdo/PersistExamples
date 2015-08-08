package com.github.jajisdo.hbmexample2.entity.one2one.unidirectional;

import com.github.jajisdo.hbmexample2.entity.PersistableEntity;

import javax.persistence.*;

/**
 * Created by domann on 30.07.15.
 */
@Entity
@NamedEntityGraph(
        name = Student.EG_PROFILE_FULL,
        attributeNodes = {
                @NamedAttributeNode(value = "name"),
                @NamedAttributeNode(value = "matriculationNumber", subgraph = MatriculationNumber.EG_PROFILE_FULL)
        }
)
@NamedQueries({
        @NamedQuery(name = "Student.getIDs", query = "SELECT s.id FROM Student s")
})
public class Student extends PersistableEntity {

    @Transient
    public transient static final String EG_PROFILE_FULL = "StudentFull";

    @Column(name = "student_name")
    protected String name;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "student_id")
    protected MatriculationNumber matriculationNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MatriculationNumber getMatriculationNumber() {
        return matriculationNumber;
    }

    public void setMatriculationNumber(MatriculationNumber matriculationNumber) throws Exception {
        this.matriculationNumber = matriculationNumber;
    }
}
