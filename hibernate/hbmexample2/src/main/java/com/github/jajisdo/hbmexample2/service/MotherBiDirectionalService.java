package com.github.jajisdo.hbmexample2.service;

import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.MotherBiDirectional;
import com.github.jajisdo.hbmexample2.repository.MotherBiDirectionalRepository;
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
public class MotherBiDirectionalService extends AbstractDbService<MotherBiDirectional, MotherBiDirectionalRepository>{

    @Override
    public MotherBiDirectional findById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<MotherBiDirectional> findAll() {
        List<MotherBiDirectional> mothers = new ArrayList<>();
        List<Long> ids = repository.getIDs();
        for (Long id : ids){
            mothers.add(repository.findById(id));
        }
        return mothers;
    }

    @Override
    @Qualifier("motherBiDirectionalRepository")
    @Autowired
    public void setRepository(MotherBiDirectionalRepository repository) {
        this.repository = repository;
    }
}
