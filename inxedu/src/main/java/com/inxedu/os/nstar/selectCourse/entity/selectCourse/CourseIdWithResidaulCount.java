package com.inxedu.os.nstar.selectCourse.entity.selectCourse;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by karak on 16-8-3.
 */
@Data
public class CourseIdWithResidaulCount implements Serializable {
    String id;
    String residaulCount;
}
