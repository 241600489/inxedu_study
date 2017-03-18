package com.inxedu.os.nstar.selectCourse.service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.selectCourse.entity.course.CourseBase;

import java.util.List;

/**
 * Created by seapig02 on 7/29 0029.
 */
public interface CourseBaseService {
    /**
     * 添加数据
     */
    public void  addBaseCourse(CourseBase eduCourseBase);
    /**
     * 根据主键删除数据
     */
    public void deleteBaseCourse(int id);
    /**
     * 修改数据
     */
    public  void updateBaseCourse(CourseBase eduCourseBase);
    /**
     * 查询所有数据
     */
    public List<CourseBase> queryAllCourseBase(PageEntity page);
    /**
     * 根据课程名称或/和课程号查询
     */
    public List<CourseBase> query(String name, String no,PageEntity page);
    /**
     * 根据id查询
     */
    public CourseBase queryByPrimaryKey(int id);
    /**
     * 查询课程名称
     */
    public List<String> queryCourseNO();

    public List<CourseBase> query(String name);

    CourseBase queryCourseNo(String courseName);

    CourseBase queryCourseBaseByNo(String courseNo);
}
