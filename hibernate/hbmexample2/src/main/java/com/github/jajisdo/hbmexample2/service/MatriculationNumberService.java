package com.github.jajisdo.hbmexample2.service;

import com.github.jajisdo.hbmexample2.entity.one2one.unidirectional.MatriculationNumber;
import com.github.jajisdo.hbmexample2.repository.MatriculationNumberRepository;
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
public class MatriculationNumberService extends
        AbstractDbService<MatriculationNumber, MatriculationNumberRepository> {

    @Override
    public MatriculationNumber findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<? extends MatriculationNumber> findAll() {
        List<MatriculationNumber> numbers = new ArrayList<>();
        List<Long> ids = repository.getIDs();
        for (Long id : ids){
            numbers.add(repository.findById(id));
        }
        return numbers;
    }

    @Override
    @Qualifier("matriculationNumberRepository")
    @Autowired
    public void setRepository(MatriculationNumberRepository repository) {
        this.repository = repository;
    }
}
