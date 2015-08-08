package com.github.jajisdo.hbmexample2.repository;

import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.Child;
import de.dailab.schaufenster.jpafilter.repository.HibernateJpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by domann on 15.07.15.
 */
@Repository
public interface ChildRepository extends HibernateJpaRepository<Child, Long> {

    @EntityGraph(value = Child.EG_PROFILE_FULL, type = EntityGraph.EntityGraphType.LOAD)
    Child findById(Long id);

    List<Long> getIDs();

}
