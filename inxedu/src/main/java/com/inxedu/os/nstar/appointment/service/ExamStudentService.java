package com.inxedu.os.nstar.appointment.service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.examClassroom.ExamClassroom;

import java.util.List;



import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.examClassroom.ExamClassroom;
import com.inxedu.os.nstar.appointment.entity.examStudent.ExamStudent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public  interface  ExamStudentService {
    /**
     * 增加
     */
    public void addExamStudent(ExamStudent examStudent);
    /**
     * 修改
     */
    public void  updateExamStudent(ExamStudent examStudent);
    /**
     * 删除
     */
    public void deleteExamStudent(int id);
    /**
     * 显示所有数据
     */
    public List<ExamStudent> queryExamStudent(PageEntity page);
    /**
     * 根据id获取对象
     */
    public  ExamStudent queryExamStudentById(int id);
    /**
     * 根据学号 姓名 班级模糊查询
     */
    public List<ExamStudent> queryExamStudentList(PageEntity page,int teacherId, String studentNo, String studentName, String studentClass);

    List<ExamStudent> queryAllExamStudent();

   public List<ExamStudent> queryExamStudentByTeacherId(int teacherId,PageEntity page);

    List<ExamStudent> queryExamStudentByTeacherIdAndSno(int teacherId, String studentNo);
    /**
     * 查询考场名称
     */
   /* public  List<ExamClassroom>  queryExamStudentName();*/
}