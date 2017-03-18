package com.inxedu.os.nstar.selectCourse.service;

import com.inxedu.os.nstar.selectCourse.entity.selectCourse.CourseController;

/**
 * Created by 1 on 2016/7/29.
 */
/**
 * Created by 1 on 2016/7/29.
 */
public interface CourseControllerService {

    /*
    获取选课时间控制的信息
     */
    public CourseController getEduCourseController();

    /*
    修改选课时间控制
     */
    public void setEduCourseController(CourseController eduCourseController);
}
