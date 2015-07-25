package de.hbmexample2.repository;

import de.hbmexample2.entity.one2many.bidirectional.ChildBiDirectional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by domann on 15.07.15.
 */
@Repository
public interface ChildBiDirectionalRepository extends CustomCrudRepository<ChildBiDirectional, Long>{

    @EntityGraph(value = ChildBiDirectional.EG_PROFILE_FULL, type = EntityGraph.EntityGraphType.LOAD)
    ChildBiDirectional findById(Long id);

    List<Long> getIDs();

}
