package com.github.jajisdo.hbmexample2.service;

import com.github.jajisdo.hbmexample2.entity.one2one.unidirectional.Student;
import com.github.jajisdo.hbmexample2.repository.StudentRepository;
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
public class StudentService extends
        AbstractDbService<Student, StudentRepository> {

    @Override
    public Student findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<? extends Student> findAll() {
        List<Student> students = new ArrayList<>();
        List<Long> ids = repository.getIDs();
        for (Long id : ids){
            students.add(repository.findById(id));
        }
        return students;
    }

    @Override
    @Qualifier("studentRepository")
    @Autowired
    public void setRepository(StudentRepository repository) {
        this.repository = repository;
    }
}
