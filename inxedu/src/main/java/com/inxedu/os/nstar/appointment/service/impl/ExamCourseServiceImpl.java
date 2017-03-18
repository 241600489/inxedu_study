package com.inxedu.os.nstar.appointment.service.impl;
import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.common.service.email.EmailService;
import com.inxedu.os.nstar.appointment.entity.examCourse.ExamCourse;
import com.inxedu.os.nstar.appointment.service.ExamCourseService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**考试课程管理
 * Created by  on 16-8-5.
 */
@Service
public class ExamCourseServiceImpl extends GenericDaoImpl implements ExamCourseService{

    @Override
    public void addexamCourse(ExamCourse examCourse) {
         this.insert("ExamCourseMapper.insert",examCourse);
    }

    @Override
    public void updateExamCourse(ExamCourse examCourse) {
        this.update("ExamCourseMapper.updateExamCourse",examCourse);

    }

    @Override
    public void deleteexamCourse(int id) {
        this.delete("ExamCourseMapper.deleteByPrimaryKey",id);

    }

    @Override
    public List<ExamCourse> queryAllExamCourse(PageEntity page) {
         List<ExamCourse> list=this.queryForListPage("ExamCourseMapper.queryAllExamCourse",null,page);
        return list;
    }

    @Override
    public ExamCourse queryCourseById(int id) {
         return this.selectOne("ExamCourseMapper.queryCourseById",id);
    }

    @Override
    public List<ExamCourse> queryAllcourseCodeList(PageEntity page, String courseCode) {
        ExamCourse examCourse =new ExamCourse();
        examCourse.setCourseCode(courseCode);
        List<ExamCourse> list =this.queryForListPage("ExamCourseMapper.queryExamCourseBycourseCode",examCourse,page);
        return list;
    }

    @Override
    public List<ExamCourse> queryAllExamCourse() {
        List<ExamCourse> list=this.selectList("ExamCourseMapper.queryAllExamCorse",null);
        return list;
    }

    @Override
    public ExamCourse queryCourseByCode(String code) {
        ExamCourse examCourse=new ExamCourse();
        examCourse.setCourseCode(code);
        ExamCourse list=this.selectOne("ExamCourseMapper.queryExamCourseByCode",examCourse);
        return list;
    }
}
