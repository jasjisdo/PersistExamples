package de.hbmexample2.service;

import de.hbmexample2.entity.one2many.bidirectional.ChildBiDirectional;
import de.hbmexample2.entity.one2many.bidirectional.MotherBiDirectional;
import de.hbmexample2.repository.ChildBiDirectionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by domann on 15.07.15.
 */
@Service
@Repository
@Transactional
public class ChildBiDirectionalService extends AbstractDbService<ChildBiDirectional, ChildBiDirectionalRepository>{

    @Override
    public ChildBiDirectional findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<ChildBiDirectional> findAll() {
        List<ChildBiDirectional> children = new ArrayList<>();
        List<Long> ids = repository.getIDs();
        for (Long id : ids){
            children.add(repository.findById(id));
        }
        return children;
    }

    @Override
    @Qualifier("childBiDirectionalRepository")
    @Autowired
    public void setRepository(ChildBiDirectionalRepository repository) {
        this.repository = repository;
    }
}

