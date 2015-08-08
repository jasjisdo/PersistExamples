package com.github.jajisdo.hbmexample2.repository;

import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.Parent;
import de.dailab.schaufenster.jpafilter.repository.HibernateJpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by domann on 15.07.15.
 */
@Repository
public interface ParentRepository extends HibernateJpaRepository<Parent, Long> {

    @EntityGraph(value = Parent.EG_PROFILE_FULL, type = EntityGraph.EntityGraphType.LOAD)
    Parent findById(Long id);

    @EntityGraph(value = Parent.EG_PROFILE_FULL, type = EntityGraph.EntityGraphType.LOAD)
    Parent findByName(String name);

    List<Long> getIDs();

}
