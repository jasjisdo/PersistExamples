package com.github.jajisdo.hbmexample2.repository;

import com.github.jajisdo.hbmexample2.entity.one2one.unidirectional.StudentUniDirectional;
import de.dailab.schaufenster.jpafilter.repository.HibernateJpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by domann on 30.07.15.
 */
@Repository
public interface StudentUniDirectionalRepository extends HibernateJpaRepository<StudentUniDirectional, Long> {

    @EntityGraph(value = StudentUniDirectional.EG_PROFILE_FULL, type = EntityGraph.EntityGraphType.LOAD)
    StudentUniDirectional findById(Long id);

    List<Long> getIDs();
}
