package com.github.jajisdo.hbmexample2.service;

import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.Parent;
import com.github.jajisdo.hbmexample2.repository.ParentRepository;
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
public class ParentService extends AbstractDbService<Parent, ParentRepository>{

    @Override
    public Parent findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Parent> findAll() {
        List<Parent> mothers = new ArrayList<>();
        List<Long> ids = repository.getIDs();
        for (Long id : ids){
            mothers.add(repository.findById(id));
        }
        return mothers;
    }

    public Parent findByName(String name){
        return repository.findByName(name);
    }

    @Override
    @Qualifier("parentRepository")
    @Autowired
    public void setRepository(ParentRepository repository) {
        this.repository = repository;
    }
}
