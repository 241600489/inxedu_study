package com.inxedu.os.nstar.selectCourse.entity.course;

import lombok.Data;

import java.io.Serializable;

@Data
public class CourseBase implements Serializable {

    private Integer id;

    private String courseName;

    private String courseNo;

    private String memo;

}