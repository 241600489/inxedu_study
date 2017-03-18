package com.inxedu.os.nstar.appointment.service;

import com.inxedu.os.common.entity.PageEntity;
import com.inxedu.os.nstar.appointment.entity.examClassroom.ExamClassroom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

public  interface  ExamClassroomService {
    /**
     * 增加
     */
    public void addexamClassRooom(ExamClassroom examClassroom);
    /**
     * 修改
     */
    public void  updateexamClassRooom(ExamClassroom examClassroom);
    /**
     * 删除
     */
    public void deleteexamClassRooom(int id);
    /**
     * 显示所有数据
     */
   public List<ExamClassroom> queryexamClassRooom(PageEntity page);
    /**
     * 根据id获取对象
     */
    public  ExamClassroom queryClassRooomById(int id);
    /**
     * 根据考场名称,查询
     */
    public List<ExamClassroom> queryClassRoomList(PageEntity page, String examClassroomName);
    /**
     * 根据考场名称模糊查询
     */
    public List<ExamClassroom> queryClassSbRoomList(PageEntity page, String examClassroomName);
    /**
     * 查询考场名称
     */
    public  List<ExamClassroom>  queryClassRoomName();

    ExamClassroom queryClassRoomByName(String examClassroomName);
}