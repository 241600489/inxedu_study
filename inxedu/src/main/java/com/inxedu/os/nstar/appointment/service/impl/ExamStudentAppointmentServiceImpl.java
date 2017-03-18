package com.inxedu.os.nstar.appointment.service.impl;
import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.StudentAppointment;
import com.inxedu.os.nstar.appointment.entity.examStudent.ExamStudent;
import com.inxedu.os.nstar.appointment.entity.examStudent.QueryExamStudent;
import com.inxedu.os.nstar.appointment.entity.examStudentAppointment.ExamStudentAppointment;
import com.inxedu.os.nstar.appointment.service.ExamStudentAppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamStudentAppointmentServiceImpl extends GenericDaoImpl implements ExamStudentAppointmentService {

    //添加
    @Override
    public void addExamStudentAppointment(StudentAppointment studentAppointment){
        this.insert("ExamStudentAppointmentMapper.insert",studentAppointment);
    }

    // 修改
    @Override
    public void  updateExamStudentAppointment(StudentAppointment studentAppointment){
        this.update("ExamStudentAppointmentMapper.update",studentAppointment);
    }

    //删除
    @Override
    public void deleteExamStudentAppointment(int id){
        this.delete("ExamStudentAppointmentMapper.delete",id);
    }


    // 显示所有数据
    @Override
    public List<StudentAppointment> queryExamStudentAppointmentList(PageEntity page){
        List<StudentAppointment> list = this.queryForListPage("ExamStudentAppointmentMapper.getExamStudentAppointmentList",null,page);

        return list;
    }

    // 根据id获取对象
    @Override
    public  ExamStudentAppointment queryStudentAppointmentById(int id){
        return this.selectOne("ExamStudentAppointmentMapper.queryStudentAppointmentById",id);
    }

    @Override
    public boolean queryStudentAppointment(String studentNo, String studentName, String examName) {
        if(studentNo.equals("")){
            studentNo=null;
        }
        if ("".equals(studentName))
        {
            studentName=null;
        }
        if ("".equals(examName)){
            return false;
        }
        if(studentNo==null&&studentName==null){
            return false;
        }
        ExamStudentAppointment examStudentAppointment=new ExamStudentAppointment();
        examStudentAppointment.setStudentNo(studentNo);
        examStudentAppointment.setStudentName(studentName);
        examStudentAppointment.setExamName(examName);
        ExamStudentAppointment list=  this.selectOne("ExamStudentAppointmentMapper.queryStudentAppointment",examStudentAppointment);
         return  list.getState();
    }

    @Override
    public List<ExamStudentAppointment> queryExamStudentAppointmentBySSE(String studentNo, String studentName, String examName,PageEntity page) {
        if("".equals(studentName)){
            studentName=null;
        }
        if ("".equals(examName)){
            examName=null;
        }
        if ("".equals(studentNo))
        {
            studentNo=null;
        }
        ExamStudentAppointment examStudentAppointment=new ExamStudentAppointment();
        examStudentAppointment.setStudentName(studentName);
        examStudentAppointment.setStudentNo(studentNo);
        examStudentAppointment.setExamName(examName);
        List<ExamStudentAppointment> list=this.queryForListPage("ExamStudentAppointmentMapper.queryExamStudentAppointmentBySSE",examStudentAppointment,page);
        return list;
    }

    @Override
    public List<ExamStudentAppointment> queryExamAppointmentStudentByTeacherIdAndExamName(int teacherId, String studentName, String studentNo, String stuClass, String examName, PageEntity page) {
        QueryExamStudent queryExamStudent=new QueryExamStudent();
        if("".equals(studentName)){
            studentName=null;
        }
        if ("".equals(examName)){
            examName=null;
        }
        if ("".equals(studentNo))
        {
            studentNo=null;
        }
        queryExamStudent.setExamName(examName);
        queryExamStudent.setTeacherId(teacherId);
        queryExamStudent.setStudentNo(studentNo);
        queryExamStudent.setStudentClass(stuClass);
        queryExamStudent.setStudentName(studentName);
        return this.queryForListPage("ExamStudentAppointmentMapper.queryExamAppointmentStudentByTeacherIdAndExamName",queryExamStudent,page);
    }
    @Override
    public List<ExamStudent> queryNoExamAppointmentStudentByTeacherIdAndExamName(int teacherId, String studentNo, String studentName, String stuClass, String examName, PageEntity page) {
        QueryExamStudent queryExamStudent=new QueryExamStudent();
        queryExamStudent.setExamName(examName);
        queryExamStudent.setTeacherId(teacherId);
        if("".equals(studentName)){
            studentName=null;
        }
        if ("".equals(examName)){
            examName=null;
        }
        if ("".equals(studentNo))
        {
            studentNo=null;
        }
        queryExamStudent.setStudentClass(stuClass);
        queryExamStudent.setStudentNo(studentNo);
        queryExamStudent.setStudentName(studentName);
        return this.queryForListPage("ExamStudentMapper.queryNoExamAppointmentStudentByTeacherIdAndExamName",queryExamStudent,page);
    }
}