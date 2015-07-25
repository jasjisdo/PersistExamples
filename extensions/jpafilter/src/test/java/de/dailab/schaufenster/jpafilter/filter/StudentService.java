package de.dailab.schaufenster.jpafilter.filter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class StudentService {

    @Inject
    private StudentRepository studentRepository;

    public List<Student> searchStudent(String subject, String school, String _class) {

        List<Student> studentList;

        // Prepare parameters for query filter
        HashMap<String, Object> inFilterParams = new HashMap<String, Object>();
        inFilterParams.put("school", "Hong Kong Secondary School");
        inFilterParams.put("class", "S5");

        // Prepare parameters for query
        HashMap<String, Object> inParams = new HashMap<String, Object>();
        inParams.put("subject", "Physics");

        studentList = studentRepository.doQueryWithFilter(
                "filterBySchoolAndClass", "query1",
                inFilterParams, inParams);

        return studentList;
    }
}