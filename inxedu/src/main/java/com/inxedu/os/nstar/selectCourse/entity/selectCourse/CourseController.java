package com.inxedu.os.nstar.selectCourse.entity.selectCourse;

/**
 * Created by 1 on 2016/7/29.
 */

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CourseController implements Serializable {

    private Integer id;

    private Date beginCourseSignupTime0;    //正选时间

    private Date beginCourseSignupTime1;    //补选时间

    private Date endCourseSignupTime0;  //正选结束时间

    private Date endCourseSignupTime1;  //补选结束时间

    private String memo;    //备注

    private Date updateDate;    //修改时间

    private String updateMan;   //更新人员
    private int type;

}