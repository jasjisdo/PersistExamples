package com.github.jajisdo.hbmexample2.service;

import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.Child;
import com.github.jajisdo.hbmexample2.repository.ChildRepository;
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
public class ChildService extends AbstractDbService<Child, ChildRepository>{

    @Override
    public Child findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Child> findAll() {
        List<Child> children = new ArrayList<>();
        List<Long> ids = repository.getIDs();
        for (Long id : ids){
            children.add(repository.findById(id));
        }
        return children;
    }

    @Override
    @Qualifier("childRepository")
    @Autowired
    public void setRepository(ChildRepository repository) {
        this.repository = repository;
    }
}

