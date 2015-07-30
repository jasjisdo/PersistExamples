package com.github.jajisdo.hbmexample2.entity.one2one.unidirectional;

import com.github.jajisdo.hbmexample2.entity.one2many.bidirectional.MotherBiDirectional;

/**
 * Created by domann on 30.07.15.
 */
public class StudentFactory {

    public static StudentUniDirectional createStudent() {
        StudentUniDirectional student = new StudentUniDirectional();
        student.setName("Martin");
        MatriculationNumberUniDirectional number = new MatriculationNumberUniDirectional();
        number.setNumber(315384);
        try {
            student.setMatriculationNumber(number);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }
}
