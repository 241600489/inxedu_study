package com.inxedu.os.nstar.appointment.service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.examCourse.ExamCourse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public  interface ExamCourseService {
    /**
     * 添加
     */
    public void addexamCourse(ExamCourse examCourse);
    /**
     * 修改
     */
    public  void updateExamCourse(ExamCourse examCourse);
    /**
     * 删除
     */
    public void deleteexamCourse(int id);
    /**
     * 查询所有的数据
     */
    public  List<ExamCourse> queryAllExamCourse(PageEntity page);
    /**
     *根据ID获取对象
     */
    public ExamCourse queryCourseById(int id);
    /**
     *  根据课程代码模糊查询
     */
    public List<ExamCourse> queryAllcourseCodeList(PageEntity page, String courseCode);
    /**
     * 查询所有课程
     */
    public List<ExamCourse> queryAllExamCourse();
    public ExamCourse queryCourseByCode(String code);
}

