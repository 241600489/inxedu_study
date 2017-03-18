package com.inxedu.os.nstar.appointment.service.impl;

import com.inxedu.os.common.dao.GenericDaoImpl;
import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.examClassroom.ExamClassroom;
import com.inxedu.os.nstar.appointment.service.ExamClassroomService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/** 考场管理
 * Created by  on 2016/8/3.
 */
@Service
public class ExamClassroomServiceImpl extends GenericDaoImpl implements ExamClassroomService{
    @Override
    public void addexamClassRooom(ExamClassroom examClassroom) {
        this.insert("ExamClassroomMapper.insert",examClassroom);
    }

    @Override
    public void updateexamClassRooom(ExamClassroom examClassroom) {
      this.update("ExamClassroomMapper.updateExamClassroom",examClassroom);
    }

    @Override
    public void deleteexamClassRooom(int id) {
      this.delete("ExamClassroomMapper.deleteByPrimaryKey",id);
    }

    @Override
    public List<ExamClassroom> queryexamClassRooom(PageEntity page) {
      List<ExamClassroom> list1=this.queryForListPage("ExamClassroomMapper.getExamClassroomList",null,page);


        return list1;
    }

    @Override
    public ExamClassroom queryClassRooomById(int id) {










        return  this.selectOne("ExamClassroomMapper.queryClassRooomById",id);
    }

    @Override
    public List<ExamClassroom> queryClassRoomList(PageEntity page, String examClassroomName) {
        ExamClassroom examClassroom=new ExamClassroom();
        if("".equals(examClassroomName)){
            examClassroomName=null;
        }
        examClassroom.setExamClassroomName(examClassroomName);
        List<ExamClassroom> list=this.queryForListPage("ExamClassroomMapper.queryClassSByName",examClassroom,page);
        return list;
    }

    @Override
    public List<ExamClassroom> queryClassSbRoomList(PageEntity page, String examClassroomName) {
        ExamClassroom examClassroom=new ExamClassroom();
        examClassroom.setExamClassroomName(examClassroomName);
        List<ExamClassroom> list=this.queryForListPage("ExamClassroomMapper.queryClassRoomByName",examClassroom,page);
        return list;

    }

    @Override
    public List<ExamClassroom> queryClassRoomName() {
        List<ExamClassroom> list=this.selectList("ExamClassroomMapper.queryClassRoomName",null);
        return list;
    }

    @Override
    public ExamClassroom queryClassRoomByName(String examClassroomName) {
        return this.selectOne("ExamClassroomMapper.queryClassRoom",examClassroomName);
    }

}
