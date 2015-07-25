package de.hbmexample2.repository;

import de.hbmexample2.entity.one2many.bidirectional.MotherBiDirectional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by domann on 15.07.15.
 */
@Repository
public interface MotherBiDirectionalRepository extends CustomCrudRepository<MotherBiDirectional, Long>{

    @EntityGraph(value = MotherBiDirectional.EG_PROFILE_FULL, type = EntityGraph.EntityGraphType.LOAD)
    MotherBiDirectional findById(Long id);

    List<Long> getIDs();

}
