package com.github.jajisdo.hbmexample2.repository;

import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.ChildBiDirectional;
import de.dailab.schaufenster.jpafilter.repository.GenericRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by domann on 15.07.15.
 */
@Repository
public interface ChildBiDirectionalRepository extends GenericRepository<ChildBiDirectional, Long> {

    @EntityGraph(value = ChildBiDirectional.EG_PROFILE_FULL, type = EntityGraph.EntityGraphType.LOAD)
    ChildBiDirectional findById(Long id);

    List<Long> getIDs();

}
