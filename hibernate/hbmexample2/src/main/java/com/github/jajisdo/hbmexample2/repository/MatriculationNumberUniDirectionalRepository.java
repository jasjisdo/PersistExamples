package com.github.jajisdo.hbmexample2.repository;

import com.github.jajisdo.hbmexample2.entity.one2one.unidirectional.MatriculationNumberUniDirectional;
import de.dailab.schaufenster.jpafilter.repository.HibernateJpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by domann on 30.07.15.
 */
@Repository
public interface MatriculationNumberUniDirectionalRepository extends HibernateJpaRepository<MatriculationNumberUniDirectional, Long> {

    @EntityGraph(value = MatriculationNumberUniDirectional.EG_PROFILE_FULL, type = EntityGraph.EntityGraphType.LOAD)
    MatriculationNumberUniDirectional findById(Long id);

    List<Long> getIDs();
}
