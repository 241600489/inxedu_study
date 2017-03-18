package com.inxedu.os.nstar.selectCourse.service;

import com.inxedu.os.nstar.selectCourse.entity.course.CourseBase;
import com.inxedu.os.nstar.selectCourse.entity.selectCourse.CourseTableDo;

import java.util.List;

/**
 * Created by seapig02 on 7/28 0028.
 */
public interface CourseTableDoService{
    /**
     * 根据班级名称查询班级id
     */
    public Integer queryClassIdByName(String className);
    /**
     * 根据课程名称查询课程id
     */
    public int queryCourseIdByName(String courseName);
    /**
     * 根据id查询课程名称
     */
    public List<String> queryCourseName();

    public List<CourseBase>  queryBaseCourseName() throws Exception;

    public String queryCourseNameById(int i);

    public int queryCourseByCourseId(String id);

    List<CourseTableDo> queryAllCoueBase();
}
