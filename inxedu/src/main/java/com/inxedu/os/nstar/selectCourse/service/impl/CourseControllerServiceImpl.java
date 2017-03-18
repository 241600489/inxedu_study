package com.inxedu.os.nstar.selectCourse.service.impl;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.nstar.selectCourse.entity.selectCourse.CourseController;
import com.inxedu.os.nstar.selectCourse.service.CourseControllerService;
import org.springframework.stereotype.Service;

/**
 * Created by 1 on 2016/7/29.
 */
@Service
public class CourseControllerServiceImpl extends GenericDaoImpl implements CourseControllerService {

    @Override
    public CourseController getEduCourseController() {
        CourseController eduCourseController = this.selectOne("CourseControllerMappper.selectController", null);
        return eduCourseController;
    }

    @Override
    public void setEduCourseController(CourseController eduCourseController) {
        this.update("CourseControllerMappper.updateController",eduCourseController);
    }
}
