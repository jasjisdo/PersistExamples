package de.dailab.schaufenster.jpafilter.filter;

import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "STUDENT")
@FilterDef(name = "filterBySchoolAndClass", parameters = {
        @ParamDef(name = "school", type = "string"),
        @ParamDef(name = "class", type = "string")
})
public class Student implements Serializable, Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String subject;

    String school;

    @Column(name = "class")
    String _class;

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return false;
    }


}