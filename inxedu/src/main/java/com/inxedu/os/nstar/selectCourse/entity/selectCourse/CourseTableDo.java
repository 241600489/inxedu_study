package com.inxedu.os.nstar.selectCourse.entity.selectCourse;

import lombok.Data;

import java.io.Serializable;

@Data
public class
CourseTableDo implements Serializable {
    private int id;

    private int sessionId;

    private int weekId;

    private int classId;

    private String courseIds;

    private String description;

}