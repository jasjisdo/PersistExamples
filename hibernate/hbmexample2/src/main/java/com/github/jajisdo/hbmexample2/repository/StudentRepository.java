package com.github.jajisdo.hbmexample2.repository;

import com.github.jajisdo.hbmexample2.entity.one2one.unidirectional.Student;
import de.dailab.schaufenster.jpafilter.repository.HibernateJpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by domann on 30.07.15.
 */
@Repository
public interface StudentRepository extends HibernateJpaRepository<Student, Long> {

    @EntityGraph(value = Student.EG_PROFILE_FULL, type = EntityGraph.EntityGraphType.LOAD)
    Student findById(Long id);

    List<Long> getIDs();
}
