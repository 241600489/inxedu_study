package com.inxedu.os.nstar.selectCourse.service;

import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.nstar.selectCourse.entity.SelectCourseState;

import java.util.List;

/**
 * Created by 陈俊文 on 16-8-3.
 */
public interface QuickSelectCourseService extends SelectLessionService {
    public SelectCourseState quickSelectCourseWithCourseController(final Integer courseId, final Integer studentId, final Integer classId);

    public SelectCourseState quickBackCourseWithCourseController(final Integer courseId, final Integer studentId, final Integer classId);

    public boolean initQuickSelectCourse();

    public void stopQuickSelectCourse();

    public boolean changeQuickSelectCourse(final Integer courseId, final Integer residaulCount);

    public List<Course> gettQuickCourse(final Integer... id);

    public List<Course> gettQuickMyCourse(final Integer userId);
}
