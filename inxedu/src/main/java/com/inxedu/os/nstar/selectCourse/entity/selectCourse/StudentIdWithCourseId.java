package com.inxedu.os.nstar.selectCourse.entity.selectCourse;


import java.io.Serializable;

/**
 * Created by karak on 16-8-4.
 */

public class StudentIdWithCourseId implements Serializable {
    String studentId;
    String courseId;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
