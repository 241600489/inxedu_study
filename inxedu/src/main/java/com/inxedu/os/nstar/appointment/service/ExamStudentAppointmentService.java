package com.inxedu.os.nstar.appointment.service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.StudentAppointment;
import com.inxedu.os.nstar.appointment.entity.examStudent.ExamStudent;
import com.inxedu.os.nstar.appointment.entity.examStudentAppointment.ExamStudentAppointment;

import java.util.List;


public  interface  ExamStudentAppointmentService {
    /**
     * 增加
     */
    public void addExamStudentAppointment(StudentAppointment studentAppointment);

    /**
     * 修改
     */
    public void updateExamStudentAppointment(StudentAppointment studentAppointment);

    /**
     * 删除
     */
    public void deleteExamStudentAppointment(int id);

    /**
     * 显示所有数据
     */
    public List<StudentAppointment> queryExamStudentAppointmentList(PageEntity page);

    /**
     * 根据id获取对象
     */
    public ExamStudentAppointment queryStudentAppointmentById(int id);

    /**
     * 根据考试名称
     */
    public boolean queryStudentAppointment(String studentNo, String studentName, String examName);

    /**号和姓名模糊查询
     * 根据考试名称,学
     */
    public  List<ExamStudentAppointment> queryExamStudentAppointmentBySSE(String studentNo, String studentName, String examName, PageEntity page);

    public List<ExamStudentAppointment> queryExamAppointmentStudentByTeacherIdAndExamName(int teacherId, String studentName, String studentNo, String stuClass, String examName, PageEntity page);

     public  List<ExamStudent> queryNoExamAppointmentStudentByTeacherIdAndExamName(int teacherId, String studentNo, String studentName, String stuClass, String examName, PageEntity page);
}