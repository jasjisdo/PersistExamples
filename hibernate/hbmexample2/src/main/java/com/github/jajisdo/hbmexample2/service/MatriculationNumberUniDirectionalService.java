package com.github.jajisdo.hbmexample2.service;

import com.github.jajisdo.hbmexample2.entity.one2one.unidirectional.MatriculationNumberUniDirectional;
import com.github.jajisdo.hbmexample2.repository.MatriculationNumberUniDirectionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by domann on 30.07.15.
 */
@Service
@Repository
@Transactional
public class MatriculationNumberUniDirectionalService extends
        AbstractDbService<MatriculationNumberUniDirectional, MatriculationNumberUniDirectionalRepository> {

    @Override
    public MatriculationNumberUniDirectional findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<? extends MatriculationNumberUniDirectional> findAll() {
        List<MatriculationNumberUniDirectional> numbers = new ArrayList<>();
        List<Long> ids = repository.getIDs();
        for (Long id : ids){
            numbers.add(repository.findById(id));
        }
        return numbers;
    }

    @Override
    @Qualifier("matriculationNumberUniDirectionalRepository")
    @Autowired
    public void setRepository(MatriculationNumberUniDirectionalRepository repository) {
        this.repository = repository;
    }
}
