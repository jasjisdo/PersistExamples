package com.github.jajisdo.hbmexample2.entity.one2one.unidirectional;

/**
 * Created by domann on 30.07.15.
 */
public class StudentFactory {

    public static Student createStudent() {
        Student student = new Student();
        student.setName("Martin");
        MatriculationNumber number = new MatriculationNumber();
        number.setNumber(315384);
        try {
            student.setMatriculationNumber(number);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }
}
