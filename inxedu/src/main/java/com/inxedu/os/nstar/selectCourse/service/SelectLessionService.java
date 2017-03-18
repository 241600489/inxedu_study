package com.inxedu.os.nstar.selectCourse.service;

import com.inxedu.os.edu.entity.course.Course;
import com.inxedu.os.nstar.selectCourse.entity.SelectCourseState;

import java.util.List;

/**
 * Created by Nstar on 2016/7/26.
 *
 public interface SelectLessionService {

 // 获取可选课位
 public List showValidCourseLocation(Integer classId);

 // 获取可选课程
 public List showValidCourseInfo(String coursIds);


 }
 */

public interface SelectLessionService {
    public List<Course> showMyCourse(final int userId);
    public boolean isValidCourseId(final Integer courseId, final String weekId, final String sessionId, final Integer classId);
    public boolean isSelectCourse(final Integer userId);
    public boolean checkTime();
    public String[] getValidCourseIds(final String weekId, final String sessionId, final Integer classId);
    public SelectCourseState backCourseCore(final Integer studentId, final Integer courseId);
    public SelectCourseState selectCourseCore(final Integer studentId, final Integer courseId);
    public List<Course> showValidCourseInfo(final String weekId, final String sessionId, final Integer classId);
    public boolean[][] showValidCourseLocation(final Integer classId);
    public List<Course> getValidCourse(final String weekId, final String sessionId);
    public default boolean[][] showValidCourseLocation(){
        return null;
    }
    public boolean isValidCourseId(Integer courseId, final Integer classId);


    public default SelectCourseState selectCourseWithCourseController(final Integer studentId, final Integer courseId, final Integer classId) {
        if (checkTime()) {
            return this.selectCourse(studentId, courseId, classId);
        } else {
            return SelectCourseState.ILLEGAL_TIME;
        }
    }
    public default SelectCourseState backCourseWithCourseController( final Integer studentId,final Integer courseId, final Integer classId) {
        if (checkTime()) {
            return this.backCourse(studentId, courseId, classId);
        } else {
            return SelectCourseState.ILLEGAL_TIME;
        }
    }


    public default SelectCourseState backCourse(final Integer studentId,final Integer courseId,final Integer classId) {
        if (isValidCourseId(courseId, classId)) {
            return backCourseCore(studentId, courseId);
        }
        return SelectCourseState.ILLEGAL;
    }


    public default SelectCourseState backCourse(final Integer studentId,final Integer courseId,final String weekId,final String sessionId,final Integer classId) {
        if (isValidCourseId(courseId, weekId, sessionId, classId)) {
            return backCourseCore(studentId, courseId);
        }
        return SelectCourseState.ILLEGAL;
    }



    public default SelectCourseState selectCourseWithCourseController(final  Integer studentId,final Integer courseId,final Integer classId,final String weekId,final String sessionId) {
        if (checkTime()) {
            return this.selectCourse(courseId, studentId, classId);
        } else {
            return SelectCourseState.ILLEGAL_TIME;
        }
    }

    public default SelectCourseState selectCourse(final Integer studentId, final Integer courseId, final Integer classId) {
        if (isValidCourseId(courseId, classId)) {
            return selectCourseCore(studentId, courseId);
        }
        return SelectCourseState.ILLEGAL;
    }
    public default SelectCourseState backCourseWithCourseController(final Integer studentId,final Integer courseId,final Integer classId,final String weekId,final String sessionId) {
        if (checkTime()) {
            return backCourse(studentId, courseId, weekId, sessionId, classId);
        }
        return SelectCourseState.ILLEGAL_TIME;
    }


    public default SelectCourseState selectCourse(final Integer studentId,final Integer courseId,  final String weekId, final String sessionId, final Integer classId) {
        if (isValidCourseId(courseId, weekId, sessionId, classId)) {
            return selectCourseCore(studentId, courseId);
        }
        return SelectCourseState.ILLEGAL;
    }


}