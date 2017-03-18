package com.inxedu.os.nstar.selectCourse.entity.selectCourse;

import java.io.Serializable;
import java.util.Date;

public class CourseStudent implements Serializable {
    public CourseStudent(Integer courseTableId, Date selectCourseDateTime, Integer studentId) {
        this.courseTableId = courseTableId;
        this.selectCourseDateTime = selectCourseDateTime;
        this.studentId = studentId;
    }

    private Integer id;

    private Integer courseTableId;

    private Date selectCourseDateTime;

    private Integer studentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseTableId() {
        return courseTableId;
    }

    public void setCourseTableId(Integer courseTableId) {
        this.courseTableId = courseTableId;
    }

    public Date getSelectCourseDateTime() {
        return selectCourseDateTime;
    }

    public void setSelectCourseDateTime(Date selectCourseDateTime) {
        this.selectCourseDateTime = selectCourseDateTime;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}