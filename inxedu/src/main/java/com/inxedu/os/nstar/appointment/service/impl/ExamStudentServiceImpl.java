package com.inxedu.os.nstar.appointment.service.impl;
import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.examStudent.ExamStudent;
import com.inxedu.os.nstar.appointment.entity.examStudent.QueryExamStudent;
import com.inxedu.os.nstar.appointment.service.ExamStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class  ExamStudentServiceImpl extends GenericDaoImpl implements ExamStudentService {

    @Override
    public void addExamStudent(ExamStudent examStudent) {
      this.insert("ExamStudentMapper.insert",examStudent);

    }

    @Override
    public void updateExamStudent(ExamStudent examStudent) {

    this.update("ExamStudentMapper.updateByPrimaryKey",examStudent);
    }

    @Override
    public void deleteExamStudent(int id) {
        this.delete("ExamStudentMapper.deleteByPrimaryKey",id);

    }

    @Override
    public List<ExamStudent> queryExamStudent(PageEntity page) {
        List<ExamStudent> list=this.queryForListPage("ExamStudentMapper.queryAllExamStudent",null,page);
        return list;
    }

    @Override
    public ExamStudent queryExamStudentById(int id) {
      ExamStudent examStudent=  this.selectOne("ExamStudentMapper.queryExamStudentById",id);
        return examStudent ;
    }

    @Override
    public List<ExamStudent> queryExamStudentList(PageEntity page,int teacherId, String studentNo, String studentName, String studentClass) {
       QueryExamStudent examStudent=new QueryExamStudent();
        if("".equals(studentNo)){
            studentNo=null;
        }
        if("".equals(studentName)){
            studentName=null;
        }
        if("".equals(studentClass)){
            studentClass=null;
        }

        examStudent.setStudentClass(studentClass);
        examStudent.setStudentName(studentName);
        examStudent.setStudentNo(studentNo);
        examStudent.setTeacherId(teacherId);
        List<ExamStudent> list=this.queryForListPage("ExamStudentMapper.queryExamStudentByMohu",examStudent,page);
        return list;
    }

    @Override
    public List<ExamStudent> queryAllExamStudent() {
        List<ExamStudent> list=this.selectList("ExamStudentMapper.queryAllExamStudentVerlidate",null);
        return list;
    }



    @Override
    public List<ExamStudent> queryExamStudentByTeacherId(int teacherId,PageEntity page) {
        QueryExamStudent queryExamStudent=new QueryExamStudent();
        queryExamStudent.setTeacherId(teacherId);
        List<ExamStudent> list=this.queryForListPage("ExamStudentMapper.queryExamStudentByTeacherId",queryExamStudent,page);
        return list;
    }

    @Override
    public List<ExamStudent> queryExamStudentByTeacherIdAndSno(int teacherId, String studentNo) {
        QueryExamStudent queryExamStudent=new QueryExamStudent();
        queryExamStudent.setStudentNo(studentNo);
        queryExamStudent.setTeacherId(teacherId);
        return this.selectList("ExamStudentMapper.queryExamStudentByTeacherIdAndSno",queryExamStudent);
    }
}